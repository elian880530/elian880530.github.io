package inventario

class NomUbicacion {

    String ubicacion

    static constraints = {
        ubicacion(size: 1..100, blank: false)
    }
    String toString() {
        return "${ubicacion}"
    }
}
