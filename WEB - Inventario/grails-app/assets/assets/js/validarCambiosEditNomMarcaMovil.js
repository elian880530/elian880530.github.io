
var varOriginalMarcaMovil = document.getElementById("marcaMovil").value;
var varSelectMarcaMovil = document.getElementById("marcaMovil").value;

$(document).ready(function() {

    $('#marcaMovil').on('change', function() {
        varSelectMarcaMovil = document.getElementById("marcaMovil").value;
    });

});

function validacion(){

    if ((varOriginalMarcaMovil == varSelectMarcaMovil)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}