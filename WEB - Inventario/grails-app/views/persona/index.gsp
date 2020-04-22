<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Personas</title>
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
                        Listado <small>Personas</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="create" controller="Persona">
                                Nueva
                            </g:link>
                        </a></li>
                        <li class="active">Listado Personas</li>
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
                                                     class="dataTables_wrapper form-inline"
                                                     role="grid"><div class="row">
                                                </div>

                                                    <table class="table-hover table table-dark table-striped dataTable no-footer"
                                                           id="dataTables-example"
                                                           aria-describedby="dataTables-example_info">
                                                        <thead>
                                                        <tr role="row">
                                                            <th class="sorting_asc" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1" colspan="1"
                                                                aria-sort="ascending"
                                                                aria-label="Rendering engine: activate to sort column ascending"
                                                                style="width: 25px;">
                                                                Id
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1" colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Nombre Apellidos
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1" colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Correo
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1" colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 50px;">
                                                                Doc Ident
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1" colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 50px;">
                                                                Departamento
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1" colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 25px;">
                                                                Editar
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example"
                                                                rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 25px;">
                                                                Eliminar
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <g:each in="${personaList}" var="persona"
                                                                status="i">
                                                            <tr>
                                                                <td class="sorting_1">${persona.id}</td>
                                                                <td>${persona.nombre+" "+persona.apellido1+" "+ persona.apellido2}</td>
                                                                <td>${persona.correo}</td>
                                                                <td>${persona.docIdentificativo}</td>
                                                                <td>${persona.departamento}</td>
                                                                <td>
                                                                    <a href="/persona/edit/${persona.id}"
                                                                       class="edit"><i
                                                                            class="glyphicon glyphicon-pencil ieditar"></i>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <g:set var="asignado" value="${0}"/>
                                                                    <g:each var="asignacionPersona"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
                                                                        <g:if test="${Objects.equals(persona.id, asignacionPersona.persona?.id)}">
                                                                            <g:set var="asignado" value="${1}"/>
                                                                        </g:if>
                                                                    </g:each>
                                                                    <g:if test="${asignado == 0}">
                                                                        <button type="button" class="eliminar"
                                                                                data-toggle="modal"
                                                                                data-target="#exampleModal${persona.id}">
                                                                            <i class="glyphicon glyphicon-trash"></i>
                                                                        </button>
                                                                        <!-- Modal -->
                                                                        <div class="modal fade"
                                                                             id="exampleModal${persona.id}" tabindex="-1"
                                                                             role="dialog"
                                                                             aria-labelledby="exampleModalLabel${persona.id}"
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
                                                                                                ¿Está usted seguro que desea eliminar la persona con el identificador ${persona.id}?
                                                                                            </strong>
                                                                                        </div>
                                                                                    </div>

                                                                                    <div class="modal-footer">
                                                                                        <g:form resource="${persona}"
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
                                                                    </g:if>
                                                                    <g:else>
                                                                        <button type="button" class="eliminar"
                                                                                disabled="disabled">
                                                                            <i class="glyphicon glyphicon-trash" data-toggle="tooltip" data-placement="right" title="Objeto Utilizado"
                                                                               style="color:red"></i>
                                                                        </button>
                                                                    </g:else>
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


