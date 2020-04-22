package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomCantRamController {

    NomCantRamService nomCantRamService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomCantRamService.list(params), model:[nomCantRamCount: nomCantRamService.count()]
    }

    protected void historico(NomCantRam nomCantRam, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomCantRam",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomCantRam.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomCantRam nomCantRam){

        def nomCantRamOriginal = NomCantRam.get(nomCantRam.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomCantRamOriginal.isDirty()) {
            nomCantRamOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomCantRamOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomCantRamOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la cantidad de RAM (ID = ${nomCantRam.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la cantidad de RAM (ID = ${nomCantRam.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomCantRam,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomCantRamService.get(id)
    }

    def create() {
        respond new NomCantRam(params)
    }

    def save(NomCantRam nomCantRam) {
        if (nomCantRam == null) {
            notFound()
            return
        }

        try {
            nomCantRamService.save(nomCantRam)
            historico(nomCantRam,"El usuario insertó una nueva cantidad de RAM (ID = ${nomCantRam.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomCantRam.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomCantRam.label', default: 'NomCantRam'), nomCantRam.id])
                redirect nomCantRam
            }
            '*' { respond nomCantRam, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomCantRamService.get(id)
    }

    def update(NomCantRam nomCantRam) {
        if (nomCantRam == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomCantRam)
            nomCantRamService.save(nomCantRam)

        } catch (ValidationException e) {
            respond nomCantRam.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomCantRam.label', default: 'NomCantRam'), nomCantRam.id])
                redirect nomCantRam
            }
            '*'{ respond nomCantRam, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomCantRam nomCantRam = nomCantRamService.get(id)
        historico(nomCantRam,"El usuario eliminó la cantidad de RAM (ID = ${nomCantRam.id}).","Sin acción")
        nomCantRamService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomCantRam.label', default: 'NomCantRam'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomCantRam.label', default: 'NomCantRam'), params.id])
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
                        NomCantRam.findByCantRam(v.cantRam)?: new NomCantRam(cantRam:v.cantRam).save(flush:true, failOnError:true)
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
        def products = NomCantRam.list(params)
        def headers = ['cantRam']
        def withProperties = ['cantRam']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
