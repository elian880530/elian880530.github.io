
var varOriginalProveedor = document.getElementById("proveedor").value;
var varSelectProveedor = document.getElementById("proveedor").value;

$(document).ready(function() {

    $('#proveedor').on('change', function() {
        varSelectProveedor = document.getElementById("proveedor").value;
    });

});

function validacion(){

    if ((varOriginalProveedor == varSelectProveedor)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}