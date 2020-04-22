
var varOriginalCantRam = document.getElementById("cantRam").value;
var varSelectCantRam = document.getElementById("cantRam").value;

$(document).ready(function() {

    $('#cantRam').on('change', function() {
        varSelectCantRam = document.getElementById("cantRam").value;
    });

});

function validacion(){

    if ((varOriginalCantRam == varSelectCantRam)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}