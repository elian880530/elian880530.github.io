package incimed

class NomGravedadError {

    String gravedadError

    static constraints = {
        gravedadError(size: 1..100, blank: false)
    }

    String toString() {
        return "${gravedadError}"
    }
}
