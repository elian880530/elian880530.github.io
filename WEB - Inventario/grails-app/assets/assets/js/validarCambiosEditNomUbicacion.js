
var varOriginalUbicacion = document.getElementById("ubicacion").value;
var varSelectUbicacion = document.getElementById("ubicacion").value;

$(document).ready(function() {

    $('#ubicacion').on('change', function() {
        varSelectUbicacion = document.getElementById("ubicacion").value;
    });

});

function validacion(){

    if ((varOriginalUbicacion == varSelectUbicacion)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}