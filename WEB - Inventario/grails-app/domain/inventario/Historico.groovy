package inventario

class Historico {

    String objetoAuditado
    String accion
    String accionSecundaria
    Date fechaSuceso
    String usuarioEjecutadoAccion

    Long idAsignacion
    Long idUsuario
    Long idDispositivo
    Long idLinea
    Long idMovil
    Long idPc
    Long idPortatil
    Long idTablet


    static constraints = {

         objetoAuditado nullable: true
         idAsignacion nullable: true
         idUsuario nullable: true
         idDispositivo nullable: true
         idLinea nullable: true
         idMovil nullable: true
         idPc nullable: true
         idPortatil nullable: true
         idTablet nullable: true
    }

    static mapping = {
        id column: 'historico_id'
    }

}
