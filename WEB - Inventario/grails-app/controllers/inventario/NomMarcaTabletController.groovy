package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomMarcaTabletController {

    NomMarcaTabletService nomMarcaTabletService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomMarcaTabletService.list(params), model:[nomMarcaTabletCount: nomMarcaTabletService.count()]
    }

    protected void historico(NomMarcaTablet nomMarcaTablet, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomMarcaTablet",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomMarcaTablet.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomMarcaTablet nomMarcaTablet){

        def nomMarcaTabletOriginal = NomMarcaTablet.get(nomMarcaTablet.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomMarcaTabletOriginal.isDirty()) {
            nomMarcaTabletOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomMarcaTabletOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomMarcaTabletOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la marca de tablet (ID = ${nomMarcaTablet.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la marca de tablet (ID = ${nomMarcaTablet.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomMarcaTablet,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomMarcaTabletService.get(id)
    }

    def create() {
        respond new NomMarcaTablet(params)
    }

    def save(NomMarcaTablet nomMarcaTablet) {
        if (nomMarcaTablet == null) {
            notFound()
            return
        }

        try {
            nomMarcaTabletService.save(nomMarcaTablet)
            historico(nomMarcaTablet,"El usuario insertó una nueva marca de tablet (ID = ${nomMarcaTablet.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomMarcaTablet.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomMarcaTablet.label', default: 'NomMarcaTablet'), nomMarcaTablet.id])
                redirect nomMarcaTablet
            }
            '*' { respond nomMarcaTablet, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomMarcaTabletService.get(id)
    }

    def update(NomMarcaTablet nomMarcaTablet) {
        if (nomMarcaTablet == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomMarcaTablet)
            nomMarcaTabletService.save(nomMarcaTablet)

        } catch (ValidationException e) {
            respond nomMarcaTablet.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomMarcaTablet.label', default: 'NomMarcaTablet'), nomMarcaTablet.id])
                redirect nomMarcaTablet
            }
            '*'{ respond nomMarcaTablet, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomMarcaTablet nomMarcaTablet = nomMarcaTabletService.get(id)
        historico(nomMarcaTablet,"El usuario eliminó la marca de tablet (ID = ${nomMarcaTablet.id}).","Sin acción")
        nomMarcaTabletService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomMarcaTablet.label', default: 'NomMarcaTablet'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomMarcaTablet.label', default: 'NomMarcaTablet'), params.id])
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
                        NomMarcaTablet.findByMarcaTablet(v.marcaTablet)?: new NomMarcaTablet(marcaTablet:v.marcaTablet).save(flush:true, failOnError:true)
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
        def products = NomMarcaTablet.list(params)
        def headers = ['marcaMarcaTablet']
        def withProperties = ['marcaMarcaTablet']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
