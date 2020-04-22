$('#dataTables-example tfoot th').each(function () {
    var title = $(this).text();
    if (title == 'Id')
        $(this).html('<input type="text" style="color: black" size="5" placeholder="' + title + '?" />');
    else if (title == 'Fecha')
        $(this).html('<input type="text" style="color: black" size="18" placeholder="' + title + '?" />');
    else if (title == 'Persona Descubrió Error')
        $(this).html('<input type="text" style="color: black" size="20" placeholder="' + title + '?" />');
    else if (title == 'Persona Afectada')
        $(this).html('<input type="text" style="color: black" size="20" placeholder="' + title + '?" />');
    else if (title == 'Nombre Medicamento')
        $(this).html('<input type="text" style="color: black" size="20" placeholder="' + title + '?" />');
    else if (title == 'Momento Día')
        $(this).html('<input type="text" style="color: black" size="15" placeholder="' + title + '?" />');
    else if (title == 'Casa')
        $(this).html('<input type="text" style="color: black" size="15" placeholder="' + title + '?" />');
    else if (title == 'Cómo se Descubrió')
        $(this).html('<input type="text" style="color: black" size="20" placeholder="' + title + '?" />');
    else if (title == 'Personal Implicado')
        $(this).html('<input type="text" style="color: black" size="20" placeholder="' + title + '?" />');
    else if (title == 'Relato Hechos')
        $(this).html('<input type="text" style="color: black" size="25" placeholder="' + title + '?" />');
    else if (title == 'Intervenciones Realizadas')
        $(this).html('<input type="text" style="color: black" size="25" placeholder="' + title + '?" />');
    else if (title == 'Gravedad Error')
        $(this).html('<input type="text" style="color: black" size="25" placeholder="' + title + '?" />');
    else if (title == 'Características Error')
        $(this).html('<input type="text" disabled="disabled" style="color: black" size="25" placeholder="' + title + '?" />');
    else if (title == 'Tipo Error')
        $(this).html('<input type="text" disabled="disabled" style="color: black" size="25" placeholder="' + title + '?" />');
    else if (title == 'Causas Error')
        $(this).html('<input type="text" disabled="disabled" style="color: black" size="25" placeholder="' + title + '?" />');
    else if (title == 'Error Alcanzó Persona')
        $(this).html('<input type="text" style="color: black" size="20" placeholder="' + title + '?" />');
    else if (title == 'Editar')
        $(this).html('<div style="width: 60px">Editar</div>');
    else if (title == 'Eliminar')
        $(this).html('<div style="width: 60px">Eliminar</div>');
});

var table = $('#dataTables-example').DataTable({
    "deferRender": true,
    "scroller": true,
    "dom": "Brtip",
    "scrollX": true,
    "scrollY": true,
    "processing": true,
    "serverSide": true,
    "ajax": {
        "url": "/incidenciaMedicacion/tablaIncidenciaJson",
        "type": "POST",
    },

    "columns": [
        {"data": "eliminar"},
        {"data": "editar"},
        {"data": "id"},
        {"data": "fechaIncidenciaMedicacion"},
        {"data": "personaDetectoError"},
        {"data": "personaAfectada"},
        {"data": "nombreMedicamento"},
        {"data": "errorAlcanzoPersona"},
        {"data": "comoDescubierto"},
        {"data": "personalImplicado"},
        {"data": "relatoHechos"},
        {"data": "intervencionesRealizadas"},
        {"data": "momentoDia"},
        {"data": "casa"},
        {"data": "gravedadError"},
        {"data": "caracteristicasError"},
        {"data": "tipoError"},
        {"data": "causasError"}

    ],

});

table.columns().eq(0).each(function (colIdx) {
    $('input', table.column(colIdx).footer()).on('keyup change', function () {
        table
            .column(colIdx)
            .search(this.value)
            .draw();
    });
});


$('#dataTables-example').on('draw.dt', function () {
    $(function () {
        $('.albatross').tooltip();
    });
});
