package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class LineaController {

    LineaService lineaService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond lineaService.list(params), model: [lineaCount: lineaService.count()]
    }

    protected void historico(Linea linea, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "Línea",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: linea.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Linea linea){

        def lineaOriginal = Linea.get(linea.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (lineaOriginal.isDirty()) {
            lineaOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${lineaOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${lineaOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la línea (ID = ${linea.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la línea (ID = ${linea.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(linea,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond lineaService.get(id)
    }

    def create() {
        respond new Linea(params)
    }

    def save(Linea linea) {
        if (linea == null) {
            notFound()
            return
        }

        try {
            lineaService.save(linea)
            historico(linea,"El usuario insertó una nueva línea (ID = ${linea.id}).","Sin acción")

        } catch (ValidationException e) {
            respond linea.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'linea.label', default: 'Linea'), linea.id])
                redirect linea
            }
            '*' { respond linea, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond lineaService.get(id)
    }

    def update(Linea linea) {
        if (linea == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(linea)
            lineaService.save(linea)

        } catch (ValidationException e) {
            respond linea.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'linea.label', default: 'Linea'), linea.id])
                redirect linea
            }
            '*' { respond linea, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Linea linea = lineaService.get(id)
        historico(linea,"El usuario eliminó la línea (ID = ${linea.id}).","Sin acción")
        lineaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'linea.label', default: 'Linea'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'linea.label', default: 'Linea'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
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
                        Linea.findByNumSim(v.numSim)?: new Linea(
                                numSim:v.numSim,
                                telefonoCorto:v.telefonoCorto,
                                telefonoLargo:v.telefonoLargo,
                                pin:v.pin,puk:v.puk,
                                observaciones:v.observaciones,
                                tarificacion:v.tarificacion,
                                cuentaFacturacion:v.cuentaFacturacion,
                                ubicacion:v.ubicacion,
                                lineaAsignada:v.lineaAsignada).save(flush:true, failOnError:true)
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
        def products = Linea.list(params)
        def headers = ['numSim','telefonoCorto','telefonoLargo','pin','puk','observaciones','tarificacion','cuentaFacturacion','ubicacion','lineaAsignada']
        def withProperties = ['numSim','telefonoCorto','telefonoLargo','pin','puk','observaciones','tarificacion','cuentaFacturacion','ubicacion','lineaAsignada']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
