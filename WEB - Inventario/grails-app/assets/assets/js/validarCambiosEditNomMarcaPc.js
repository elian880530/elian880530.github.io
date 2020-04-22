
var varOriginalMarcaPc = document.getElementById("marcaPc").value;
var varSelectMarcaPc = document.getElementById("marcaPc").value;

$(document).ready(function() {

    $('#marcaPc').on('change', function() {
        varSelectMarcaPc = document.getElementById("marcaPc").value;
    });

});

function validacion(){

    if ((varOriginalMarcaPc == varSelectMarcaPc)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}