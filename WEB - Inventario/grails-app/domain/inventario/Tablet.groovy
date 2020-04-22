package inventario

class Tablet extends Dispositivo implements Serializable {

    String imei
    NomTipoMicro tipoMicro
    NomCantRam cantRam
    NomCantAlmacenamiento cantAlmacenamiento
    String numSerie
    NomMarcaTablet marcaTablet

    static constraints = {
        imei nullable: true
    }

    static mapping = {
        id column: 'tablet_id'
    }
}
