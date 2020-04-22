
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalMovil = document.getElementById("casoLineaMovil").value;
var varOriginalTablet = document.getElementById("casoLineaTablet").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectMovil = document.getElementById("casoLineaMovil").value;
var varSelectTablet = document.getElementById("casoLineaTablet").value;
var varSelectFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varSelectFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varSelectFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

window.onload = function () {/*hace que se cargue la función */
    /* "Mandamos como parametro el nombre de la Div para ocultar" */
    /*Ocultamos Todos*/
    oculta('c1NMT');
    /*Ocultamos Todos*/
    oculta('c2NMT');
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
    $('#casoLineaTablet').on('change', function() {
        varSelectTablet = document.getElementById("casoLineaTablet").value;
    });
    $('#casoLineaMovil').on('change', function() {
        varSelectMovil = document.getElementById("casoLineaMovil").value;
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

function oculta_selectTabletMovilNinguno(selObj) {

    switch (selObj) {
        case "NMTSLM":
            //alert("Selecciona una opcion");
            oculta('c1NMT');
            oculta('c2NMT');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1NMT":
            //alert("Movil");
            muestra('c1NMT');
            oculta('c2NMT');
            $('select[name="tablet.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c2NMT":
            //alert("Tablet");
            muestra('c2NMT');
            oculta('c1NMT');
            $('select[name="movil.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        default:
            //alert("error, actualice la pagina");
    }

}