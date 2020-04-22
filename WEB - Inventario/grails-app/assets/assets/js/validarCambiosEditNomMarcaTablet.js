
var varOriginalMarcaTablet = document.getElementById("marcaTablet").value;
var varSelectMarcaTablet = document.getElementById("marcaTablet").value;

$(document).ready(function() {

    $('#marcaTablet').on('change', function() {
        varSelectMarcaTablet = document.getElementById("marcaTablet").value;
    });

});

function validacion(){

    if ((varOriginalMarcaTablet == varSelectMarcaTablet)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}