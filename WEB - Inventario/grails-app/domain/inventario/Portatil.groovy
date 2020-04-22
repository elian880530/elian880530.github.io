package inventario

class Portatil extends Dispositivo implements Serializable{

    NomTipoMicro tipoMicro
    NomCantRam cantRam
    NomCantAlmacenamiento cantAlmacenamiento
    String pegatina
    NomUbicacion ubicacion
    NomMarcaPortatil marcaPortatil

    static constraints = {
    }

    static mapping = {
        id column: 'portatil_id'
    }

}

