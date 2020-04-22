package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomTarificacionController {

    NomTarificacionService nomTarificacionService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomTarificacionService.list(params), model:[nomTarificacionCount: nomTarificacionService.count()]
    }

    protected void historico(NomTarificacion nomTarificacion, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomTarificacion",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomTarificacion.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomTarificacion nomTarificacion){

        def nomTarificacionOriginal = NomTarificacion.get(nomTarificacion.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomTarificacionOriginal.isDirty()) {
            nomTarificacionOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomTarificacionOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomTarificacionOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la tarifa (ID = ${nomTarificacion.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la tarifa (ID = ${nomTarificacion.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomTarificacion,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomTarificacionService.get(id)
    }

    def create() {
        respond new NomTarificacion(params)
    }

    def save(NomTarificacion nomTarificacion) {
        if (nomTarificacion == null) {
            notFound()
            return
        }

        try {
            nomTarificacionService.save(nomTarificacion)
            historico(nomTarificacion,"El usuario insertó una nueva tarifa (ID = ${nomTarificacion.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomTarificacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomTarificacion.label', default: 'NomTarificacion'), nomTarificacion.id])
                redirect nomTarificacion
            }
            '*' { respond nomTarificacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomTarificacionService.get(id)
    }

    def update(NomTarificacion nomTarificacion) {
        if (nomTarificacion == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomTarificacion)
            nomTarificacionService.save(nomTarificacion)

        } catch (ValidationException e) {
            respond nomTarificacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomTarificacion.label', default: 'NomTarificacion'), nomTarificacion.id])
                redirect nomTarificacion
            }
            '*'{ respond nomTarificacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomTarificacion nomTarificacion = nomTarificacionService.get(id)
        historico(nomTarificacion,"El usuario eliminó la tarifa (ID = ${nomTarificacion.id}).","Sin acción")
        nomTarificacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomTarificacion.label', default: 'NomTarificacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomTarificacion.label', default: 'NomTarificacion'), params.id])
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
                        NomTarificacion.findByTarificacion(v.tarificacion)?: new NomTarificacion(tarificacion:v.tarificacion).save(flush:true, failOnError:true)
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
        def products = NomTarificacion.list(params)
        def headers = ['tarificacion']
        def withProperties = ['tarificacion']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
