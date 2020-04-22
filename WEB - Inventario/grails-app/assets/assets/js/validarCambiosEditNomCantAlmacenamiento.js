
var varOriginalCantAlmacenamiento = document.getElementById("cantAlmacenamiento").value;
var varSelectCantAlmacenamiento = document.getElementById("cantAlmacenamiento").value;

$(document).ready(function() {

    $('#cantAlmacenamiento').on('change', function() {
        varSelectCantAlmacenamiento = document.getElementById("cantAlmacenamiento").value;
    });

});

function validacion(){

    if ((varOriginalCantAlmacenamiento == varSelectCantAlmacenamiento)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}