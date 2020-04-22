package inventario

class NomTipoMicro {

    String tipoMicro

    static constraints = {
        tipoMicro(size: 1..100, blank: false)
    }
    String toString() {
        return "${tipoMicro}"
    }
}
