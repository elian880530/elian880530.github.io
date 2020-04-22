var varOriginalFechaIncidenciaMedicacion_day = document.getElementById("fechaIncidenciaMedicacion_day").value;
var varOriginalFechaIncidenciaMedicacion_month = document.getElementById("fechaIncidenciaMedicacion_month").value;
var varOriginalFechaIncidenciaMedicacion_year = document.getElementById("fechaIncidenciaMedicacion_year").value;
var varOriginalFechaIncidenciaMedicacion_hour = document.getElementById("fechaIncidenciaMedicacion_hour").value;
var varOriginalFechaIncidenciaMedicacion_minute = document.getElementById("fechaIncidenciaMedicacion_minute").value;
var varOriginalErrorAlcanzoPersona = document.getElementById("errorAlcanzoPersona").value;
var varOriginalPersonaDetectoError = document.getElementById("personaDetectoError").value;
var varOriginalPersonaAfectada = document.getElementById("personaAfectada").value;
var varOriginalNombreMedicamento = document.getElementById("nombreMedicamento").value;
var varOriginalComoDescubierto = document.getElementById("comoDescubierto").value;
var varOriginalPersonalImplicado = document.getElementById("personalImplicado").value;
var varOriginalRelatoHechos = document.getElementById("relatoHechos").value;
var varOriginalIntervencionesRealizadas = document.getElementById("intervencionesRealizadas").value;
var varOriginalMomentoDia = document.getElementById("momentoDia").value;
var varOriginalCasa = document.getElementById("casa").value;
var varOriginalGravedadError = document.getElementById("gravedadError").value;
var varOriginalCaracteristicasError = document.getElementById("caracteristicasError").value;
var varOriginalTipoError = document.getElementById("tipoError").value;
var varOriginalCausasError = document.getElementById("causasError").value;

var varSelectFechaIncidenciaMedicacion_day = document.getElementById("fechaIncidenciaMedicacion_day").value;
var varSelectFechaIncidenciaMedicacion_month = document.getElementById("fechaIncidenciaMedicacion_month").value;
var varSelectFechaIncidenciaMedicacion_year = document.getElementById("fechaIncidenciaMedicacion_year").value;
var varSelectFechaIncidenciaMedicacion_hour = document.getElementById("fechaIncidenciaMedicacion_hour").value;
var varSelectFechaIncidenciaMedicacion_minute = document.getElementById("fechaIncidenciaMedicacion_minute").value;
var varSelectErrorAlcanzoPersona = document.getElementById("errorAlcanzoPersona").value;
var varSelectPersonaDetectoError = document.getElementById("personaDetectoError").value;
var varSelectPersonaAfectada = document.getElementById("personaAfectada").value;
var varSelectNombreMedicamento = document.getElementById("nombreMedicamento").value;
var varSelectComoDescubierto = document.getElementById("comoDescubierto").value;
var varSelectPersonalImplicado = document.getElementById("personalImplicado").value;
var varSelectRelatoHechos = document.getElementById("relatoHechos").value;
var varSelectIntervencionesRealizadas = document.getElementById("intervencionesRealizadas").value;
var varSelectMomentoDia = document.getElementById("momentoDia").value;
var varSelectCasa = document.getElementById("casa").value;
var varSelectGravedadError = document.getElementById("gravedadError").value;
var varSelectCaracteristicasError = document.getElementById("caracteristicasError").value;
var varSelectTipoError = document.getElementById("tipoError").value;
var varSelectCausasError = document.getElementById("causasError").value;

