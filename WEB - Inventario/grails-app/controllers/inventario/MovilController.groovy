package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class MovilController {

    MovilService movilService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond movilService.list(params), model:[movilCount: movilService.count()]
    }

    protected void historico(Movil movil, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "Movil",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: movil.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Movil movil){

        def movilOriginal = Movil.get(movil.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (movilOriginal.isDirty()) {
            movilOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${movilOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${movilOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }
            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en el móvil (ID = ${movil.id}):\n"
        }

        else {
            mensaje1 = "El usuario accedió al móvil (ID = ${movil.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(movil,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond movilService.get(id)
    }

    def create() {
        respond new Movil(params)
    }

    def save(Movil movil) {
        if (movil == null) {
            notFound()
            return
        }

        try {
            movilService.save(movil)
            historico(movil,"El usuario insertó un nuevo móvil (ID = ${movil.id}).","Sin acción")

        } catch (ValidationException e) {
            respond movil.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'movil.label', default: 'Movil'), movil.id])
                redirect movil
            }
            '*' { respond movil, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond movilService.get(id)
    }

    def update(Movil movil) {
        if (movil == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(movil)
            movilService.save(movil)

        } catch (ValidationException e) {
            respond movil.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'movil.label', default: 'Movil'), movil.id])
                redirect movil
            }
            '*'{ respond movil, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Movil movil = movilService.get(id)
        historico(movil,"El usuario eliminó el móvil (ID = ${movil.id}).","Sin acción")
        movilService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'movil.label', default: 'Movil'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'movil.label', default: 'Movil'), params.id])
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
                        Movil.findByImei(v.imei)?: new Movil(
                                imei:v.imei,
                                tipoMicro:v.tipoMicro,
                                cantRam:v.cantRam,
                                cantAlmacenamiento:v.cantAlmacenamiento,
                                numSerie:v.numSerie,
                                marcaMovil:v.marcaMovil,
                                dispositivoAsignado:v.dispositivoAsignado,
                                fechaCompra:fechaLista ,
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
        def products = Movil.list(params)
        def headers = ['imei','tipoMicro','cantRam','cantAlmacenamiento','numSerie','marcaMovil']
        def withProperties = ['imei','tipoMicro','cantRam','cantAlmacenamiento','numSerie','marcaMovil']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
