
function oculta(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'none'; //damos un atributo display:none que oculta el div
}

function muestra(id) {
    var elDiv = document.getElementById(id); //se define la variable "elDiv" igual a nuestro div
    elDiv.style.display = 'block';//damos un atributo display:block que muestra  el div
}

window.onload = function () {/*hace que se cargue la función */
    /* "Mandamos como parametro el nombre de la Div para ocultar" */
    oculta('c1');
    /*Ocultamos Todos*/
    oculta('c2');
}

function oculta_select(selObj) {

    switch (selObj) {
        case "SL":
            //alert("Selecciona una opcion");
            oculta('c1');
            oculta('c2');
            $('select[name="tablet.id"]').val(1);
            $('select[name="movil.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1":
            //alert("Movil");
            muestra('c1');
            oculta('c2');
            $('select[name="tablet.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c2":
            //alert("Tablet");
            muestra('c2');
            oculta('c1');
            $('select[name="movil.id"]').val(1);
            $('.selectpicker').selectpicker('refresh');
            break;

        default:
            //alert("error, actualice la pagina");
    }

}

function oculta_selectMovil(selObj) {

    switch (selObj) {
        case "SLMovil":
            oculta('c1Movil');
            oculta('c2Movil');
            break;

        case "c1Movil":
            //alert("Movil");
            muestra('c1Movil');
            oculta('c2Movil');
            break;

        case "c2Movil":
            //alert("Tablet");
            muestra('c2Movil');
            oculta('c1Movil');
            break;

        default:
            //alert("error, actualice la pagina");
    }

}

function oculta_selectTablet(selObj) {

    switch (selObj) {
        case "SLTablet":
            oculta('c1Tablet');
            oculta('c2Tablet');
            break;

        case "c1Tablet":
            //alert("Movil");
            muestra('c1Tablet');
            oculta('c2Tablet');
            break;

        case "c2Tablet":
            //alert("Tablet");
            muestra('c2Tablet');
            oculta('c1Tablet');
            break;

        default:
            //alert("error, actualice la pagina");
    }

}