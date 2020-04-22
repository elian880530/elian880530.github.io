var varOriginalMomentoDia = document.getElementById("momentoDia").value;
var varSelectMomentoDia = document.getElementById("momentoDia").value;

$(document).ready(function () {

    $('#momentoDia').on('change', function () {
        varSelectMomentoDia = document.getElementById("momentoDia").value;
    });

});

function validacion() {

    if ((varOriginalMomentoDia == varSelectMomentoDia)) {
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}