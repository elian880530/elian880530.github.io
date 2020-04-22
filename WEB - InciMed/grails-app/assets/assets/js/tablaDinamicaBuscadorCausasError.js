$('#dataTables-example tfoot th').each(function () {
    var title = $(this).text();
    if (title == 'Id')
        $(this).html('<input type="text" style="color: black" size="5" placeholder="' + title + '?" />');
    else if (title == 'Causas Error')
        $(this).html('<input type="text" style="color: black" size="125" placeholder="' + title + '?" />');
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
        "url": "/causasError/tablaCausasErrorJson",
        "type": "POST",
    },

    "columns": [
        {"data": "eliminar"},
        {"data": "editar"},
        {"data": "id"},
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