$(document).ready(function () {
    $('#fechaIncidenciaMedicacion_day').on('change', function () {
        varSelectFechaIncidenciaMedicacion_day = document.getElementById("fechaIncidenciaMedicacion_day").value;
    });
    $('#fechaIncidenciaMedicacion_month').on('change', function () {
        varSelectFechaIncidenciaMedicacion_month = document.getElementById("fechaIncidenciaMedicacion_month").value;
    });
    $('#fechaIncidenciaMedicacion_year').on('change', function () {
        varSelectFechaIncidenciaMedicacion_year = document.getElementById("fechaIncidenciaMedicacion_year").value;
    });
    $('#fechaIncidenciaMedicacion_hour').on('change', function () {
        varSelectFechaIncidenciaMedicacion_hour = document.getElementById("fechaIncidenciaMedicacion_hour").value;
    });
    $('#fechaIncidenciaMedicacion_minute').on('change', function () {
        varSelectFechaIncidenciaMedicacion_minute = document.getElementById("fechaIncidenciaMedicacion_minute").value;
    });
    $('#errorAlcanzoPersona').on('change', function () {
        varSelectErrorAlcanzoPersona = document.getElementById("errorAlcanzoPersona").value;
    });
    $('#personaDetectoError').on('change', function () {
        varSelectPersonaDetectoError = document.getElementById("personaDetectoError").value;
    });
    $('#personaAfectada').on('change', function () {
        varSelectPersonaAfectada = document.getElementById("personaAfectada").value;
    });
    $('#nombreMedicamento').on('change', function () {
        varSelectNombreMedicamento = document.getElementById("nombreMedicamento").value;
    });
    $('#comoDescubierto').on('change', function () {
        varSelectComoDescubierto = document.getElementById("comoDescubierto").value;
    });
    $('#personalImplicado').on('change', function () {
        varSelectPersonalImplicado = document.getElementById("personalImplicado").value;
    });
    $('#relatoHechos').on('change', function () {
        varSelectRelatoHechos = document.getElementById("relatoHechos").value;
    });
    $('#intervencionesRealizadas').on('change', function () {
        varSelectIntervencionesRealizadas = document.getElementById("intervencionesRealizadas").value;
    });
    $('#momentoDia').on('change', function () {
        varSelectMomentoDia = document.getElementById("momentoDia").value;
    });
    $('#casa').on('change', function () {
        varSelectCasa = document.getElementById("casa").value;
    });
    $('#gravedadError').on('change', function () {
        varSelectGravedadError = document.getElementById("gravedadError").value;
    });
    $('#caracteristicasError').on('change', function () {
        varSelectCaracteristicasError = document.getElementById("caracteristicasError").value;
    });
    $('#tipoError').on('change', function () {
        varSelectTipoError = document.getElementById("tipoError").value;
    });
    $('#causasError').on('change', function () {
        varSelectCausasError = document.getElementById("causasError").value;
    });

});

function validacion() {

    if ((varOriginalFechaIncidenciaMedicacion_day == varSelectFechaIncidenciaMedicacion_day) &&
        (varOriginalFechaIncidenciaMedicacion_month == varSelectFechaIncidenciaMedicacion_month) &&
        (varOriginalFechaIncidenciaMedicacion_year == varSelectFechaIncidenciaMedicacion_year) &&
        (varOriginalFechaIncidenciaMedicacion_hour == varSelectFechaIncidenciaMedicacion_hour) &&
        (varOriginalFechaIncidenciaMedicacion_minute == varSelectFechaIncidenciaMedicacion_minute) &&
        (varOriginalErrorAlcanzoPersona == varSelectErrorAlcanzoPersona) &&
        (varOriginalPersonaDetectoError == varSelectPersonaDetectoError) &&
        (varOriginalPersonaAfectada == varSelectPersonaAfectada) &&
        (varOriginalNombreMedicamento == varSelectNombreMedicamento) &&
        (varOriginalComoDescubierto == varSelectComoDescubierto) &&
        (varOriginalPersonalImplicado == varSelectPersonalImplicado) &&
        (varOriginalRelatoHechos == varSelectRelatoHechos) &&
        (varOriginalIntervencionesRealizadas == varSelectIntervencionesRealizadas) &&
        (varOriginalMomentoDia == varSelectMomentoDia) &&
        (varOriginalCasa == varSelectCasa) &&
        (varOriginalGravedadError == varSelectGravedadError) &&
        (varOriginalCaracteristicasError == varSelectCaracteristicasError) &&
        (varOriginalTipoError == varSelectTipoError) &&
        (varOriginalCausasError == varSelectCausasError)) {

        $("#cuerpoMensaje").html("Imposible actualizar, no se han detectado modificaciones en los valores originales del formulario.");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    return true;
}