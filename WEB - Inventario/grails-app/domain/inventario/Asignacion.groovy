package inventario

class Asignacion {

    Linea linea
    Persona persona
    Dispositivo dispositivo
    Movil movil
    Tablet tablet
    Pc pc
    Portatil portatil
    Date fechaAsignacion

    static constraints = {
        dispositivo nullable: true
        linea nullable: true
        movil nullable: true
        tablet nullable: true
        pc nullable: true
        portatil nullable: true
    }

    static mapping = {
        id column: 'asignacion_id'
    }



}
