
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalMovil = document.getElementById("c1AMovilSelect").value;
var varOriginalLinea = document.getElementById("c5AMovilSelect").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectMovil = document.getElementById("c1AMovilSelect").value;
var varSelectLinea = document.getElementById("c5AMovilSelect").value;
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
    $('#c1AMovilSelect').on('change', function() {
        varSelectMovil = document.getElementById("c1AMovilSelect").value;
    });
    $('#c5AMovilSelect').on('change', function() {
        varSelectLinea = document.getElementById("c5AMovilSelect").value;
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

window.onload = function () {/*hace que se cargue la función */
    /* "Mandamos como parametro el nombre de la Div para ocultar" */
    /*Ocultamos las otras*/
    oculta('c2AMovil');
    oculta('c4AMovil');
    oculta('c3AMovil');
    /*Mostramos Movil*/
    muestra('c1AMovil');
    muestra('c5AMovil');
}

function validacion(){
    varOculMenu = document.getElementById("oculMenuAsignacionMovil").value;
    varSelectPc = document.getElementById("c2AMovilSelect").value;
    varSelectPortatil = document.getElementById("c3AMovilSelect").value;
    varSelectTablet = document.getElementById("c4AMovilSelect").value;
    varSelectLinea = document.getElementById("c5AMovilSelect").value;
    varSelectMovil = document.getElementById("c1AMovilSelect").value;

    if (varOculMenu == "c2AMovil" && varSelectPc == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Pc");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c3AMovil" && varSelectPortatil == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Portatil");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c4AMovil" && varSelectTablet == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Tablet o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c5AMovil" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Linea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c1AMovil" && varSelectMovil == "" && varSelectLinea == ""){
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

function oculta_selectAsignacionMovil(selObj) {

    switch (selObj) {
        case "c2AMovil":
            //c2AMovil es pc
            oculta('c3AMovil');
            oculta('c1AMovil');
            oculta('c4AMovil');
            oculta('c5AMovil');
            muestra('c2AMovil');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c3AMovil":
            //c3AMovil es portatil
            oculta('c2AMovil');
            oculta('c1AMovil');
            oculta('c4AMovil');
            oculta('c5AMovil');
            muestra('c3AMovil');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1AMovil":
            //c2AMovil es movil
            oculta('c2AMovil');
            oculta('c3AMovil');
            oculta('c4AMovil');
            muestra('c1AMovil');
            elemtImpares('c5AMovil');
            muestra('panelbotones');
            $('select[name="tablet.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c4AMovil":
            //c2AMovil es tablet
            oculta('c2AMovil');
            oculta('c3AMovil');
            oculta('c1AMovil');
            muestra('c4AMovil');
            elemtImpares('c5AMovil');
            muestra('panelbotones');
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c5AMovil":
            //c2AMovil es linea
            oculta('c3AMovil');
            oculta('c1AMovil');
            oculta('c2AMovil');
            oculta('c4AMovil');
            muestra('c5AMovil');
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