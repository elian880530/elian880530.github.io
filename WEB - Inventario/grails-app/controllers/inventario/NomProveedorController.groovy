package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomProveedorController {

    NomProveedorService nomProveedorService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomProveedorService.list(params), model:[nomProveedorCount: nomProveedorService.count()]
    }

    protected void historico(NomProveedor nomProveedor, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomProveedor",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomProveedor.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomProveedor nomProveedor){

        def nomProveedorOriginal = NomProveedor.get(nomProveedor.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomProveedorOriginal.isDirty()) {
            nomProveedorOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomProveedorOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomProveedorOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en el proveedor (ID = ${nomProveedor.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió al proveedor (ID = ${nomProveedor.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomProveedor,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomProveedorService.get(id)
    }

    def create() {
        respond new NomProveedor(params)
    }

    def save(NomProveedor nomProveedor) {
        if (nomProveedor == null) {
            notFound()
            return
        }

        try {
            nomProveedorService.save(nomProveedor)
            historico(nomProveedor,"El usuario insertó un nuevo proveedor (ID = ${nomProveedor.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomProveedor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomProveedor.label', default: 'NomProveedor'), nomProveedor.id])
                redirect nomProveedor
            }
            '*' { respond nomProveedor, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomProveedorService.get(id)
    }

    def update(NomProveedor nomProveedor) {
        if (nomProveedor == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomProveedor)
            nomProveedorService.save(nomProveedor)

        } catch (ValidationException e) {
            respond nomProveedor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomProveedor.label', default: 'NomProveedor'), nomProveedor.id])
                redirect nomProveedor
            }
            '*'{ respond nomProveedor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomProveedor nomProveedor = nomProveedorService.get(id)
        historico(nomProveedor,"El usuario eliminó el proveedor (ID = ${nomProveedor.id}).","Sin acción")
        nomProveedorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomProveedor.label', default: 'NomProveedor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomProveedor.label', default: 'NomProveedor'), params.id])
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
                        NomProveedor.findByProveedor(v.proveedor)?: new NomProveedor(proveedor:v.proveedor).save(flush:true, failOnError:true)
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
        def products = NomProveedor.list(params)
        def headers = ['proveedor']
        def withProperties = ['proveedor']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
