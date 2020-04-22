
var varOriginalNumSim = document.getElementById("numSim").value;
var varOriginalTelefonoCorto = document.getElementById("telefonoCorto").value;
var varOriginalTelefonoLargo = document.getElementById("telefonoLargo").value;
var varOriginalPin = document.getElementById("pin").value;
var varOriginalPuk = document.getElementById("puk").value;
var varOriginalObservaciones = document.getElementById("observaciones").value;
var varOriginalTarificacion = document.getElementById("tarificacion").value;
var varOriginalCuentaFacturacion = document.getElementById("cuentaFacturacion").value;
var varOriginalUbicacion = document.getElementById("ubicacion").value;

var varSelectNumSim = document.getElementById("numSim").value;
var varSelectTelefonoCorto = document.getElementById("telefonoCorto").value;
var varSelectTelefonoLargo = document.getElementById("telefonoLargo").value;
var varSelectPin = document.getElementById("pin").value;
var varSelectPuk = document.getElementById("puk").value;
var varSelectObservaciones = document.getElementById("observaciones").value;
var varSelectTarificacion  = document.getElementById("tarificacion").value;
var varSelectCuentaFacturacion = document.getElementById("cuentaFacturacion").value;
var varSelectUbicacion = document.getElementById("ubicacion").value;


$(document).ready(function() {

    $('#numSim').on('change', function() {
        varSelectNumSim = document.getElementById("numSim").value;
    });
    $('#telefonoCorto').on('change', function() {
        varSelectTelefonoCorto = document.getElementById("telefonoCorto").value;
    });
    $('#telefonoLargo').on('change', function() {
        varSelectTelefonoLargo = document.getElementById("telefonoLargo").value;
    });
    $('#pin').on('change', function() {
        varSelectPin = document.getElementById("pin").value;
    });
    $('#puk').on('change', function() {
        varSelectPuk = document.getElementById("puk").value;
    });
    $('#observaciones').on('change', function() {
        varSelectObservaciones = document.getElementById("observaciones").value;
    });
    $('#tarificacion').on('change', function() {
        varSelectTarificacion = document.getElementById("tarificacion").value;
    });
    $('#FechaCompra_day').on('change', function() {
        varSelectCuentaFacturacion = document.getElementById("cuentaFacturacion").value;
    });
    $('#FechaCompra_month').on('change', function() {
        varSelectUbicacion = document.getElementById("ubicacion").value;
    });

});

function validacion(){

    if ((varOriginalNumSim == varSelectNumSim) &&
        (varOriginalTelefonoCorto == varSelectTelefonoCorto) &&
        (varOriginalTelefonoLargo == varSelectTelefonoLargo) &&
        (varOriginalPin == varSelectPin) &&
        (varOriginalPuk == varSelectPuk) &&
        (varOriginalObservaciones == varSelectObservaciones) &&
        (varOriginalTarificacion == varSelectTarificacion) &&
        (varOriginalCuentaFacturacion == varSelectCuentaFacturacion) &&
        (varOriginalUbicacion == varSelectUbicacion)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}