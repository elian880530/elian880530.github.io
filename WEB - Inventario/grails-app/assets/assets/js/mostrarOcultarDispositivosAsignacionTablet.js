
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalTablet = document.getElementById("c4ATabletSelect").value;
var varOriginalLinea = document.getElementById("c5ATabletSelect").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectTablet = document.getElementById("c4ATabletSelect").value;
var varSelectLinea = document.getElementById("c5ATabletSelect").value;
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
    $('#c4ATabletSelect').on('change', function() {
        varSelectTablet = document.getElementById("c4ATabletSelect").value;
    });
    $('#c5ATabletSelect').on('change', function() {
        varSelectLinea = document.getElementById("c5ATabletSelect").value;
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
    varOculMenu = document.getElementById("oculMenuAsignacionTablet").value;
    varSelectPc = document.getElementById("c2ATabletSelect").value;
    varSelectPortatil = document.getElementById("c3ATabletSelect").value;
    varSelectTablet = document.getElementById("c4ATabletSelect").value;
    varSelectLinea = document.getElementById("c5ATabletSelect").value;
    varSelectMovil = document.getElementById("c1ATabletSelect").value;

    if (varOculMenu == "c2ATablet" && varSelectPc == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Pc");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c3ATablet" && varSelectPortatil == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Portatil");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c4ATablet" && varSelectTablet == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Tablet o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c5ATablet" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Linea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c1ATablet" && varSelectMovil == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Movil o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if ((varOriginalPersona == varSelectPersona) &&
        (varOriginalTablet == varSelectTablet) &&
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
    oculta('c2ATablet');
    oculta('c1ATablet');
    oculta('c3ATablet');
    /*Mostramos Tablet*/
    muestra('c4ATablet');
    muestra('c5ATablet');
}

function oculta_selectAsignacionTablet(selObj) {

    switch (selObj) {
        case "c2ATablet":
            //c2ATablet es pc
            oculta('c3ATablet');
            oculta('c1ATablet');
            oculta('c4ATablet');
            oculta('c5ATablet');
            muestra('c2ATablet');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c3ATablet":
            //c3ATablet es portatil
            oculta('c2ATablet');
            oculta('c1ATablet');
            oculta('c4ATablet');
            oculta('c5ATablet');
            muestra('c3ATablet');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1ATablet":
            //c2ATablet es movil
            oculta('c2ATablet');
            oculta('c3ATablet');
            oculta('c4ATablet');
            muestra('c1ATablet');
            elemtImpares('c5ATablet');
            muestra('panelbotones');
            $('select[name="tablet.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c4ATablet":
            //c2ATablet es tablet
            oculta('c2ATablet');
            oculta('c3ATablet');
            oculta('c1ATablet');
            muestra('c4ATablet');
            elemtImpares('c5ATablet');
            muestra('panelbotones');
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c5ATablet":
            //c2ATablet es linea
            oculta('c3ATablet');
            oculta('c1ATablet');
            oculta('c2ATablet');
            oculta('c4ATablet');
            muestra('c5ATablet');
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