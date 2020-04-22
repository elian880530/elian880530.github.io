package incimed

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import org.grails.datastore.mapping.query.Query
import pl.touk.excel.export.WebXlsxExporter

import static org.springframework.http.HttpStatus.*

@Secured("ROLE_Z_INCIMED_ADMIN")
class IncidenciaMedicacionController {

    IncidenciaMedicacionService incidenciaMedicacionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond incidenciaMedicacionService.list(params), model: [incidenciaMedicacionCount: incidenciaMedicacionService.count()]
    }

    def show(Long id) {
        respond incidenciaMedicacionService.get(id)
    }

    def create() {
        respond new IncidenciaMedicacion(params)
    }

    def save(IncidenciaMedicacion incidenciaMedicacion) {
        if (incidenciaMedicacion == null) {
            notFound()
            return
        }

        try {
            incidenciaMedicacionService.save(incidenciaMedicacion)
        } catch (ValidationException e) {
            respond incidenciaMedicacion.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'incidenciaMedicacion.label', default: 'IncidenciaMedicacion'), incidenciaMedicacion.id])
                redirect incidenciaMedicacion
            }
            '*' { respond incidenciaMedicacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond incidenciaMedicacionService.get(id)
    }

    def update(IncidenciaMedicacion incidenciaMedicacion) {
        if (incidenciaMedicacion == null) {
            notFound()
            return
        }

        try {
            incidenciaMedicacionService.save(incidenciaMedicacion)
        } catch (ValidationException e) {
            respond incidenciaMedicacion.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'incidenciaMedicacion.label', default: 'IncidenciaMedicacion'), incidenciaMedicacion.id])
                redirect incidenciaMedicacion
            }
            '*' { respond incidenciaMedicacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        incidenciaMedicacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'incidenciaMedicacion.label', default: 'IncidenciaMedicacion'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'incidenciaMedicacion.label', default: 'IncidenciaMedicacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def downloadFile() {
        def products = IncidenciaMedicacion.list(params)
        def headers = [
                'id',
                'fechaIncidenciaMedicacion',
                'personaDetectoError',
                'personaAfectada',
                'nombreMedicamento',
                'errorAlcanzoPersona',
                'momentoDia',
                'casa',
                'comoDescubierto',
                'personalImplicado',
                'relatoHechos',
                'intervencionesRealizadas',
                'gravedadError',
                'caracteristicasError',
                'tipoError',
                'causasError'
        ]

        def withProperties = [
                'id',
                'fechaIncidenciaMedicacion',
                'personaDetectoError',
                'personaAfectada',
                'nombreMedicamento',
                'errorAlcanzoPersona',
                'momentoDia',
                'casa',
                'comoDescubierto',
                'personalImplicado',
                'relatoHechos',
                'intervencionesRealizadas',
                'gravedadError',
                'caracteristicasError',
                'tipoError',
                'causasError'
        ]
        new WebXlsxExporter().with {
            setResponseHeaders(response)
            fillHeader(headers)
            add(products, withProperties)
            save(response.outputStream)
        }
    }

    def tablaIncidenciaJson() {

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
                case 'momentoDia':
                    whereTerm = NomMomentoDia.findAllByMomentoDiaLike("%${v.search.value}%")
                    break
                case 'casa':
                    whereTerm = NomCasa.findAllByCasaLike("%${v.search.value}%")
                    break
                case 'gravedadError':
                    whereTerm = NomGravedadError.findAllByGravedadErrorLike("%${v.search.value}%")
                    break

                /*
                case 'caracteristicasError':
                    def todasIncidencias = incimed.IncidenciaMedicacion?.all
                    def listaCaractErrorLike = CaracteristicasError.findAllByCaracteristicasErrorLike("%${v.search.value}%")
                    def listaResult = []

                    todasIncidencias.each { incid ->
                        incid.caracteristicasError.each { caract ->
                            listaCaractErrorLike.each { caractlike ->
                                println(v.search.value)
                                println(incid)
                                println(caract)
                                println(caractlike)
                                println("---------------")
                                if (caractlike == caract){
                                    listaResult.add(caract.id as Long)
                                    println(listaResult)
                                    println("---------------")
                                }
                            }
                        }
                    }
                    println("---------------")
                    println(listaResult)
                    println("---------------")
                    whereTerm = listaResult

                    //whereTerm = IncidenciaMedicacion.findAllByCaracteristicasErrorLike(listaResultInciden)
                    break

                case 'tipoError':
                    whereTerm = TipoError.findWhere(tipoError: v.search.value)
                    break

                case 'causasError':
                    whereTerm = CausasError.findWhere(causasError: v.search.value)
                    break
                */

                case 'id':
                    if (v.search?.value ==~ /[0-9]+/)
                        whereTerm = v.search.value as Long
                    break
                case 'errorAlcanzoPersona':
                    if (v.search?.value == "1") {
                        whereTerm = 1
                    } else if (v.search?.value == "0") {
                        whereTerm = 0
                    }
                    break
                case 'fechaIncidenciaMedicacion':
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
            if (columnMap.gravedadError.where) inList 'gravedadError', columnMap.gravedadError.where
            if (columnMap.momentoDia.where) inList 'momentoDia', columnMap.momentoDia.where
            if (columnMap.casa.where) inList 'casa', columnMap.casa.where
            if (columnMap.personaDetectoError.where) ilike 'personaDetectoError', columnMap.personaDetectoError.where
            if (columnMap.personaAfectada.where) ilike 'personaAfectada', columnMap.personaAfectada.where
            if (columnMap.nombreMedicamento.where) ilike 'nombreMedicamento', columnMap.nombreMedicamento.where
            if (columnMap.comoDescubierto.where) ilike 'comoDescubierto', columnMap.comoDescubierto.where
            if (columnMap.personalImplicado.where) ilike 'personalImplicado', columnMap.personalImplicado.where
            if (columnMap.relatoHechos.where) ilike 'relatoHechos', columnMap.relatoHechos.where
            if (columnMap.intervencionesRealizadas.where) ilike 'intervencionesRealizadas', columnMap.intervencionesRealizadas.where
            if (columnMap.errorAlcanzoPersona.where) ilike 'errorAlcanzoPersona', columnMap.errorAlcanzoPersona.where
            //if (columnMap.caracteristicasError.where) ilike 'caracteristicasError', columnMap.caracteristicasError.where
            //if (columnMap.tipoError.where) inList 'tipoError', columnMap.tipoError.where
            //if (columnMap.causasError.where) inList 'causasError', columnMap.causasError.where

            if (columnMap.fechaIncidenciaMedicacion.where) {
                if (columnMap.fechaIncidenciaMedicacion.where.size() > 1) {
                    or {
                        columnMap.fechaIncidenciaMedicacion.where.each {
                            between 'fechaIncidenciaMedicacion',
                                    Date.parse('yyyy/MM/dd hh:mm:ss', "${it}/01/01 hh:mm:ss" as String),
                                    Date.parse('yyyy/MM/dd hh:mm:ss', "${it}/12/31 hh:mm:ss" as String)
                        }
                    }
                } else {
                    between 'fechaIncidenciaMedicacion',
                            Date.parse('yyyy/MM/dd hh:mm:ss', "${columnMap.fechaIncidenciaMedicacion.where[0]}/01/01 01:59:59" as String),
                            Date.parse('yyyy/MM/dd hh:mm:ss', "${columnMap.fechaIncidenciaMedicacion.where[0]}/12/31 01:59:59" as String)
                }
            }

            //println(columnMap.caracteristicasError)

        }
        def recordsTotal = IncidenciaMedicacion.count()

        def c = IncidenciaMedicacion.createCriteria()
        def recordsFiltered = c.count {
            filterer.delegate = delegate
            filterer()
        }

        def orderer = IncidenciaMedicacion.withCriteria {
            filterer.delegate = delegate
            filterer()
            orderList.each { oi ->

                switch (oi[0]) {
                    case 'id': order 'id', oi[1]; break
                    case 'fechaIncidenciaMedicacion': order 'fechaIncidenciaMedicacion', oi[1]; break
                    case 'personaDetectoError': order 'personaDetectoError', oi[1]; break
                    case 'personaAfectada': order 'personaAfectada', oi[1]; break
                    case 'nombreMedicamento': order 'nombreMedicamento', oi[1]; break
                    case 'errorAlcanzoPersona': order 'errorAlcanzoPersona', oi[1]; break
                    case 'momentoDia': order 'momentoDia', oi[1]; break
                    case 'casa': order 'casa', oi[1]; break
                    case 'comoDescubierto': order 'comoDescubierto', oi[1]; break
                    case 'relatoHechos': order 'relatoHechos', oi[1]; break
                    case 'intervencionesRealizadas': order 'intervencionesRealizadas', oi[1]; break
                    case 'gravedadError': order 'gravedadError', oi[1]; break
                    case 'caracteristicasError': order 'caracteristicasError', oi[1]; break
                    case 'tipoError': order 'tipoError', oi[1]; break
                    case 'causasError': order 'causasError', oi[1]; break

                }
            }
            maxResults(jqdtParams.length as Integer)
            firstResult(jqdtParams.start as Integer)
        }

        def incidenciaMedicacion = orderer.collect { incidenciaMedicacion ->

            def resultadoHTML =
                    "<button type='button' class='eliminar' data-toggle='modal' data-target='#exampleModal${incidenciaMedicacion.id}'>\n" +
                            "\t<i class='glyphicon glyphicon-trash'></i>\n" +
                            "</button>\n" +
                            "<!-- Modal -->\n" +
                            "<div class='modal fade' id='exampleModal${incidenciaMedicacion.id}' tabindex='-1' role='dialog' aria-labelledby='exampleModalLabel${incidenciaMedicacion.id}' aria-hidden='true'>\n" +
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
                            "\t\t\t\t\t\t¿Está usted seguro que desea eliminar la incidencia con el identificador ${incidenciaMedicacion.id}?\n" +
                            "\t\t\t\t\t</strong>\n" +
                            "\t\t\t\t</div>\n" +
                            "\t\t\t</div>\n" +
                            "\n" +
                            "\t\t\t<div class='modal-footer'>\n" +
                            "\t\t\t\t<form action='/incidenciaMedicacion/delete/${incidenciaMedicacion.id}' method='post'><input type='hidden' name='_method' value='DELETE' id='_method'>\n" +
                            "\t\t\t\t\t<button type='submit' class='btn btn-danger' name='delete.id' id='delete' value='Delete'>\n" +
                            "\t\t\t\t\t\t<i class='glyphicon glyphicon-trash'></i> Eliminar\n" +
                            "\t\t\t\t\t</button>\n" +
                            "\t\t\t\t</form>\n" +
                            "\t\t\t</div>\n" +
                            "\t\t</div>\n" +
                            "\t</div>\n" +
                            "</div>"

            ['id'                       : incidenciaMedicacion.id,
             'fechaIncidenciaMedicacion': incidenciaMedicacion.fechaIncidenciaMedicacion.format('yyyy/MM/dd hh:mm:ss'),
             'personaDetectoError'      : incidenciaMedicacion?.personaDetectoError,
             'personaAfectada'          : incidenciaMedicacion?.personaAfectada,
             'nombreMedicamento'        : incidenciaMedicacion?.nombreMedicamento,
             'errorAlcanzoPersona'      : incidenciaMedicacion?.errorAlcanzoPersona,
             'comoDescubierto'          : incidenciaMedicacion?.comoDescubierto,
             'personalImplicado'        : incidenciaMedicacion?.personalImplicado,
             'relatoHechos'             : incidenciaMedicacion?.relatoHechos,
             'intervencionesRealizadas' : incidenciaMedicacion?.intervencionesRealizadas,
             'momentoDia'               : incidenciaMedicacion.momentoDia?.momentoDia,
             'casa'                     : incidenciaMedicacion.casa?.casa,
             'gravedadError'            : incidenciaMedicacion.gravedadError?.gravedadError,
             'caracteristicasError'     : incidenciaMedicacion.caracteristicasError?.caracteristicasError,
             'tipoError'                : incidenciaMedicacion.tipoError?.tipoError,
             'causasError'              : incidenciaMedicacion.causasError?.causasError,
             'editar'                   : "<a href='/incidenciaMedicacion/edit/${incidenciaMedicacion.id}' class='edit'><i class='glyphicon glyphicon-pencil ieditar'></i></a>",
             'eliminar'                 : resultadoHTML
            ]
        }

        def result = [draw: jqdtParams.draw, recordsTotal: recordsTotal, recordsFiltered: recordsFiltered, data: incidenciaMedicacion]
        render(result as JSON)

    }


}
