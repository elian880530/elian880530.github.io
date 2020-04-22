package inventario

import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter
import static org.springframework.http.HttpStatus.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class NomCantAlmacenamientoController {

    NomCantAlmacenamientoService nomCantAlmacenamientoService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomCantAlmacenamientoService.list(params), model: [nomCantAlmacenamientoCount: nomCantAlmacenamientoService.count()]
    }

    protected void historico(NomCantAlmacenamiento nomCantAlmacenamiento, String accion, String accionSecundaria) {

        Historico hist = new Historico(
                objetoAuditado: "NomCantAlmacenamiento",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion: principal.username,
                idDispositivo: nomCantAlmacenamiento.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(NomCantAlmacenamiento nomCantAlmacenamiento) {

        def nomCantAlmacenamientoOriginal = NomCantAlmacenamiento.get(nomCantAlmacenamiento.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (nomCantAlmacenamientoOriginal.isDirty()) {
            nomCantAlmacenamientoOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${
                    nomCantAlmacenamientoOriginal.getPersistentValue(var)
                } ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${nomCantAlmacenamientoOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2) {
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la cantidad de almacenamiento (ID = ${nomCantAlmacenamiento.id}):\n"

        } else {
            mensaje1 = "El usuario accedió a la cantidad de almacenamiento (ID = ${nomCantAlmacenamiento.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(nomCantAlmacenamiento, mensaje1, mensaje2)
    }

    def show(Long id) {
        respond nomCantAlmacenamientoService.get(id)
    }

    def create() {
        respond new NomCantAlmacenamiento(params)
    }

    def save(NomCantAlmacenamiento nomCantAlmacenamiento) {
        if (nomCantAlmacenamiento == null) {
            notFound()
            return
        }

        try {
            nomCantAlmacenamientoService.save(nomCantAlmacenamiento)
            historico(nomCantAlmacenamiento, "El usuario insertó una nueva cantidad de almacenamiento (ID = ${nomCantAlmacenamiento.id}).", "Sin acción")

        } catch (ValidationException e) {
            respond nomCantAlmacenamiento.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomCantAlmacenamiento.label', default: 'NomCantAlmacenamiento'), nomCantAlmacenamiento.id])
                redirect nomCantAlmacenamiento
            }
            '*' { respond nomCantAlmacenamiento, [status: CREATED] }
        }


    }

    def edit(Long id) {
        respond nomCantAlmacenamientoService.get(id)
    }

    def update(NomCantAlmacenamiento nomCantAlmacenamiento) {
        if (nomCantAlmacenamiento == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(nomCantAlmacenamiento)
            nomCantAlmacenamientoService.save(nomCantAlmacenamiento)

        } catch (ValidationException e) {
            respond nomCantAlmacenamiento.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomCantAlmacenamiento.label', default: 'NomCantAlmacenamiento'), nomCantAlmacenamiento.id])
                redirect nomCantAlmacenamiento
            }
            '*' { respond nomCantAlmacenamiento, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        NomCantAlmacenamiento nomCantAlmacenamiento = nomCantAlmacenamientoService.get(id)
        historico(nomCantAlmacenamiento, "El usuario eliminó la cantidad de almacenamiento (ID = ${nomCantAlmacenamiento.id}).", "Sin acción")
        nomCantAlmacenamientoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomCantAlmacenamiento.label', default: 'NomCantAlmacenamiento'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomCantAlmacenamiento.label', default: 'NomCantAlmacenamiento'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def uploadFile() {
        def file = request.getFile('excelFile')
        if (!file.empty) {
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
                    switch (cell.cellType) {
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
                    if (v) {
                        NomCantAlmacenamiento.findByCantAlmacenamiento(v.cantAlmacenamiento) ?: new NomCantAlmacenamiento(cantAlmacenamiento: v.cantAlmacenamiento).save(flush: true, failOnError: true)
                        flash.message = "1"
                    }
                }
                catch (ValidationException e) {
                    flash.message = "0"
                    return
                }
            }
            redirect action: "index"
        }
    }

    def downloadFile(){
        def products = NomCantAlmacenamiento.list(params)
        def headers = ['cantAlmacenamiento']
        def withProperties = ['cantAlmacenamiento']
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

}