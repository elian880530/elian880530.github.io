
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
    varOculMenu = document.getElementById("oculMenu").value;
    varSelectPc = document.getElementById("pc").value;
    varSelectPortatil = document.getElementById("portatil").value;
    varSelectTablet = document.getElementById("tablet").value;
    varSelectLinea = document.getElementById("linea").value;
    varSelectMovil = document.getElementById("movil").value;

    if (varOculMenu == "SL"){
        $("#cuerpoMensaje").html("Debe seleccionar un dispositivo");
        $("#alertModal").modal({backdrop: true});
        return false;
    }

    else if (varOculMenu == "c2" && varSelectPc == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Pc");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c3" && varSelectPortatil == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Portatil");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c4" && varSelectTablet == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Tablet o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c5" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Linea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    else if (varOculMenu == "c1" && varSelectMovil == "" && varSelectLinea == ""){
        $("#cuerpoMensaje").html("Debe seleccionar un Movil o una Línea");
        $("#alertModal").modal({backdrop: true});
        return false;
    }
    return true;
}


window.onload = function () {/*hace que se cargue la función */
    /* "Mandamos como parametro el nombre de la Div para ocultar" */
    oculta('c1');
    /*Ocultamos Todos*/
    oculta('c2');
    oculta('c3');
    oculta('c4');
    oculta('c5');
}

function oculta_select(selObj) {

    switch (selObj) {
        case "SL":
            //alert("Selecciona una opcion");
            oculta('c1');
            oculta('c2');
            oculta('c3');
            oculta('c4');
            oculta('c5');
            $('select[name="pc.id"]').val(0);
            $('select[name="tablet.id"]').val(0);
            $('select[name="movil.id"]').val(0);
            $('select[name="portatil.id"]').val(0);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c1":
            //alert("Movil");
            muestra('c1');
            oculta('c2');
            oculta('c3');
            oculta('c4');
            muestra('c5');
            $('select[name="pc.id"]').val(0);
            $('select[name="tablet.id"]').val(0);
            $('select[name="portatil.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c2":
            //alert("Pc");
            muestra('c2');
            oculta('c1');
            oculta('c3');
            oculta('c4');
            oculta('c5');
            $('select[name="tablet.id"]').val(0);
            $('select[name="movil.id"]').val(0);
            $('select[name="portatil.id"]').val(0);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c3":
            //alert("Portatil");
            muestra('c3');
            oculta('c1');
            oculta('c2');
            oculta('c4');
            oculta('c5');
            $('select[name="pc.id"]').val(0);
            $('select[name="tablet.id"]').val(0);
            $('select[name="movil.id"]').val(0);
            $('select[name="linea.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c4":
            //alert("Tablet");
            muestra('c4');
            oculta('c1');
            oculta('c2');
            oculta('c3');
            muestra('c5');
            $('select[name="pc.id"]').val(0);
            $('select[name="movil.id"]').val(0);
            $('select[name="portatil.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        case "c5":
            //alert("Tablet");
            muestra('c5');
            oculta('c1');
            oculta('c2');
            oculta('c3');
            oculta('c4');
            $('select[name="pc.id"]').val(0);
            $('select[name="movil.id"]').val(0);
            $('select[name="tablet.id"]').val(0);
            $('select[name="portatil.id"]').val(0);
            $('.selectpicker').selectpicker('refresh');
            break;

        default:
            //alert("error, actualice la pagina");
    }

}