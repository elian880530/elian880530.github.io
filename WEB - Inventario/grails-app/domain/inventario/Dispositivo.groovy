package inventario

class Dispositivo {

    boolean dispositivoAsignado
    Date fechaCompra
    NomProveedor proveedor

    static constraints = {
        dispositivoAsignado nullable: true
    }

    static mapping = {
        tablePerHierarchy false
        id column: 'dispositivo_id'
    }

}