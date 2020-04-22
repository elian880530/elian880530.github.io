
var varOriginalMarcaPortatil = document.getElementById("marcaPortatil").value;
var varOriginalProveedor = document.getElementById("proveedor").value;
var varOriginalTipoMicro = document.getElementById("tipoMicro").value;
var varOriginalCantRam = document.getElementById("cantRam").value;
var varOriginalCantAlmacenamiento = document.getElementById("cantAlmacenamiento").value;
var varOriginalPegatina = document.getElementById("pegatina").value;
var varOriginalUbicacion = document.getElementById("ubicacion").value;
var varOriginalFechaCompraDay = document.getElementById("fechaCompra_day").value;
var varOriginalFechaCompraMonth = document.getElementById("fechaCompra_month").value;
var varOriginalFechaCompraYear = document.getElementById("fechaCompra_year").value;

var varSelectMarcaPortatil = document.getElementById("marcaPortatil").value;
var varSelectProveedor = document.getElementById("proveedor").value;
var varSelectTipoMicro = document.getElementById("tipoMicro").value;
var varSelectCantRam = document.getElementById("cantRam").value;
var varSelectCantAlmacenamiento = document.getElementById("cantAlmacenamiento").value;
var varSelectPegatina = document.getElementById("pegatina").value;
var varSelectUbicacion = document.getElementById("ubicacion").value;
var varSelectFechaCompraDay = document.getElementById("fechaCompra_day").value;
var varSelectFechaCompraMonth = document.getElementById("fechaCompra_month").value;
var varSelectFechaCompraYear = document.getElementById("fechaCompra_year").value;

$(document).ready(function() {

    $('#marcaPortatil').on('change', function() {
        varSelectMarcaPortatil = document.getElementById("marcaPortatil").value;
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
    $('#cantAlmacenamiento').on('change', function() {
        varSelectCantAlmacenamiento = document.getElementById("cantAlmacenamiento").value;
    });
    $('#pegatina').on('change', function() {
        varSelectPegatina = document.getElementById("pegatina").value;
    });
    $('#ubicacion').on('change', function() {
        varSelectUbicacion = document.getElementById("ubicacion").value;
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

    if ((varOriginalMarcaPortatil == varSelectMarcaPortatil) &&
        (varOriginalProveedor == varSelectProveedor) &&
        (varOriginalTipoMicro == varSelectTipoMicro) &&
        (varOriginalCantRam == varSelectCantRam) &&
        (varOriginalCantAlmacenamiento == varSelectCantAlmacenamiento) &&
        (varOriginalPegatina == varSelectPegatina) &&
        (varOriginalUbicacion == varSelectUbicacion) &&
        (varOriginalFechaCompraDay == varSelectFechaCompraDay) &&
        (varOriginalFechaCompraMonth == varSelectFechaCompraMonth) &&
        (varOriginalFechaCompraYear == varSelectFechaCompraYear)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}