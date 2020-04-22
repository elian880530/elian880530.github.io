<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Móviles</title>
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
                        Listado <small>Móviles</small>
                    </h1>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li>
                            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li>
                            <a href="#">
                                <g:link action="create" controller="Movil">
                                    Nuevo
                                </g:link>
                            </a>
                        </li>
                        <li class="active">Listado Móviles</li>
                    </ol>
                </div>
                <div class="col-md-6" id="content" role="main" >
                    <div class="form-inline">
                        <g:uploadForm controller="Movil" action="uploadFile" class="form-inline">
                            <ol class="breadcrumb derecha">
                                <li>
                                    <input type="file" accept=".xlsx" name="excelFile" required>
                                </li>
                                <li>
                                    <button type="submit" class="eliminar" name="subirExcelFile" value="" id="subirExcelFile">
                                        <i class="glyphicon glyphicon-open"></i>
                                    </button>
                                </li>
                                <li>
                                    <g:link action="downloadFile" controller="Movil">
                                        <button type="button" class="eliminar">
                                            <i class="glyphicon glyphicon-save"></i>
                                        </button>
                                    </g:link>
                                </li>
                            </ol>
                        </g:uploadForm>
                    </div>
                </div>
                <div class="col-md-12 content scaffold-show" role="main">
                    <g:if test="${flash.message == "1"}">
                        <div class="alert alert-success">
                            <strong>¡Correcto!</strong> Nuevo Móvil Importad desde el Archivo Excel.
                        </div>
                    </g:if>
                    <g:if test="${flash.message == "0"}">
                        <div class="alert alert-danger">
                            <strong>¡Error!</strong> Imposible Cargar los Datos Presentes en el Archivo Excel.
                        </div>
                    </g:if>
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

                                                    <table class="table-hover table table-dark table-striped dataTable no-footer" id="dataTables-example" aria-describedby="dataTables-example_info">
                                                        <thead>
                                                        <tr role="row">
                                                            <th class="sorting_asc" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1" aria-sort="ascending"
                                                                aria-label="Rendering engine: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Id
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Ram
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Almacen
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Asignado
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Proveedor
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                IMEI
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Serie
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 200px;">
                                                                Micro
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 200px;">
                                                                F Compra
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Browser: activate to sort column ascending"
                                                                style="width: 200px;">
                                                                Marca
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Editar
                                                            </th>
                                                            <th class="sorting" tabindex="0"
                                                                aria-controls="dataTables-example" rowspan="1"
                                                                colspan="1"
                                                                aria-label="Platform(s): activate to sort column ascending"
                                                                style="width: 150px;">
                                                                Eliminar
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <g:each in="${movilList}" var="movil" status="i">
                                                            <tr>
                                                                <td class="sorting_1">${movil.id}</td>
                                                                <td>${movil.cantRam}</td>
                                                                <td>${movil.cantAlmacenamiento}</td>
                                                                <td>${movil.dispositivoAsignado}</td>
                                                                <td>${movil.proveedor}</td>
                                                                <td>${movil.imei}</td>
                                                                <td>${movil.numSerie}</td>
                                                                <td>${movil.tipoMicro}</td>
                                                                <td>
                                                                    <g:formatDate format="dd-MM-yyyy" date="${movil.fechaCompra}"/>
                                                                </td>
                                                                <td>${movil.marcaMovil}</td>
                                                                <td>
                                                                    <a href="/movil/edit/${movil.id}" class="edit"><i
                                                                            class="glyphicon glyphicon-pencil ieditar"></i>
                                                                    </a>
                                                                </td>
                                                                <td>
                                                                    <g:set var="asignado" value="${0}"/>
                                                                    <g:each var="asignacionMovil"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
                                                                        <g:if test="${Objects.equals(movil.id, asignacionMovil.movil?.id)}">
                                                                            <g:set var="asignado" value="${1}"/>
                                                                        </g:if>
                                                                    </g:each>
                                                                    <g:if test="${asignado == 0}">
                                                                        <button type="button" class="eliminar"
                                                                                data-toggle="modal"
                                                                                data-target="#exampleModal${movil.id}">
                                                                            <i class="glyphicon glyphicon-trash"></i>
                                                                        </button>
                                                                        <!-- Modal -->
                                                                        <div class="modal fade"
                                                                             id="exampleModal${movil.id}" tabindex="-1"
                                                                             role="dialog"
                                                                             aria-labelledby="exampleModalLabel${movil.id}"
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
                                                                                                ¿Está usted seguro que desea eliminar el móvil con el identificador ${movil.id}?
                                                                                            </strong>
                                                                                        </div>
                                                                                    </div>

                                                                                    <div class="modal-footer">
                                                                                        <g:form resource="${movil}"
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



