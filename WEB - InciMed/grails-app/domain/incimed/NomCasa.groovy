package incimed

class NomCasa {

    String casa

    static constraints = {
        casa(size: 1..100, blank: false)
    }

    String toString() {
        return "${casa}"
    }
}
