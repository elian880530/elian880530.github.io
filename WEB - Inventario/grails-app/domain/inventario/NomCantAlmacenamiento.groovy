package inventario

class NomCantAlmacenamiento {

    String cantAlmacenamiento

    static constraints = {
        cantAlmacenamiento(size: 1..100, blank: false)
    }
    String toString() {
        return "${cantAlmacenamiento}"
    }
}
