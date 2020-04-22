<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Editar Personas</title>
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
                        Editar <small>Personas</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="Persona">
                                Listado Personas
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
                            Persona
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="edit-persona" class="paginaform" role="main">
                                                    <g:form class="form-inline" role="form" resource="${this.persona}"
                                                            method="PUT">
                                                        <div class="form-group">
                                                            <label>Nombre</label>
                                                            <input class="form-control" required=""
                                                                   maxlength="100" name="nombre" id="nombre"
                                                                   type="text" value="${this.persona?.nombre}">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Primer Apellido</label>
                                                            <input class="form-control" value="${this.persona?.apellido1}" required=""
                                                                   maxlength="100" name="apellido1" id="apellido1"
                                                                   type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Segundo Apellido</label>
                                                            <input class="form-control" value="${this.persona?.apellido2}" required=""
                                                                   maxlength="100" name="apellido2" id="apellido2"
                                                                   type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Correo</label>
                                                            <input class="form-control" required=""
                                                                   maxlength="100" name="correo" id="correo"
                                                                   type="text" value="${this.persona?.correo}">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Doc Identificativo</label>
                                                            <input class="form-control" required=""
                                                                   maxlength="100" name="docIdentificativo"
                                                                   id="docIdentificativo" type="text" value="${this.persona?.docIdentificativo}">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Departamento</label>
                                                            <input class="form-control" value="${this.persona?.departamento}" required=""
                                                                   maxlength="100" name="departamento"
                                                                   id="departamento" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Empleado ID</label>
                                                            <input class="form-control" value="${this.persona?.employeeID}" required=""
                                                                   maxlength="100" name="employeeID"
                                                                   id="employeeID" type="text">
                                                        </div>

                                                        <div id="paginaform">
                                                            <span class="izquierda">
                                                                <g:link action="index" controller="Persona">
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
