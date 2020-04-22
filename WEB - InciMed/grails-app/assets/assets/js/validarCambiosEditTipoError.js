var varOriginalTipoError = document.getElementById("tipoError").value;
var varSelectTipoError = document.getElementById("tipoError").value;

$(document).ready(function () {

    $('#tipoError').on('change', function () {
        varSelectTipoError = document.getElementById("tipoError").value;
    });

});

function validacion() {

    if ((varOriginalTipoError == varSelectTipoError)) {
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}