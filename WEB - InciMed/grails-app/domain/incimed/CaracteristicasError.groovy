package incimed

class CaracteristicasError {
    String caracteristicasError

    static constraints = {
    }

    static mapping = {
        id column: 'caracteristicasError_id'
    }

    String toString() {
        return "${caracteristicasError}"
    }
}
