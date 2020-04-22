package inventario

class NomTarificacion {

    String tarificacion

    static constraints = {
        tarificacion(size: 1..100, blank: false)
    }
    String toString() {
        return "${tarificacion}"
    }
}
