var varOriginalCausasError = document.getElementById("causasError").value;
var varSelectCausasError = document.getElementById("causasError").value;

$(document).ready(function () {

    $('#causasError').on('change', function () {
        varSelectCausasError = document.getElementById("causasError").value;
    });

});

function validacion() {

    if ((varOriginalCausasError == varSelectCausasError)) {
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}