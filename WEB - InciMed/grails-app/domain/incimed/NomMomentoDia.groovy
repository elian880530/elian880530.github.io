package incimed

class NomMomentoDia {
    String momentoDia

    static constraints = {
        momentoDia(size: 1..100, blank: false)
    }

    String toString() {
        return "${momentoDia}"
    }
}
