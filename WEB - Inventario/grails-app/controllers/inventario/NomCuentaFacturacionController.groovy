package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomCuentaFacturacionController {

    NomCuentaFacturacionService nomCuentaFacturacionService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomCuentaFacturacionService.list(params), model:[nomCuentaFacturacionCount: nomCuentaFacturacionService.count()]
    }

    protected void historico(NomCuentaFacturacion nomCuentaFacturacion, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomCuentaFacturacion",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomCuentaFacturacion.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomCuentaFacturacion nomCuentaFacturacion){

        def nomCuentaFacturacionOriginal = NomCuentaFacturacion.get(nomCuentaFacturacion.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomCuentaFacturacionOriginal.isDirty()) {
            nomCuentaFacturacionOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomCuentaFacturacionOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomCuentaFacturacionOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la cuenta de facturación (ID = ${nomCuentaFacturacion.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la cuenta de facturación (ID = ${nomCuentaFacturacion.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomCuentaFacturacion,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomCuentaFacturacionService.get(id)
    }

    def create() {
        respond new NomCuentaFacturacion(params)
    }

    def save(NomCuentaFacturacion nomCuentaFacturacion) {
        if (nomCuentaFacturacion == null) {
            notFound()
            return
        }

        try {
            nomCuentaFacturacionService.save(nomCuentaFacturacion)
            historico(nomCuentaFacturacion,"El usuario insertó una nueva cuenta de facturación (ID = ${nomCuentaFacturacion.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomCuentaFacturacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomCuentaFacturacion.label', default: 'NomCuentaFacturacion'), nomCuentaFacturacion.id])
                redirect nomCuentaFacturacion
            }
            '*' { respond nomCuentaFacturacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomCuentaFacturacionService.get(id)
    }

    def update(NomCuentaFacturacion nomCuentaFacturacion) {
        if (nomCuentaFacturacion == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomCuentaFacturacion)
            nomCuentaFacturacionService.save(nomCuentaFacturacion)

        } catch (ValidationException e) {
            respond nomCuentaFacturacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomCuentaFacturacion.label', default: 'NomCuentaFacturacion'), nomCuentaFacturacion.id])
                redirect nomCuentaFacturacion
            }
            '*'{ respond nomCuentaFacturacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomCuentaFacturacion nomCuentaFacturacion = nomCuentaFacturacionService.get(id)
        historico(nomCuentaFacturacion,"El usuario eliminó la cuenta de facturación (ID = ${nomCuentaFacturacion.id}).","Sin acción")
        nomCuentaFacturacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomCuentaFacturacion.label', default: 'NomCuentaFacturacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomCuentaFacturacion.label', default: 'NomCuentaFacturacion'), params.id])
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
                        NomCuentaFacturacion.findByCuentaFacturacion(v.cuentaFacturacion)?: new NomCuentaFacturacion(cuentaFacturacion:v.cuentaFacturacion).save(flush:true, failOnError:true)
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
        def products = NomCuentaFacturacion.list(params)
        def headers = ['cuentaFacturacion']
        def withProperties = ['cuentaFacturacion']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
