package incimed

class CausasError {
    String causasError

    static constraints = {
    }

    static mapping = {
        id column: 'causasError_id'
    }

    String toString() {
        return "${causasError}"
    }
}
