
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varSelectFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varSelectFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

$(document).ready(function() {

    $('#persona').on('change', function() {
        varSelectPersona = document.getElementById("persona").value;
    });
    $('#fechaAsignacion_day').on('change', function() {
        varSelectFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
    });
    $('#fechaAsignacion_month').on('change', function() {
        varSelectFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
    });
    $('#fechaAsignacion_year').on('change', function() {
        varSelectFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;
    });

});

function validacion(){

    if ((varOriginalPersona == varSelectPersona) &&
        (varOriginalFechaAsignacionDay == varSelectFechaAsignacionDay) &&
        (varOriginalFechaAsignacionMonth == varSelectFechaAsignacionMonth) &&
        (varOriginalFechaAsignacionYear == varSelectFechaAsignacionYear)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}