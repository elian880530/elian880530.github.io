package inventario

class Pc extends Dispositivo implements Serializable {

    NomTipoMicro tipoMicro
    NomCantRam cantRam
    NomCantAlmacenamiento cantAlmacenamiento
    String pegatina
    NomUbicacion ubicacion
    NomMarcaPc marcaPc

    static constraints = {
    }

    static mapping = {
        id column: 'pc_id'
    }

}
