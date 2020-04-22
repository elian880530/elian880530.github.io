package inventario

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import groovy.sql.Sql

import static org.springframework.http.HttpStatus.*

@Secured("ROLE_Z_ITEMBOX_ADMIN")
class PersonaController {

    PersonaService personaService
    HistoricoService historicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max
        respond personaService.list(params), model:[personaCount: personaService.count()]
    }

    protected void historico(Persona persona, String accion, String accionSecundaria){

        Historico  hist = new Historico(
                objetoAuditado: "Persona",
                accion: accion,
                accionSecundaria: accionSecundaria,
                fechaSuceso: new Date(),
                usuarioEjecutadoAccion:  principal.username,
                idDispositivo: persona.id
        )
        historicoService.save(hist)
    }

    protected void historicoUpdate(Persona persona){

        def personaOriginal = Persona.get(persona.id)
        int varControl
        String mensaje1
        String varSingularPlural = "actualización"
        String mensaje2 = ""

        if (personaOriginal.isDirty()) {
            personaOriginal.dirtyPropertyNames.eachWithIndex { var, index ->
                mensaje2 += """${index + 1}. El atributo [ ${var} : ${personaOriginal.getPersistentValue(var)} ] fue actualizado con:\n """
                mensaje2 += """   Nuevo valor [ ${var} : ${personaOriginal.getProperty(var)} ]\n"""
                varControl = index + 1
                if (varControl >= 2){
                    varSingularPlural = "actualizaciones"
                }
            }

            mensaje1 = "El usuario realizó ${varControl} ${varSingularPlural} en la persona (ID = ${persona.id}):\n"

        }

        else {
            mensaje1 = "El usuario accedió a la persona (ID = ${persona.id}) sin realizar ninguna modificación en los atributos"
        }
        historico(persona,mensaje1,mensaje2)
    }

    def show(Long id) {
        respond personaService.get(id)
    }

    def create() {
        respond new Persona(params)
    }

    def save(Persona persona) {
        if (persona == null) {
            notFound()
            return
        }

        try {
            personaService.save(persona)
            historico(persona,"El usuario insertó una nueva persona (ID = ${persona.id}).","Sin acción")

        } catch (ValidationException e) {
            respond persona.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect persona
            }
            '*' { respond persona, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond personaService.get(id)
    }

    def update(Persona persona) {
        if (persona == null) {
            notFound()
            return
        }

        try {
            historicoUpdate(persona)
            personaService.save(persona)

        } catch (ValidationException e) {
            respond persona.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), persona.id])
                redirect persona
            }
            '*'{ respond persona, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Persona persona = personaService.get(id)
        historico(persona,"El usuario eliminó la persona (ID = ${persona.id}).","Sin acción")
        personaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
