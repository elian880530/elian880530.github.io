package inventario

class NomCantRam {

    String cantRam

    static constraints = {
        cantRam(size: 1..100, blank: false)
    }
    String toString() {
        return "${cantRam}"
    }
}
