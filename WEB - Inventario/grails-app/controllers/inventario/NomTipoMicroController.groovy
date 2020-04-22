package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomTipoMicroController {

    NomTipoMicroService nomTipoMicroService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomTipoMicroService.list(params), model:[nomTipoMicroCount: nomTipoMicroService.count()]
    }

    protected void historico(NomTipoMicro nomTipoMicro, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "NomTipoMicro",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomTipoMicro.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomTipoMicro nomTipoMicro){

        def nomTipoMicroOriginal = NomTipoMicro.get(nomTipoMicro.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomTipoMicroOriginal.isDirty()) {
            nomTipoMicroOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${nomTipoMicroOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomTipoMicroOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en el tipo de micro (ID = ${nomTipoMicro.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió al tipo de micro (ID = ${nomTipoMicro.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomTipoMicro,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond nomTipoMicroService.get(id)
    }

    def create() {
        respond new NomTipoMicro(params)
    }

    def save(NomTipoMicro nomTipoMicro) {
        if (nomTipoMicro == null) {
            notFound()
            return
        }

        try {
            nomTipoMicroService.save(nomTipoMicro)
            historico(nomTipoMicro,"El usuario insertó un nuevo tipo de micro (ID = ${nomTipoMicro.id}).","Sin acción")

        } catch (ValidationException e) {
            respond nomTipoMicro.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomTipoMicro.label', default: 'NomTipoMicro'), nomTipoMicro.id])
                redirect nomTipoMicro
            }
            '*' { respond nomTipoMicro, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomTipoMicroService.get(id)
    }

    def update(NomTipoMicro nomTipoMicro) {
        if (nomTipoMicro == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomTipoMicro)
            nomTipoMicroService.save(nomTipoMicro)

        } catch (ValidationException e) {
            respond nomTipoMicro.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomTipoMicro.label', default: 'NomTipoMicro'), nomTipoMicro.id])
                redirect nomTipoMicro
            }
            '*'{ respond nomTipoMicro, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomTipoMicro nomTipoMicro = nomTipoMicroService.get(id)
        historico(nomTipoMicro,"El usuario eliminó el tipo de micro (ID = ${nomTipoMicro.id}).","Sin acción")
        nomTipoMicroService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomTipoMicro.label', default: 'NomTipoMicro'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomTipoMicro.label', default: 'NomTipoMicro'), params.id])
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
                        NomTipoMicro.findByTipoMicro(v.tipoMicro)?: new NomTipoMicro(tipoMicro:v.tipoMicro).save(flush:true, failOnError:true)
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
        def products = NomTipoMicro.list(params)
        def headers = ['tipoMicro']
        def withProperties = ['tipoMicro']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}
