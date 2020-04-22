<%@ page import="inventario.Tablet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Asignaciones</title>
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
                        Listado <small>Asignaciones</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="create" controller="Asignacion">
                                Nuevo
                            </g:link>
                        </a></li>
                        <li class="active">Listado Asignaciones</li>
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
                                                     class="dataTables_wrapper form-inline" role="grid"><div
                                                        class="row">
                                                </div>
                                                    <table class="table-hover table table-dark table-striped dataTable no-footer table-hover"
                                                           id="dataTables-example"
                                                           aria-describedby="dataTables-example_info">
                                                        <thead>
                                                        <tr role="row">
                                                            <th class="sorting_asc" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1" aria-sort="ascending"
                                                                aria-label="Rendering engine: activate to sort column ascending"
                                                                style="width: 100px;">
                                                                Id
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Fecha
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 200px;">
                                                                Persona Apellidos
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 100px;">
                                                                Dispositivo
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 100px;">
                                                                Línea
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 100px;">
                                                                Editar
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 100px;">
                                                                Eliminar
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <g:each in="${asignacionList}" var="asignacion" status="i">

                                                            <%
                                                                def aux
                                                                def var
                                                                if (!Objects.equals(null, asignacion.tablet?.imei)) {
                                                                    aux = asignacion.tablet?.imei
                                                                }
                                                                if (!Objects.equals(null, asignacion.pc?.pegatina)) {
                                                                    aux = asignacion.pc?.pegatina
                                                                }
                                                                if (!Objects.equals(null, asignacion.movil?.imei)) {
                                                                    aux = asignacion.movil?.imei
                                                                }
                                                                if (!Objects.equals(null, asignacion.portatil?.pegatina)) {
                                                                    aux = asignacion.portatil?.pegatina
                                                                }
                                                                if (!Objects.equals(null, asignacion.linea?.numSim)) {
                                                                    var = asignacion.linea?.numSim
                                                                }

                                                            %>

                                                            <tr>
                                                                <td class="sorting_1">${asignacion.id}</td>
                                                                <td>
                                                                    <g:formatDate format="dd-MM-yyyy" date="${asignacion.fechaAsignacion}"/>
                                                                </td>
                                                                <td>${asignacion.persona.nombre+" "+asignacion.persona.apellido1+" "+ asignacion.persona.apellido2}</td>
                                                                <td>${aux}</td>
                                                                <td>${var}</td>
                                                                <td>
                                                                    <a href="/asignacion/edit/${asignacion.id}"
                                                                       class="edit"><i
                                                                            class="glyphicon glyphicon-pencil ieditar"></i>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <button type="button" class="eliminar"
                                                                            data-toggle="modal"
                                                                            data-target="#exampleModal${asignacion.id}">
                                                                        <i class="glyphicon glyphicon-trash"></i>
                                                                    </button>

                                                                    <!-- Modal -->
                                                                    <div class="modal fade"
                                                                         id="exampleModal${asignacion.id}" tabindex="-1"
                                                                         role="dialog"
                                                                         aria-labelledby="exampleModalLabel${asignacion.id}"
                                                                         aria-hidden="true">
                                                                        <div class="modal-dialog" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h3 class="modal-title"
                                                                                        id="exampleModalLabel">
                                                                                        <i class="glyphicon glyphicon-exclamation-sign"></i>
                                                                                        <strong>Alerta</strong>
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
                                                                                    <div class="alert alert-warning">
                                                                                        <strong>
                                                                                            ¿Está usted seguro que desea eliminar la asignación con el identificador ${asignacion.id}?
                                                                                        </strong>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="modal-footer">
                                                                                    <g:form resource="${asignacion}"
                                                                                            method="DELETE">
                                                                                        <button type="submit"
                                                                                                class="btn btn-danger"
                                                                                                name="delete.id"
                                                                                                id="delete"
                                                                                                value="Delete">
                                                                                            <i class="glyphicon glyphicon-trash"></i> Eliminar
                                                                                        </button>
                                                                                    </g:form>
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



