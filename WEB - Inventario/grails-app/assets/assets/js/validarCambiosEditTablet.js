
var varOriginalMarcaTablet = document.getElementById("marcaTablet").value;
var varOriginalProveedor = document.getElementById("proveedor").value;
var varOriginalTipoMicro = document.getElementById("tipoMicro").value;
var varOriginalCantRam = document.getElementById("cantRam").value;
var varOriginalNumSerie = document.getElementById("numSerie").value;
var varOriginalImei = document.getElementById("imei").value;
var varOriginalFechaCompraDay = document.getElementById("fechaCompra_day").value;
var varOriginalFechaCompraMonth = document.getElementById("fechaCompra_month").value;
var varOriginalFechaCompraYear = document.getElementById("fechaCompra_year").value;

var varSelectMarcaTablet = document.getElementById("marcaTablet").value;
var varSelectProveedor = document.getElementById("proveedor").value;
var varSelectTipoMicro = document.getElementById("tipoMicro").value;
var varSelectCantRam = document.getElementById("cantRam").value;
var varSelectNumSerie = document.getElementById("numSerie").value;
var varSelectImei = document.getElementById("imei").value;
var varSelectFechaCompraDay = document.getElementById("fechaCompra_day").value;
var varSelectFechaCompraMonth = document.getElementById("fechaCompra_month").value;
var varSelectFechaCompraYear = document.getElementById("fechaCompra_year").value;

$(document).ready(function() {

    $('#marcaTablet').on('change', function() {
        varSelectMarcaTablet = document.getElementById("marcaTablet").value;
    });
    $('#proveedor').on('change', function() {
        varSelectProveedor = document.getElementById("proveedor").value;
    });
    $('#tipoMicro').on('change', function() {
        varSelectTipoMicro = document.getElementById("tipoMicro").value;
    });
    $('#cantRam').on('change', function() {
        varSelectCantRam = document.getElementById("cantRam").value;
    });
    $('#numSerie').on('change', function() {
        varSelectNumSerie = document.getElementById("numSerie").value;
    });
    $('#imei').on('change', function() {
        varSelectImei = document.getElementById("imei").value;
    });
    $('#FechaCompra_day').on('change', function() {
        varSelectFechaCompraDay = document.getElementById("fechaCompra_day").value;
    });
    $('#FechaCompra_month').on('change', function() {
        varSelectFechaCompraMonth = document.getElementById("fechaCompra_month").value;
    });
    $('#FechaCompra_year').on('change', function() {
        varSelectFechaCompraYear = document.getElementById("fechaCompra_year").value;
    });

});

function validacion(){

    if ((varOriginalMarcaTablet == varSelectMarcaTablet) &&
        (varOriginalProveedor == varSelectProveedor) &&
        (varOriginalTipoMicro == varSelectTipoMicro) &&
        (varOriginalCantRam == varSelectCantRam) &&
        (varOriginalNumSerie == varSelectNumSerie) &&
        (varOriginalImei == varSelectImei) &&
        (varOriginalFechaCompraDay == varSelectFechaCompraDay) &&
        (varOriginalFechaCompraMonth == varSelectFechaCompraMonth) &&
        (varOriginalFechaCompraYear == varSelectFechaCompraYear)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}