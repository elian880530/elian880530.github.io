package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomMarcaPcController {

    NomMarcaPcService nomMarcaPcService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomMarcaPcService.list(params), model:[nomMarcaPcCount: nomMarcaPcService.count()]
    }

    protected void historico(NomMarcaPc nomMarcaPc, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomMarcaPc",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomMarcaPc.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomMarcaPc nomMarcaPc){

        def nomMarcaPcOriginal = NomMarcaPc.get(nomMarcaPc.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomMarcaPcOriginal.isDirty()) {
            nomMarcaPcOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomMarcaPcOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomMarcaPcOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la marca de PC (ID = ${nomMarcaPc.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la marca de PC (ID = ${nomMarcaPc.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomMarcaPc,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomMarcaPcService.get(id)
    }

    def create() {
        respond new NomMarcaPc(params)
    }

    def save(NomMarcaPc nomMarcaPc) {
        if (nomMarcaPc == null) {
            notFound()
            return
        }

        try {
            nomMarcaPcService.save(nomMarcaPc)
            historico(nomMarcaPc,"El usuario insertó una nueva marca de PC (ID = ${nomMarcaPc.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomMarcaPc.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomMarcaPc.label', default: 'NomMarcaPc'), nomMarcaPc.id])
                redirect nomMarcaPc
            }
            '*' { respond nomMarcaPc, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomMarcaPcService.get(id)
    }

    def update(NomMarcaPc nomMarcaPc) {
        if (nomMarcaPc == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomMarcaPc)
            nomMarcaPcService.save(nomMarcaPc)

        } catch (ValidationException e) {
            respond nomMarcaPc.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomMarcaPc.label', default: 'NomMarcaPc'), nomMarcaPc.id])
                redirect nomMarcaPc
            }
            '*'{ respond nomMarcaPc, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomMarcaPc nomMarcaPc = nomMarcaPcService.get(id)
        historico(nomMarcaPc,"El usuario eliminó la marca de PC (ID = ${nomMarcaPc.id}).","Sin acción")
        nomMarcaPcService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomMarcaPc.label', default: 'NomMarcaPc'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomMarcaPc.label', default: 'NomMarcaPc'), params.id])
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
                        NomMarcaPc.findByMarcaPc(v.marcaPc)?: new NomMarcaPc(marcaPc:v.marcaPc).save(flush:true, failOnError:true)
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
        def products = NomMarcaPc.list(params)
        def headers = ['marcaPc']
        def withProperties = ['marcaPc']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
