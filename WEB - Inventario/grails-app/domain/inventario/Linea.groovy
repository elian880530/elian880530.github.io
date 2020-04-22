package inventario

class Linea {

    String numSim
    String telefonoCorto
    String telefonoLargo
    String pin
    String puk
    String observaciones
    NomTarificacion tarificacion
    NomCuentaFacturacion cuentaFacturacion
    NomUbicacion ubicacion
    boolean lineaAsignada


    static constraints = {
        observaciones nullable: true
        lineaAsignada nullable: true
    }

    static mapping = {
        id column: 'linea_id'
    }
}
