var varOriginalGravedadError = document.getElementById("gravedadError").value;
var varSelectGravedadError = document.getElementById("gravedadError").value;

$(document).ready(function () {

    $('#gravedadError').on('change', function () {
        varSelectGravedadError = document.getElementById("gravedadError").value;
    });

});

function validacion() {

    if ((varOriginalGravedadError == varSelectGravedadError)) {
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}