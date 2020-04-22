
var varOriginalPersona = document.getElementById("persona").value;
var varOriginalTablet = document.getElementById("c4ATabletLineaSelect").value;
var varOriginalLinea = document.getElementById("c5ATabletLineaSelect").value;
var varOriginalFechaAsignacionDay = document.getElementById("fechaAsignacion_day").value;
var varOriginalFechaAsignacionMonth = document.getElementById("fechaAsignacion_month").value;
var varOriginalFechaAsignacionYear = document.getElementById("fechaAsignacion_year").value;

var varSelectPersona = document.getElementById("persona").value;
var varSelectTablet = document.getElementById("c4ATabletLineaSelect").value;
var varSelectLinea = document.getElementById("c5ATabletLineaSelect").value;
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
    $('#c4ATabletLineaSelect').on('change', function() {
        varSelectTablet = document.getElementById("c4ATabletLineaSelect").value;
    });
    $('#c5ATabletLineaSelect').on('change', function() {
        varSelectLinea = document.getElementById("c5ATabletLineaSelect").value;
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
    varOculMenu = document.getElementById("oculMenuAsignacionTabletLinea").value;
    varSelectPc = document.getElementById("c2ATabletLineaSelect").value;
    varSelectPortatil = document.getElementById("c3ATabletLineaSelect").value;
    varSelectTablet = document.getElementById("c4ATabletLineaSelect").value;
    varSelectLinea = document.getElementById("c5ATabletLineaSelect").value;
    varSelectMovil = document.getElementById("c1ATabletLineaSelect").value;

    if (varOculMenu == "c2ATabletLinea" && varSelectPc == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Pc");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c3ATabletLinea" && varSelectPortatil == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Portatil");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c4ATabletLinea" && varSelectTablet == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Tablet o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c5ATabletLinea" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Linea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c1ATabletLinea" && varSelectMovil == "" && varSelectLinea == ""){
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
    oculta('c2ATabletLinea');
    oculta('c1ATabletLinea');
    oculta('c3ATabletLinea');
    /*Mostramos TabletLinea*/
    muestra('c4ATabletLinea');
    muestra('c5ATabletLinea');
}

function oculta_selectAsignacionTabletLinea(selObj) {

    switch (selObj) {
        case "c2ATabletLinea":
            //c2ATabletLinea es pc
            oculta('c3ATabletLinea');
            oculta('c1ATabletLinea');
            oculta('c4ATabletLinea');
            oculta('c5ATabletLinea');
            muestra('c2ATabletLinea');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c3ATabletLinea":
            //c3ATabletLinea es portatil
            oculta('c2ATabletLinea');
            oculta('c1ATabletLinea');
            oculta('c4ATabletLinea');
            oculta('c5ATabletLinea');
            muestra('c3ATabletLinea');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1ATabletLinea":
            //c1ATabletLinea es movil
            oculta('c2ATabletLinea');
            oculta('c3ATabletLinea');
            oculta('c4ATabletLinea');
            muestra('c1ATabletLinea');
            elemtImpares('c5ATabletLinea');
            muestra('panelbotones');
            $('select[name="tablet.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c4ATabletLinea":
            //c4ATabletLinea es tablet
            oculta('c2ATabletLinea');
            oculta('c3ATabletLinea');
            oculta('c1ATabletLinea');
            muestra('c4ATabletLinea');
            elemtImpares('c5ATabletLinea');
            muestra('panelbotones');
            $('select[name="movil.id"]').val(1);
            $('select[name="portatil.id"]').val(1);
            $('select[name="pc.id"]').val(1);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c5ATabletLinea":
            //c5ATabletLinea es linea
            oculta('c3ATabletLinea');
            oculta('c1ATabletLinea');
            oculta('c2ATabletLinea');
            oculta('c4ATabletLinea');
            muestra('c5ATabletLinea');
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