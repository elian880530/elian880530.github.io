<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Mostrar Ubicación</title>
    <g:render template="/layouts/ImportacionCSS"/>
</head>

<body>
<div id="wrapper">

    <g:render template="/layouts/ImportacionBODY"/>

    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="page-header">
                        Nueva <small>Ubicación</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="NomUbicacion">
                                Listado Ubicaciones
                            </g:link>
                        </a></li>
                        <li class="active">Nueva</li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Ubicación
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="create-nomUbicacion" class="col-lg-12" role="main">

                                                    <div id="show-nomUbicacion" class="content scaffold-show"
                                                         role="main">
                                                        <div class="alert alert-success">
                                                            <strong>¡Correcto!</strong> Nueva Ubicación.
                                                        </div>
                                                        <g:form resource="${this.nomUbicacion}"
                                                                method="DELETE">
                                                            <g:link action="edit" resource="${this.nomUbicacion}">
                                                                <button type="button" class="btn btn-primary">
                                                                    <i class="glyphicon glyphicon-pencil ieditar"></i>
                                                                    Editar
                                                                </button>
                                                            </g:link>
                                                            <g:set var="utilizado" value="${0}"/>
                                                            <g:each var="Linea"
                                                                    in="${inventario.Linea?.all}"
                                                                    status="k">
                                                                <g:if test="${Objects.equals(nomUbicacion.id, Linea.ubicacion?.id)}">
                                                                    <g:set var="utilizado" value="${1}"/>
                                                                </g:if>
                                                            </g:each>
                                                            <g:each var="Pc"
                                                                    in="${inventario.Pc?.all}"
                                                                    status="l">
                                                                <g:if test="${Objects.equals(nomUbicacion.id, Pc.ubicacion?.id)}">
                                                                    <g:set var="utilizado" value="${1}"/>
                                                                </g:if>
                                                            </g:each>
                                                            <g:each var="Portatil"
                                                                    in="${inventario.Portatil?.all}"
                                                                    status="m">
                                                                <g:if test="${Objects.equals(nomUbicacion.id, Portatil.ubicacion?.id)}">
                                                                    <g:set var="utilizado" value="${1}"/>
                                                                </g:if>
                                                            </g:each>
                                                            <g:if test="${utilizado == 0}">
                                                                <button type="button" class="btn btn-danger"
                                                                        data-toggle="modal" data-target="#exampleModal">
                                                                    <i class="glyphicon glyphicon-trash"></i>
                                                                    Eliminar
                                                                </button>
                                                            </g:if>
                                                            <g:else>
                                                                <a href="#" class="btn btn-danger verDisable"
                                                                   data-toggle="tooltip" data-placement="bottom" title="Objeto Utilizado">
                                                                    <i class="glyphicon glyphicon-trash" ></i>
                                                                    Eliminar
                                                                </a>
                                                            </g:else>

                                                            <g:link action="index" controller="nomUbicacion">
                                                                <button type="button" class="btn btn-success">
                                                                    <i class="glyphicon glyphicon-forward"></i>
                                                                    Continuar
                                                                </button>
                                                            </g:link>
                                                            <g:render template="/layouts/ImportacionModalEliminar"/>
                                                        </g:form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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

</body>
</html>
