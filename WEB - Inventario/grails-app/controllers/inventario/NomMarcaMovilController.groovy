package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomMarcaMovilController {

    NomMarcaMovilService nomMarcaMovilService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomMarcaMovilService.list(params), model:[nomMarcaMovilCount: nomMarcaMovilService.count()]
    }

    protected void historico(NomMarcaMovil nomMarcaMovil, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomMarcaMovil",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomMarcaMovil.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomMarcaMovil nomMarcaMovil){

        def nomMarcaMovilOriginal = NomMarcaMovil.get(nomMarcaMovil.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomMarcaMovilOriginal.isDirty()) {
            nomMarcaMovilOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomMarcaMovilOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomMarcaMovilOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }
            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la marca de móvil (ID = ${nomMarcaMovil.id}):\n"
        }
        else {
            mensaje1 = "El usuario accedió a la marca de móvil (ID = ${nomMarcaMovil.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomMarcaMovil,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomMarcaMovilService.get(id)
    }

    def create() {
        respond new NomMarcaMovil(params)
    }

    def save(NomMarcaMovil nomMarcaMovil) {
        if (nomMarcaMovil == null) {
            notFound()
            return
        }

        try {
            nomMarcaMovilService.save(nomMarcaMovil)
            historico(nomMarcaMovil,"El usuario insertó una nueva marca de móvil (ID = ${nomMarcaMovil.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomMarcaMovil.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomMarcaMovil.label', default: 'NomMarcaMovil'), nomMarcaMovil.id])
                redirect nomMarcaMovil
            }
            '*' { respond nomMarcaMovil, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomMarcaMovilService.get(id)
    }

    def update(NomMarcaMovil nomMarcaMovil) {
        if (nomMarcaMovil == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomMarcaMovil)
            nomMarcaMovilService.save(nomMarcaMovil)

        } catch (ValidationException e) {
            respond nomMarcaMovil.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomMarcaMovil.label', default: 'NomMarcaMovil'), nomMarcaMovil.id])
                redirect nomMarcaMovil
            }
            '*'{ respond nomMarcaMovil, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomMarcaMovil nomMarcaMovil = nomMarcaMovilService.get(id)
        historico(nomMarcaMovil,"El usuario eliminó la marca de móvil (ID = ${nomMarcaMovil.id}).","Sin acción")
        nomMarcaMovilService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomMarcaMovil.label', default: 'NomMarcaMovil'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomMarcaMovil.label', default: 'NomMarcaMovil'), params.id])
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
                        NomMarcaMovil.findByMarcaMovil(v.marcaMovil)?: new NomMarcaMovil(marcaMovil:v.marcaMovil).save(flush:true, failOnError:true)
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
        def products = NomMarcaMovil.list(params)
        def headers = ['marcaMovil']
        def withProperties = ['marcaMovil']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
