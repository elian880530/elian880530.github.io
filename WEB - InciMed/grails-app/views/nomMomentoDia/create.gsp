<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Momento Día</title>
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
                        Nuevo <small>Momento Día</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="NomMomentoDia">
                                Listado Momento Día
                            </g:link>
                        </a></li>
                        <li class="active">Nuevo</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Momento Día
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="create-nomMomentoDia" class="col-lg-6" role="main">
                                                    <form role="form" action="/nomMomentoDia/save" method="post">
                                                        <div class="form-group">
                                                            <label>
                                                                Nuevo Valor
                                                            </label>
                                                            <input class="form-control" value="" required=""
                                                                   maxlength="100"
                                                                   name="momentoDia" id="momentoDia" type="text">
                                                        </div>
                                                        <g:link action="index" controller="NomMomentoDia">
                                                            <button type="button" class="btn btn-success">
                                                                <i class="glyphicon glyphicon-remove"></i>
                                                                Cancelar
                                                            </button>
                                                        </g:link>
                                                        <button type="submit" class="btn btn-primary" name="create"
                                                                value="Crear"
                                                                id="create">
                                                            <i class="glyphicon glyphicon-ok"></i>
                                                            Confirmar
                                                        </button>
                                                    </form>
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
