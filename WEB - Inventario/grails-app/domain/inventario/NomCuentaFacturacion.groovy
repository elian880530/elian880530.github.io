package inventario

class NomCuentaFacturacion {

    String cuentaFacturacion

    static constraints = {
        cuentaFacturacion(size: 1..100, blank: false)
    }
    String toString() {
        return "${cuentaFacturacion}"
    }
}
