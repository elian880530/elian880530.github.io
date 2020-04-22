
var varOriginalCuentaFacturacion = document.getElementById("cuentaFacturacion").value;
var varSelectCuentaFacturacion = document.getElementById("cuentaFacturacion").value;

$(document).ready(function() {

    $('#cuentaFacturacion').on('change', function() {
        varSelectCuentaFacturacion = document.getElementById("cuentaFacturacion").value;
    });

});

function validacion(){

    if ((varOriginalCuentaFacturacion == varSelectCuentaFacturacion)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}