<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Histórico</title>
    <g:render template="/layouts/ImportacionCSS"/>
    <g:render template="/layouts/ImportacionTablesCSS"/>
</head>

<body>
<div id="wrapper">

    <g:render template="/layouts/ImportacionBODY"/>

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="page-header">
                        Listado <small>Históricos</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li class="active">Listado Históricos</li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">

                        <div class="panel-body">

                            <div class="row">
                                <div class="col-md-12">
                                    <!-- Advanced Tables -->
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            Listado
                                        </div>

                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <div id="dataTables-example_wrapper"
                                                     class="dataTables_wrapper form-inline" role="grid">
                                                    <table class="table-hover table table-dark table-striped dataTable no-footer"
                                                           id="dataTables-example"
                                                           aria-describedby="dataTables-example_info">
                                                        <thead>
                                                        <tr role="row">
                                                            <th class="sorting_asc" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1" aria-sort="ascending"
                                                                aria-label="Rendering engine: activate to sort column ascending"
                                                                style="width: 30px;">
                                                                Id
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 40px;">
                                                                Usuario
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 20px;">
                                                                Objeto
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 80px;">
                                                                Fecha
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 280px;">
                                                                Acción
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 10px;">
                                                                Cambios
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <g:each in="${historicoList}"
                                                                var="historico" status="i">
                                                            <tr>
                                                                <td class="sorting_1">${historico.id}</td>
                                                                <td>${historico.usuarioEjecutadoAccion}</td>
                                                                <td>${historico.objetoAuditado}</td>
                                                                <td>
                                                                    <g:formatDate format="dd-MM-yyyy hh:mm:ss" date="${historico.fechaSuceso}"/>
                                                                </td>
                                                                <td>${historico.accion}</td>
                                                                <td>
                                                                    <button type="button" class="eliminar"
                                                                            data-toggle="modal"
                                                                            data-target="#exampleModal${historico.id}">
                                                                        <i class="glyphicon glyphicon-new-window"></i>
                                                                    </button>
                                                                    <!-- Modal -->
                                                                    <div class="modal fade"
                                                                         id="exampleModal${historico.id}" tabindex="-1"
                                                                         role="dialog"
                                                                         aria-labelledby="exampleModalLabel${historico.id}"
                                                                         aria-hidden="true">
                                                                        <div class="modal-dialog modal-dialogUpdate" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h3 class="modal-title"
                                                                                        id="exampleModalLabel">
                                                                                        <i class="glyphicon glyphicon-exclamation-sign"></i>
                                                                                        <strong>Cambios</strong>
                                                                                        <button type="button"
                                                                                                class="close"
                                                                                                data-dismiss="modal"
                                                                                                aria-label="Close">
                                                                                            <span aria-hidden="true">
                                                                                                &times;
                                                                                            </span>
                                                                                        </button>
                                                                                    </h3>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="alert alert-warning" style="white-space:pre-wrap; word-wrap:break-word">${historico.accionSecundaria}</div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </g:each>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--End Advanced Tables -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <g:render template="/layouts/ImportacionFirma"/>

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->

</div>

<g:render template="/layouts/ImportacionJS"/>
<g:render template="/layouts/ImportacionTablesJS"/>

</body>

</html>

