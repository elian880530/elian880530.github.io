package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class TabletController {

    TabletService tabletService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond tabletService.list(params), model:[tabletCount: tabletService.count()]
    }

    protected void historico(Tablet tablet, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "Tablet",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: tablet.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Tablet tablet){

        def tabletOriginal = Tablet.get(tablet.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (tabletOriginal.isDirty()) {
            tabletOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${tabletOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${tabletOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en el tablet (ID = ${tablet.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió al tablet (ID = ${tablet.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(tablet,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond tabletService.get(id)
    }

    def create() {
        respond new Tablet(params)
    }

    def save(Tablet tablet) {
        if (tablet == null) {
            notFound()
            return
        }

        try {
            tabletService.save(tablet)
            historico(tablet,"El usuario insertó un nuevo tablet (ID = ${tablet.id}).","Sin acción")

        } catch (ValidationException e) {
            respond tablet.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tablet.label', default: 'Tablet'), tablet.id])
                redirect tablet
            }
            '*' { respond tablet, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond tabletService.get(id)
    }

    def update(Tablet tablet) {
        if (tablet == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(tablet)
            tabletService.save(tablet)

        } catch (ValidationException e) {
            respond tablet.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tablet.label', default: 'Tablet'), tablet.id])
                redirect tablet
            }
            '*'{ respond tablet, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Tablet tablet = tabletService.get(id)
        historico(tablet,"El usuario eliminó el tablet (ID = ${tablet.id}).","Sin acción")
        tabletService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tablet.label', default: 'Tablet'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tablet.label', default: 'Tablet'), params.id])
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
                    String fechaExcel = v.fechaCompra
                    Date fechaLista = new Date().parse('dd/MM/yyyy', fechaExcel)

                    if(v) {
                        Tablet.findByImei(v.imei)?: new Tablet(
                                imei:v.imei,
                                tipoMicro:v.tipoMicro,
                                cantRam:v.cantRam,
                                cantAlmacenamiento:v.cantAlmacenamiento,
                                numSerie:v.numSerie,
                                marcaTablet:v.marcaTablet,
                                dispositivoAsignado:v.dispositivoAsignado,
                                fechaCompra:fechaLista,
                                proveedor:v.proveedor).save(flush:true, failOnError:true)
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
        def products = Tablet.list(params)
        def headers = ['imei','tipoMicro','cantRam','cantAlmacenamiento','numSerie','marcaTablet']
        def withProperties = ['imei','tipoMicro','cantRam','cantAlmacenamiento','numSerie','marcaTablet']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
