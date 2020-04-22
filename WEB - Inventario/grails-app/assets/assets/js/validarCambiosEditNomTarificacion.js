
var varOriginalTarificacion = document.getElementById("tarificacion").value;
var varSelectTarificacion = document.getElementById("tarificacion").value;

$(document).ready(function() {

    $('#tarificacion').on('change', function() {
        varSelectTarificacion = document.getElementById("tarificacion").value;
    });

});

function validacion(){

    if ((varOriginalTarificacion == varSelectTarificacion)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}