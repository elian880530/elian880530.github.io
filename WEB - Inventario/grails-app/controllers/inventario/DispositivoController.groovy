package inventario

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class DispositivoController {

    DispositivoService dispositivoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond dispositivoService.list(params), model:[dispositivoCount: dispositivoService.count()]
    }

    def show(Long id) {
        respond dispositivoService.get(id)
    }

    def create() {
        respond new Dispositivo(params)
    }

    def save(Dispositivo dispositivo) {
        if (dispositivo == null) {
            notFound()
            return
        }

        try {
            dispositivoService.save(dispositivo)

        } catch (ValidationException e) {
            respond dispositivo.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), dispositivo.id])
                redirect dispositivo
            }
            '*' { respond dispositivo, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dispositivoService.get(id)
    }

    def update(Dispositivo dispositivo) {
        if (dispositivo == null) {
            notFound()
            return
        }

        try {
            dispositivoService.save(dispositivo)

        } catch (ValidationException e) {
            respond dispositivo.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), dispositivo.id])
                redirect dispositivo
            }
            '*'{ respond dispositivo, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dispositivoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dispositivo.label', default: 'Dispositivo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
