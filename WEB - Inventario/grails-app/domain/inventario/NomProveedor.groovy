package inventario

class NomProveedor {

    String proveedor

    static constraints = {
        proveedor(size: 1..100, blank: false)
    }
    String toString() {
        return "${proveedor}"
    }
}
