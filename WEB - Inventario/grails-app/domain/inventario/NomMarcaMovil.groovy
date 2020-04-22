package inventario

class NomMarcaMovil {

    String marcaMovil

    static constraints = {
        marcaMovil(size: 1..100, blank: false)
    }
    String toString() {
        return "${marcaMovil}"
    }
}
