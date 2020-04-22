
var varOriginalMarcaPortatil = document.getElementById("marcaPortatil").value;
var varSelectMarcaPortatil = document.getElementById("marcaPortatil").value;

$(document).ready(function() {

    $('#marcaPortatil').on('change', function() {
        varSelectMarcaPortatil = document.getElementById("marcaPortatil").value;
    });

});

function validacion(){

    if ((varOriginalMarcaPortatil == varSelectMarcaPortatil)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}