package inventario

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class AsignacionController {

    AsignacionService asignacionService
    HistoricoService historicoService
    PersonaService personaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond asignacionService.list(params), model:[asignacionCount: asignacionService.count()]
    }

    protected void historico(Asignacion asignacion, String accion, String accionSecundaria){

        Historico  hist = new Historico(
            objetoAuditado: "Asignación",
            accion: accion,
            accionSecundaria: accionSecundaria,
            fechaSuceso: new Date(),
            usuarioEjecutadoAccion: principal.username,
            idAsignacion: asignacion.id,
            idUsuario: asignacion.personaId,
            idDispositivo: asignacion.dispositivoId,
            idLinea: asignacion.lineaId,
            idMovil: asignacion.movilId,
            idPc: asignacion.pcId,
            idPortatil: asignacion.portatilId,
            idTablet: asignacion.tabletId
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Asignacion asignacion){

        def asignacionOriginal = Asignacion.get(asignacion.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (asignacionOriginal.isDirty()) {
            asignacionOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${asignacionOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${asignacionOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la asignación (ID = ${asignacion.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la asignación (ID = ${asignacion.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(asignacion,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond asignacionService.get(id)
    }

    def create() {
        respond new Asignacion(params)
    }

    def save(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        if (!Objects.equals(null, asignacion.tablet?.id)) {
            asignacion.tablet?.dispositivoAsignado = true
        }
        if (!Objects.equals(null, asignacion.pc?.id)) {
            asignacion.pc?.dispositivoAsignado = true
        }
        if (!Objects.equals(null, asignacion.movil?.id)) {
            asignacion.movil?.dispositivoAsignado = true
        }
        if (!Objects.equals(null, asignacion.portatil?.id)) {
            asignacion.portatil?.dispositivoAsignado = true
        }
        if (!Objects.equals(null, asignacion.linea?.id)) {
            asignacion.linea?.lineaAsignada = true
        }

        try {
            asignacionService.save(asignacion)
            historico(asignacion,"El usuario insertó una nueva asignación (ID = ${asignacion.id}).","Sin acción")

        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect asignacion
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def saveMovil(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacion.movil?.dispositivoAsignado = true
            asignacionService.save(asignacion)
            historico(asignacion,"El usuario insertó una nueva asignación (ID = ${asignacion.id}).","Sin acción")
        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect(controller:"movil", action: "index")
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def saveTablet(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacion.tablet?.dispositivoAsignado = true
            asignacionService.save(asignacion)
            historico(asignacion,"El usuario insertó una nueva asignación (ID = ${asignacion.id}).","Sin acción")
        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect(controller:"tablet", action: "index")
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def savePc(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacion.pc?.dispositivoAsignado = true
            asignacionService.save(asignacion)
            historico(asignacion,"El usuario insertó una nueva asignación (ID = ${asignacion.id}).","Sin acción")
        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect(controller:"pc", action: "index")
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def savePortatil(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacion.portatil?.dispositivoAsignado = true
            asignacionService.save(asignacion)
            historico(asignacion,"El usuario insertó una nueva asignación (ID = ${asignacion.id}).","Sin acción")
        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect(controller:"portatil", action: "index")
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def saveLinea(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            asignacion.linea?.lineaAsignada = true
            asignacionService.save(asignacion)
            historico(asignacion,"El usuario insertó una nueva asignación (ID = ${asignacion.id}).","Sin acción")
        } catch (ValidationException e) {
            respond asignacion.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect(controller:"linea", action: "index")
            }
            '*' { respond asignacion, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond asignacionService.get(id)
    }

    def update(Asignacion asignacion) {
        if (asignacion == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(asignacion)
            asignacionService.save(asignacion)

        } catch (ValidationException e) {
            respond asignacion.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), asignacion.id])
                redirect asignacion
            }
            '*'{ respond asignacion, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Asignacion asignacion = asignacionService.get(id)

        if (!Objects.equals(null, asignacion.tablet?.id)) {
            asignacion.tablet?.dispositivoAsignado = false
        }
        if (!Objects.equals(null, asignacion.pc?.id)) {
            asignacion.pc?.dispositivoAsignado = false
        }
        if (!Objects.equals(null, asignacion.movil?.id)) {
            asignacion.movil?.dispositivoAsignado = false
        }
        if (!Objects.equals(null, asignacion.portatil?.id)) {
            asignacion.portatil?.dispositivoAsignado = false
        }
        if (!Objects.equals(null, asignacion.linea?.id)) {
            asignacion.linea?.lineaAsignada = false
        }

        historico(asignacion,"El usuario eliminó la asignación (ID = ${asignacion.id}).","Sin acción")
        asignacionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'asignacion.label', default: 'Asignacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
