<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Editar Marcas Móviles</title>
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
                        Editar <small>Marcas Móviles</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="NomMarcaMovil">
                                Listado Marcas Móviles
                            </g:link>
                        </a></li>
                        <li class="active">Editar</li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Marcas Móviles
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="edit-nomMarcaMovil" class="paginaform" role="main">
                                                    <g:form class="form-inline" role="form"
                                                            resource="${this.nomMarcaMovil}"
                                                            method="PUT" onsubmit="return validacion()">
                                                        <div class="form-group">
                                                            <label>
                                                                Valor
                                                            </label>
                                                            <input class="form-control" value="${this.nomMarcaMovil}"
                                                                   required=""
                                                                   maxlength="100" name="marcaMovil" id="marcaMovil"
                                                                   type="text">
                                                        </div>

                                                        <g:render template="/layouts/ImportacionModalAlerta"/>
                                                        <g:render template="/layouts/ImportacionValidarCambiosEditNomMarcaMovil"/>


                                                        <div id="paginaform">
                                                            <span class="izquierda">
                                                                <g:link action="index" controller="NomMarcaMovil">
                                                                    <button type="button" class="btn btn-success">
                                                                        <i class="glyphicon glyphicon-remove"></i>
                                                                        Cancelar
                                                                    </button>
                                                                </g:link>
                                                            </span>
                                                            <span class="izquierda">
                                                                <button type="submit" class="btn btn-primary"
                                                                        name="update"
                                                                        value="Confirmar"
                                                                        id="update">
                                                                    <i class="glyphicon glyphicon-ok"></i>
                                                                    Confirmar
                                                                </button>
                                                            </span>
                                                        </div>
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
