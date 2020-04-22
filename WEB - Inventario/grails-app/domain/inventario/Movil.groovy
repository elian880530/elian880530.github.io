package inventario

class Movil extends Dispositivo implements Serializable {

    String imei
    NomTipoMicro tipoMicro
    NomCantRam cantRam
    NomCantAlmacenamiento cantAlmacenamiento
    String numSerie
    NomMarcaMovil marcaMovil

    static constraints = {
    }

    static mapping = {
        id column: 'movil_id'
    }

}
