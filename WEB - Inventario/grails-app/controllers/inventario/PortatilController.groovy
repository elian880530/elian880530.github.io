package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class PortatilController {

    PortatilService portatilService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond portatilService.list(params), model:[portatilCount: portatilService.count()]
    }

    protected void historico(Portatil portatil, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "Portatil",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: portatil.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Portatil portatil){

        def portatilOriginal = Portatil.get(portatil.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (portatilOriginal.isDirty()) {
            portatilOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${portatilOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${portatilOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en el portátil (ID = ${portatil.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió al portátil (ID = ${portatil.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(portatil,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond portatilService.get(id)
    }

    def create() {
        respond new Portatil(params)
    }

    def save(Portatil portatil) {
        if (portatil == null) {
            notFound()
            return
        }

        try {
            portatilService.save(portatil)
            historico(portatil,"El usuario insertó un nuevo portátil (ID = ${portatil.id}).","Sin acción")

        } catch (ValidationException e) {
            respond portatil.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'portatil.label', default: 'Portatil'), portatil.id])
                redirect portatil
            }
            '*' { respond portatil, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond portatilService.get(id)
    }

    def update(Portatil portatil) {
        if (portatil == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(portatil)
            portatilService.save(portatil)

        } catch (ValidationException e) {
            respond portatil.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'portatil.label', default: 'Portatil'), portatil.id])
                redirect portatil
            }
            '*'{ respond portatil, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Portatil portatil = portatilService.get(id)
        historico(portatil,"El usuario eliminó el portatil (ID = ${portatil.id}).","Sin acción")
        portatilService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'portatil.label', default: 'Portatil'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'portatil.label', default: 'Portatil'), params.id])
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
                        Portatil.findByPegatina(v.pegatina)?: new Portatil(
                                pegatina:v.pegatina,
                                tipoMicro:v.tipoMicro,
                                cantRam:v.cantRam,
                                cantAlmacenamiento:v.cantAlmacenamiento,
                                ubicacion:v.ubicacion,
                                marcaPortatil:v.marcaPortatil,
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
        def products = Portatil.list(params)
        def headers = ['tipoMicro','cantRam','cantAlmacenamiento','pegatina','ubicacion','marcaPortatil']
        def withProperties = ['tipoMicro','cantRam','cantAlmacenamiento','pegatina','ubicacion','marcaPortatil']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
