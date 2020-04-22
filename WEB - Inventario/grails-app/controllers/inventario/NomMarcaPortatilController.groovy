package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomMarcaPortatilController {

    NomMarcaPortatilService nomMarcaPortatilService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomMarcaPortatilService.list(params), model:[nomMarcaPortatilCount: nomMarcaPortatilService.count()]
    }

    protected void historico(NomMarcaPortatil nomMarcaPortatil, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomMarcaPortatil",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomMarcaPortatil.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomMarcaPortatil nomMarcaPortatil){

        def nomMarcaPortatilOriginal = NomMarcaPortatil.get(nomMarcaPortatil.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomMarcaPortatilOriginal.isDirty()) {
            nomMarcaPortatilOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomMarcaPortatilOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomMarcaPortatilOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la marca de portátil (ID = ${nomMarcaPortatil.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la marca de portátil (ID = ${nomMarcaPortatil.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomMarcaPortatil,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomMarcaPortatilService.get(id)
    }

    def create() {
        respond new NomMarcaPortatil(params)
    }

    def save(NomMarcaPortatil nomMarcaPortatil) {
        if (nomMarcaPortatil == null) {
            notFound()
            return
        }

        try {
            nomMarcaPortatilService.save(nomMarcaPortatil)
            historico(nomMarcaPortatil,"El usuario insertó una nueva marca de portátil (ID = ${nomMarcaPortatil.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomMarcaPortatil.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomMarcaPortatil.label', default: 'NomMarcaPortatil'), nomMarcaPortatil.id])
                redirect nomMarcaPortatil
            }
            '*' { respond nomMarcaPortatil, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomMarcaPortatilService.get(id)
    }

    def update(NomMarcaPortatil nomMarcaPortatil) {
        if (nomMarcaPortatil == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomMarcaPortatil)
            nomMarcaPortatilService.save(nomMarcaPortatil)

        } catch (ValidationException e) {
            respond nomMarcaPortatil.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomMarcaPortatil.label', default: 'NomMarcaPortatil'), nomMarcaPortatil.id])
                redirect nomMarcaPortatil
            }
            '*'{ respond nomMarcaPortatil, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomMarcaPortatil nomMarcaPortatil = nomMarcaPortatilService.get(id)
        historico(nomMarcaPortatil,"El usuario eliminó la marca de portátil (ID = ${nomMarcaPortatil.id}).","Sin acción")
        nomMarcaPortatilService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomMarcaPortatil.label', default: 'NomMarcaPortatil'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomMarcaPortatil.label', default: 'NomMarcaPortatil'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def uploadFile() {
        def file = request.getFile('excelFile')
        if(!file.empty) {
            def sheetheader = []
            def values = []
            def workbook = new XSSFWorkbook(file.getInputStream())
            def sheet = workbook.getSheetAt(0)

            for (cell in sheet.getRow(0).cellIterator()) {
                sheetheader << cell.stringCellValue
            }

            def headerFlag = true
            for (row in sheet.rowIterator()) {
                if (headerFlag) {
                    headerFlag = false
                    continue
                }
                def value = ''
                def map = [:]
                for (cell in row.cellIterator()) {
                    switch(cell.cellType) {
                        case 1:
                            value = cell.stringCellValue
                            map["${sheetheader[cell.columnIndex]}"] = value
                            break
                        case 0:
                            value = cell.numericCellValue
                            map["${sheetheader[cell.columnIndex]}"] = value
                            break
                        default:
                            value = ''
                    }
                }
                values.add(map)
            }

            values.each { v ->
                try {
                    if(v) {
                        NomMarcaPortatil.findByMarcaPortatil(v.marcaPortatil)?: new NomMarcaPortatil(marcaPortatil:v.marcaPortatil).save(flush:true, failOnError:true)
                        flash.message = "1"
                    }
                }
                catch (ValidationException e) {
                    flash.message = "0"
                    return
                }
            }
            redirect action:"index"
        }
    }

    def downloadFile(){
        def products = NomMarcaPortatil.list(params)
        def headers = ['marcaMarcaPortatil']
        def withProperties = ['marcaMarcaPortatil']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
