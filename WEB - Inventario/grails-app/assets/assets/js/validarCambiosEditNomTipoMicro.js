
var varOriginalTipoMicro = document.getElementById("tipoMicro").value;
var varSelectTipoMicro = document.getElementById("tipoMicro").value;

$(document).ready(function() {

    $('#tipoMicro').on('change', function() {
        varSelectTipoMicro = document.getElementById("tipoMicro").value;
    });

});

function validacion(){

    if ((varOriginalTipoMicro == varSelectTipoMicro)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}