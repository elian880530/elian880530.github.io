
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalMovil = document.getElementById("casoMovilMovil").value;
var varOriginalTablet = document.getElementById("casoMovilTablet").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectMovil = document.getElementById("casoMovilMovil").value;
var varSelectTablet = document.getElementById("casoMovilTablet").value;
var varSelectFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varSelectFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varSelectFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

window.onload = function () {/*hace que se cargue la función */
    /* "Mandamos como parametro el nombre de la Div para ocultar" */
    /*Ocultamos Tablet*/
    oculta('c2M');
    /*Mostramos Movil*/
    muestra('c1M');
}

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
    $('#casoMovilTablet').on('change', function() {
        varSelectTablet = document.getElementById("casoMovilTablet").value;
    });
    $('#casoMovilMovil').on('change', function() {
        varSelectMovil = document.getElementById("casoMovilMovil").value;
    });

});

function validacion(){

    if ((varOriginalPersona == varSelectPersona) &&
        (varOriginalMovil == varSelectMovil) &&
        (varOriginalTablet == varSelectTablet) &&
        (varOriginalFechaAsignacionDay == varSelectFechaAsignacionDay) &&
        (varOriginalFechaAsignacionMonth == varSelectFechaAsignacionMonth) &&
        (varOriginalFechaAsignacionYear == varSelectFechaAsignacionYear)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}

function oculta(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'none'; //damos un atributo display:none que oculta el div
}

function muestra(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'block';//damos un atributo display:block que muestra  el div
}

function oculta_selectMovil(selObj) {

    switch (selObj) {
        case "SLM":
            //alert("Ninguno");
            oculta('c1M');
            oculta('c2M');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1M":
            //alert("Movil");
            muestra('c1M');
            oculta('c2M');
            $('select[name="tablet.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c2M":
            //alert("Tablet");
            muestra('c2M');
            oculta('c1M');
            $('select[name="movil.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        default:
            //alert("error, actualice la pagina");
    }

}