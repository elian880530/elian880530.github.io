var varOriginalCasa = document.getElementById("casa").value;
var varSelectCasa = document.getElementById("casa").value;

$(document).ready(function () {

    $('#casa').on('change', function () {
        varSelectCasa = document.getElementById("casa").value;
    });

});

function validacion() {

    if ((varOriginalCasa == varSelectCasa)) {
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}