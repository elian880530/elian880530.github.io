package incimed

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import pl.touk.excel.export.WebXlsxExporter

import static org.springframework.http.HttpStatus.*

@Secured("ROLE_Z_INCIMED_ADMIN")
class NomCasaController {

    NomCasaService nomCasaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond nomCasaService.list(params), model: [nomCasaCount: nomCasaService.count()]
    }

    def show(Long id) {
        respond nomCasaService.get(id)
    }

    def create() {
        respond new NomCasa(params)
    }

    def save(NomCasa nomCasa) {
        if (nomCasa == null) {
            notFound()
            return
        }

        try {
            nomCasaService.save(nomCasa)
        } catch (ValidationException e) {
            respond nomCasa.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nomCasa.label', default: 'NomCasa'), nomCasa.id])
                redirect nomCasa
            }
            '*' { respond nomCasa, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nomCasaService.get(id)
    }

    def update(NomCasa nomCasa) {
        if (nomCasa == null) {
            notFound()
            return
        }

        try {
            nomCasaService.save(nomCasa)
        } catch (ValidationException e) {
            respond nomCasa.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nomCasa.label', default: 'NomCasa'), nomCasa.id])
                redirect nomCasa
            }
            '*' { respond nomCasa, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        nomCasaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nomCasa.label', default: 'NomCasa'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nomCasa.label', default: 'NomCasa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def downloadFile() {
        def products = NomCasa.list(params)
        def headers = [
                'id',
                'casa'
        ]

        def withProperties = [
                'id',
                'casa'
        ]
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

    def tablaCasaJson() {

        def jqdtParams = [:]

        params.each { key, value ->
            def keyFields = key.replace(']', '').split(/\[/)
            def table = jqdtParams
            for (int f = 0; f < keyFields.size() - 1; f++) {
                def keyField = keyFields[f]
                if (!table.containsKey(keyField))
                    table[keyField] = [:]
                table = table[keyField]
            }
            table[keyFields[-1]] = value
        }

        def columnMap = jqdtParams.columns.collectEntries { k, v ->
            def whereTerm = null
            switch (v.data) {
                case 'id':
                    if (v.search?.value ==~ /[0-9]+/)
                        whereTerm = v.search.value as Long
                    break
                default:
                    if (v.search?.value ==~ /[A-Za-z0-9]+/)
                        whereTerm = "%${v.search.value}%" as String
                    break
            }
            [(v.data): [where: whereTerm]]
        }

        def allColumnList = columnMap.keySet() as List

        def orderList = jqdtParams.order.collect { k, v -> [allColumnList[v.column as Integer], v.dir] }

        def filterer = {
            if (columnMap.id.where) inList 'id', columnMap.id.where
            if (columnMap.casa.where) ilike 'casa', columnMap.casa.where
        }

        def recordsTotal = NomCasa.count()

        def c = NomCasa.createCriteria()

        def recordsFiltered = c.count {
            filterer.delegate = delegate
            filterer()
        }

        def orderer = NomCasa.withCriteria {
            filterer.delegate = delegate
            filterer()
            orderList.each { oi ->
                switch (oi[0]) {
                    case 'id': order 'id', oi[1]; break
                    case 'casa': order 'casa', oi[1]; break
                }
            }
            maxResults(jqdtParams.length as Integer)
            firstResult(jqdtParams.start as Integer)
        }

        def casa = orderer.collect { casa ->

            def todasIncidencias = incimed.IncidenciaMedicacion?.all
            def asignado = 0
            def resultadoHTML = ""
            def listaCasasUsadas = []

            todasIncidencias.each {
                it.casa.each {
                    listaCasasUsadas.add(it)
                }
            }

            listaCasasUsadas.each {
                if (it.equals(casa)) {
                    asignado = 1
                }
            }

            if (asignado == 0) {
                resultadoHTML =
                        "<button type='button' class='eliminar' data-toggle='modal' data-target='#exampleModal${casa.id}'>\n" +
                                "\t<i class='glyphicon glyphicon-trash'></i>\n" +
                                "</button>\n" +
                                "<!-- Modal -->\n" +
                                "<div class='modal fade' id='exampleModal${casa.id}' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel${casa.id}' aria-hidden='true'>\n" +
                                "\t<div class='modal-dialog' role='document'>\n" +
                                "\t\t<div class='modal-content'>\n" +
                                "\t\t\t<div class='modal-header'>\n" +
                                "\t\t\t\t<h3 class='modal-title' id='exampleModalLabel'>\n" +
                                "\t\t\t\t\t<i class='glyphicon glyphicon-exclamation-sign'></i>\n" +
                                "\t\t\t\t\t<strong>Alerta</strong>\n" +
                                "\t\t\t\t\t<button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n" +
                                "\t\t\t\t\t\t<span aria-hidden='true'>\n" +
                                "\t\t\t\t\t\t\t×\n" +
                                "\t\t\t\t\t\t</span>\n" +
                                "\t\t\t\t\t</button>\n" +
                                "\t\t\t\t</h3>\n" +
                                "\t\t\t</div>\n" +
                                "\n" +
                                "\t\t\t<div class='modal-body'>\n" +
                                "\t\t\t\t<div class='alert alert-warning'>\n" +
                                "\t\t\t\t\t<strong>\n" +
                                "\t\t\t\t\t\t¿Está usted seguro que desea eliminar la casa con el identificador ${casa.id}?\n" +
                                "\t\t\t\t\t</strong>\n" +
                                "\t\t\t\t</div>\n" +
                                "\t\t\t</div>\n" +
                                "\n" +
                                "\t\t\t<div class='modal-footer'>\n" +
                                "\t\t\t\t<form action='/nomCasa/delete/${casa.id}' method='post'><input type='hidden' name='_method' value='DELETE' id='_method'>\n" +
                                "\t\t\t\t\t<button type='submit' class='btn btn-danger' name='delete.id' id='delete' value='Delete'>\n" +
                                "\t\t\t\t\t\t<i class='glyphicon glyphicon-trash'></i> Eliminar\n" +
                                "\t\t\t\t\t</button>\n" +
                                "\t\t\t\t</form>\n" +
                                "\t\t\t</div>\n" +
                                "\t\t</div>\n" +
                                "\t</div>\n" +
                                "</div>"
            } else {
                resultadoHTML =
                        "<button type='button' class='eliminar' disabled='disabled'>\n" +
                                "\t<i name='albatross' class='glyphicon glyphicon-trash albatross' data-toggle='tooltip' data-placement='right' title='Objeto Utilizado' style='color:red'></i>\n" +
                                "</button>"
            }

            ['id'      : casa.id,
             'casa'    : casa?.casa,
             'editar'  : "<a href='/nomCasa/edit/${casa.id}' class='edit'><i class='glyphicon glyphicon-pencil ieditar'></i></a>",
             'eliminar': resultadoHTML
            ]
        }

        def result = [draw: jqdtParams.draw, recordsTotal: recordsTotal, recordsFiltered: recordsFiltered, data: casa]
        render(result as JSON)

    }

}
