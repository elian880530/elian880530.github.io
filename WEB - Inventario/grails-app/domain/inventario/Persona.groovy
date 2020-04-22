package inventario

class Persona {

    String docIdentificativo
    String nombre
    String apellido1
    String apellido2
    String correo
    String departamento
    int bajaID
    String employeeID

    static constraints = {
    }

    static mapping = {
        id column: 'persona_id'
    }

}
