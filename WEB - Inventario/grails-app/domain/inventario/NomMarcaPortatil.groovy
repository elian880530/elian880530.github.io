package inventario

class NomMarcaPortatil {

    String marcaPortatil

    static constraints = {
        marcaPortatil(size: 1..100, blank: false)
    }
    String toString() {
        return "${marcaPortatil}"
    }
}
