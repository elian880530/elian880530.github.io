package inventario

class NomMarcaTablet {

    String marcaTablet

    static constraints = {
        marcaTablet(size: 1..100, blank: false)
    }
    String toString() {
        return "${marcaTablet}"
    }
}
