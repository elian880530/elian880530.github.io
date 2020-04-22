package incimed

class IncidenciaMedicacion {

    Date fechaIncidenciaMedicacion

    boolean errorAlcanzoPersona

    String personaDetectoError
    String personaAfectada
    String nombreMedicamento
    String comoDescubierto
    String personalImplicado
    String relatoHechos
    String intervencionesRealizadas

    NomMomentoDia momentoDia
    NomCasa casa
    NomGravedadError gravedadError

    static hasMany = [caracteristicasError: CaracteristicasError, tipoError: TipoError, causasError: CausasError]

    static constraints = {

    }

    static mapping = {
        id column: 'incidenciaMedicacion_id'
    }
}
