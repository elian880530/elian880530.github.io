package inventario

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class HistoricoController {

    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond historicoService.list(params), model:[historicoCount: historicoService.count()]
    }

    def show(Long id) {
        respond historicoService.get(id)
    }

    def create() {
        respond new Historico(params)
    }

    def save(Historico historico) {
        if (historico == null) {
            notFound()
            return
        }

        try {
            historicoService.save(historico)
        } catch (ValidationException e) {
            respond historico.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'historico.label', default: 'Historico'), historico.id])
                redirect historico
            }
            '*' { respond historico, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond historicoService.get(id)
    }

    def update(Historico historico) {
        if (historico == null) {
            notFound()
            return
        }

        try {
            historicoService.save(historico)
        } catch (ValidationException e) {
            respond historico.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'historico.label', default: 'Historico'), historico.id])
                redirect historico
            }
            '*'{ respond historico, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        historicoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'historico.label', default: 'Historico'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'historico.label', default: 'Historico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
