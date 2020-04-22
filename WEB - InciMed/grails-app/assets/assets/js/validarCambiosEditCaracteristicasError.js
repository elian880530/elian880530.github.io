var varOriginalCaracteristicasError = document.getElementById("caracteristicasError").value;
var varSelectCaracteristicasError = document.getElementById("caracteristicasError").value;

$(document).ready(function () {

    $('#caracteristicasError').on('change', function () {
        varSelectCaracteristicasError = document.getElementById("caracteristicasError").value;
    });

});

function validacion() {

    if ((varOriginalCaracteristicasError == varSelectCaracteristicasError)) {
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}