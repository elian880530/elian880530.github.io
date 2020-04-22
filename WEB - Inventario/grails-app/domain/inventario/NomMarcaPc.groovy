package inventario

class NomMarcaPc {

    String marcaPc

    static constraints = {
        marcaPc(size: 1..100, blank: false)
    }
    String toString() {
        return "${marcaPc}"
    }
}
