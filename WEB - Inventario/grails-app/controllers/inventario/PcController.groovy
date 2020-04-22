package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class PcController {

    PcService pcService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond pcService.list(params), model:[pcCount: pcService.count()]
    }

    protected void historico(Pc pc, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "Pc",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: pc.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Pc pc){

        def pcOriginal = Pc.get(pc.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (pcOriginal.isDirty()) {
            pcOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${pcOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${pcOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la PC (ID = ${pc.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la PC (ID = ${pc.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(pc,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond pcService.get(id)
    }

    def create() {
        respond new Pc(params)
    }

    def save(Pc pc) {
        if (pc == null) {
            notFound()
            return
        }

        try {
            pcService.save(pc)
            historico(pc,"El usuario insertó una nueva PC (ID = ${pc.id}).","Sin acción")

        } catch (ValidationException e) {
            respond pc.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pc.label', default: 'Pc'), pc.id])
                redirect pc
            }
            '*' { respond pc, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pcService.get(id)
    }

    def update(Pc pc) {
        if (pc == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(pc)
            pcService.save(pc)

        } catch (ValidationException e) {
            respond pc.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pc.label', default: 'Pc'), pc.id])
                redirect pc
            }
            '*'{ respond pc, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Pc pc = pcService.get(id)
        historico(pc,"El usuario eliminó la PC (ID = ${pc.id}).","Sin acción")
        pcService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pc.label', default: 'Pc'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pc.label', default: 'Pc'), params.id])
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
                        Pc.findByPegatina(v.pegatina)?: new Pc(
                            pegatina:v.pegatina,
                            tipoMicro:v.tipoMicro,
                            cantRam:v.cantRam,
                            cantAlmacenamiento:v.cantAlmacenamiento,
                            ubicacion:v.ubicacion,
                            marcaPc:v.marcaPc,
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
        def products = Pc.list(params)
        def headers = ['tipoMicro','cantRam','cantAlmacenamiento','pegatina','ubicacion','marcaPc']
        def withProperties = ['tipoMicro','cantRam','cantAlmacenamiento','pegatina','ubicacion','marcaPc']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
