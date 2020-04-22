
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalPortatil = document.getElementById("c3APortatilSelect").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectPortatil = document.getElementById("c3APortatilSelect").value;
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
    $('#c3APortatilSelect').on('change', function() {
        varSelectPortatil = document.getElementById("c3APortatilSelect").value;
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
    varOculMenu = document.getElementById("oculMenuAsignacionPortatil").value;
    varSelectPc = document.getElementById("c2APortatilSelect").value;
    varSelectPortatil = document.getElementById("c3APortatilSelect").value;
    varSelectTablet = document.getElementById("c4APortatilSelect").value;
    varSelectLinea = document.getElementById("c5APortatilSelect").value;
    varSelectMovil = document.getElementById("c1APortatilSelect").value;

    if (varOculMenu == "c2APortatil" && varSelectPc == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Pc");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c3APortatil" && varSelectPortatil == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Portatil");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c4APortatil" && varSelectTablet == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Tablet o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c5APortatil" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Linea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c1APortatil" && varSelectMovil == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Movil o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if ((varOriginalPersona == varSelectPersona) &&
        (varOriginalPortatil == varSelectPortatil) &&
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
    oculta('c2APortatil');
    oculta('c1APortatil');
    oculta('c4APortatil');
    oculta('c5APortatil');
    /*Mostramos Portatil*/
    muestra('c3APortatil');
}

function oculta_selectAsignacionPortatil(selObj) {

    switch (selObj) {
        case "c2APortatil":
            //c2APortatil es pc
            oculta('c3APortatil');
            oculta('c1APortatil');
            oculta('c4APortatil');
            oculta('c5APortatil');
            muestra('c2APortatil');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c3APortatil":
            //c3APortatil es portatil
            oculta('c2APortatil');
            oculta('c1APortatil');
            oculta('c4APortatil');
            oculta('c5APortatil');
            muestra('c3APortatil');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1APortatil":
            //c1APortatil es movil
            oculta('c2APortatil');
            oculta('c3APortatil');
            oculta('c4APortatil');
            muestra('c1APortatil');
            elemtImpares('c5APortatil');
            muestra('panelbotones');
            $('select[name="tablet.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c4APortatil":
            //c4APortatil es tablet
            oculta('c2APortatil');
            oculta('c3APortatil');
            oculta('c1APortatil');
            muestra('c4APortatil');
            elemtImpares('c5APortatil');
            muestra('panelbotones');
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c5APortatil":
            //c5APortatil es linea
            oculta('c3APortatil');
            oculta('c1APortatil');
            oculta('c2APortatil');
            oculta('c4APortatil');
            muestra('c5APortatil');
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