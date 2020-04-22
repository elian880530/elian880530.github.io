
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalMovil = document.getElementById("c1AMovilLineaSelect").value;
var varOriginalLinea = document.getElementById("c5AMovilLineaSelect").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectMovil = document.getElementById("c1AMovilLineaSelect").value;
var varSelectLinea = document.getElementById("c5AMovilLineaSelect").value;
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
    $('#c1AMovilLineaSelect').on('change', function() {
        varSelectMovil = document.getElementById("c1AMovilLineaSelect").value;
    });
    $('#c5AMovilLineaSelect').on('change', function() {
        varSelectLinea = document.getElementById("c5AMovilLineaSelect").value;
    });

});

function oculta(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'none'; //damos un atributo display:none que oculta el div
}

function muestra(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'inline-block';//damos un atributo display:block que muestra  el div
}

function elemtImpares(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'block';//damos un atributo display:block que muestra  el div
}

function validacion(){
    varOculMenu = document.getElementById("oculMenuAsignacionMovilLinea").value;
    varSelectPc = document.getElementById("c2AMovilLineaSelect").value;
    varSelectPortatil = document.getElementById("c3AMovilLineaSelect").value;
    varSelectMovil = document.getElementById("c4AMovilLineaSelect").value;
    varSelectLinea = document.getElementById("c5AMovilLineaSelect").value;
    varSelectMovil = document.getElementById("c1AMovilLineaSelect").value;

    if (varOculMenu == "c2AMovilLinea" && varSelectPc == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Pc");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c3AMovilLinea" && varSelectPortatil == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Portatil");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c4AMovilLinea" && varSelectTablet == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Tablet o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c5AMovilLinea" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Linea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c1AMovilLinea" && varSelectMovil == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Movil o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if ((varOriginalPersona == varSelectPersona) &&
        (varOriginalMovil == varSelectMovil) &&
        (varOriginalLinea == varSelectLinea) &&
        (varOriginalFechaAsignacionDay == varSelectFechaAsignacionDay) &&
        (varOriginalFechaAsignacionMonth == varSelectFechaAsignacionMonth) &&
        (varOriginalFechaAsignacionYear == varSelectFechaAsignacionYear)){
        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    return true;
}

window.onload = function () {/*hace que se cargue la función */
    /* "Mandamos como parametro el nombre de la Div para ocultar" */
    /*Ocultamos las otras*/
    oculta('c2AMovilLinea');
    oculta('c4AMovilLinea');
    oculta('c3AMovilLinea');
    /*Mostramos MovilLinea*/
    muestra('c1AMovilLinea');
    muestra('c5AMovilLinea');
}

function oculta_selectAsignacionMovilLinea(selObj) {

    switch (selObj) {
        case "c2AMovilLinea":
            //c2AMovilLinea es pc
            oculta('c3AMovilLinea');
            oculta('c1AMovilLinea');
            oculta('c4AMovilLinea');
            oculta('c5AMovilLinea');
            muestra('c2AMovilLinea');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c3AMovilLinea":
            //c3AMovilLinea es portatil
            oculta('c2AMovilLinea');
            oculta('c1AMovilLinea');
            oculta('c4AMovilLinea');
            oculta('c5AMovilLinea');
            muestra('c3AMovilLinea');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1AMovilLinea":
            //c1AMovilLinea es movil
            oculta('c2AMovilLinea');
            oculta('c3AMovilLinea');
            oculta('c4AMovilLinea');
            muestra('c1AMovilLinea');
            elemtImpares('c5AMovilLinea');
            muestra('panelbotones');
            $('select[name="tablet.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c4AMovilLinea":
            //c4AMovilLinea es tablet
            oculta('c2AMovilLinea');
            oculta('c3AMovilLinea');
            oculta('c1AMovilLinea');
            muestra('c4AMovilLinea');
            elemtImpares('c5AMovilLinea');
            muestra('panelbotones');
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c5AMovilLinea":
            //c5AMovilLinea es linea
            oculta('c3AMovilLinea');
            oculta('c1AMovilLinea');
            oculta('c2AMovilLinea');
            oculta('c4AMovilLinea');
            muestra('c5AMovilLinea');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        default:
            //alert("error, actualice la pagina");

    }

}