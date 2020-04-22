<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Incidencia</title>
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
                        Listado <small>Incidencias</small>
                    </h1>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li>
                            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li>
                            <a href="#">
                                <g:link action="create" controller="IncidenciaMedicacion">
                                    Nuevo
                                </g:link>
                            </a>
                        </li>
                        <li class="active">Listado Incidencias</li>
                    </ol>
                </div>
                <div class="col-md-6" id="content" role="main">
                    <div class="form-inline">
                        <ol class="breadcrumb derecha">
                            <li>
                                Descargar Archivo
                            </li>
                            <li>
                                <g:link action="downloadFile" controller="IncidenciaMedicacion">
                                    <button type="button" class="eliminar">
                                        <i class="glyphicon glyphicon-save"></i>
                                    </button>
                                </g:link>
                            </li>
                        </ol>
                    </div>
                </div>
                <div class="col-md-12 content scaffold-show" role="main">
                    <g:if test="${flash.message == "1"}">
                        <div class="alert alert-success">
                            <strong>¡Correcto!</strong> Nueva Incidencia Importada desde el Archivo Excel.
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
                                                     class="dataTables_wrapper form-inline" role="main">
                                                    <table id="dataTables-example"
                                                           class="table-hover table table-dark table-striped dataTable no-footer"
                                                           aria-describedby="dataTables-example_info">
                                                        <thead>
                                                        <tr>
                                                            <th>Eliminar</th>
                                                            <th>Editar</th>
                                                            <th>Id</th>
                                                            <th>Fecha</th>
                                                            <th>Persona Descubrió Error</th>
                                                            <th>Persona Afectada</th>
                                                            <th>Nombre Medicamento</th>
                                                            <th>Error Alcanzó Persona</th>
                                                            <th>Cómo se Descubrió</th>
                                                            <th>Personal Implicado</th>
                                                            <th>Relato Hechos</th>
                                                            <th>Intervenciones Realizadas</th>
                                                            <th>Momento Día</th>
                                                            <th>Casa</th>
                                                            <th>Gravedad Error</th>
                                                            <th>Características Error</th>
                                                            <th>Tipo Error</th>
                                                            <th>Causas Error</th>
                                                        </tr>
                                                        </thead>
                                                        <tfoot>
                                                        <tr>
                                                            <th>Eliminar</th>
                                                            <th>Editar</th>
                                                            <th>Id</th>
                                                            <th>Fecha</th>
                                                            <th>Persona Descubrió Error</th>
                                                            <th>Persona Afectada</th>
                                                            <th>Nombre Medicamento</th>
                                                            <th>Error Alcanzó Persona</th>
                                                            <th>Cómo se Descubrió</th>
                                                            <th>Personal Implicado</th>
                                                            <th>Relato Hechos</th>
                                                            <th>Intervenciones Realizadas</th>
                                                            <th>Momento Día</th>
                                                            <th>Casa</th>
                                                            <th>Gravedad Error</th>
                                                            <th>Características Error</th>
                                                            <th>Tipo Error</th>
                                                            <th>Causas Error</th>
                                                        </tr>
                                                        </tfoot>
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
<script src="/assets/js/tablaDinamicaBuscadorIncidencia.js"></script>
</body>
</html>



