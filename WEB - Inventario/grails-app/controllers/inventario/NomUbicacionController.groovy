package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomUbicacionController {

    NomUbicacionService nomUbicacionService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomUbicacionService.list(params), model:[nomUbicacionCount: nomUbicacionService.count()]
    }

    protected void historico(NomUbicacion nomUbicacion, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomUbicacion",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomUbicacion.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomUbicacion nomUbicacion){

        def nomUbicacionOriginal = NomUbicacion.get(nomUbicacion.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomUbicacionOriginal.isDirty()) {
            nomUbicacionOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomUbicacionOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomUbicacionOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la ubicación (ID = ${nomUbicacion.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la ubicación (ID = ${nomUbicacion.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomUbicacion,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomUbicacionService.get(id)
    }

    def create() {
        respond new NomUbicacion(params)
    }

    def save(NomUbicacion nomUbicacion) {
        if (nomUbicacion == null) {
            notFound()
            return
        }

        try {
            nomUbicacionService.save(nomUbicacion)
            historico(nomUbicacion,"El usuario insertó una nueva ubicación (ID = ${nomUbicacion.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomUbicacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomUbicacion.label', default: 'NomUbicacion'), nomUbicacion.id])
                redirect nomUbicacion
            }
            '*' { respond nomUbicacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomUbicacionService.get(id)
    }

    def update(NomUbicacion nomUbicacion) {
        if (nomUbicacion == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomUbicacion)
            nomUbicacionService.save(nomUbicacion)

        } catch (ValidationException e) {
            respond nomUbicacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomUbicacion.label', default: 'NomUbicacion'), nomUbicacion.id])
                redirect nomUbicacion
            }
            '*'{ respond nomUbicacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomUbicacion nomUbicacion = nomUbicacionService.get(id)
        historico(nomUbicacion,"El usuario eliminó la ubicación (ID = ${nomUbicacion.id}).","Sin acción")
        nomUbicacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomUbicacion.label', default: 'NomUbicacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomUbicacion.label', default: 'NomUbicacion'), params.id])
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
                        NomUbicacion.findByUbicacion(v.ubicacion)?: new NomUbicacion(ubicacion:v.ubicacion).save(flush:true, failOnError:true)
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
        def products = NomUbicacion.list(params)
        def headers = ['ubicacion']
        def withProperties = ['ubicacion']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
