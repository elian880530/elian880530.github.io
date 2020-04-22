package incimed

class TipoError {
    String tipoError

    static constraints = {
    }

    static mapping = {
        id column: 'tipoError_id'
    }

    String toString() {
        return "${tipoError}"
    }
}
