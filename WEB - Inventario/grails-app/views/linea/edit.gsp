<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Editar Líneas</title>
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
                        Editar <small>Líneas</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="Linea">
                                Listado Líneas
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
                            Líneas
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="edit-linea" class="paginaform" role="main">
                                                    <g:form class="form-inline" role="form" resource="${linea}" method="PUT" onsubmit="return validacion()">
                                                        <div class="form-group">
                                                            <label> SIM </label>
                                                            <input class="form-control" value="${linea?.numSim}" required="" maxlength="100" name="numSim" id="numSim" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Teléfono Corto</label>
                                                            <input class="form-control" value="${linea?.telefonoCorto}" required="" maxlength="100" name="telefonoCorto" id="telefonoCorto" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Teléfono Largo</label>
                                                            <input class="form-control" value="${linea?.telefonoLargo}" required="" maxlength="100" name="telefonoLargo" id="telefonoLargo" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>PIN</label>
                                                            <input class="form-control" value="${linea?.pin}" required="" maxlength="100" name="pin" id="pin" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>PUK</label>
                                                            <input class="form-control" value="${linea?.puk}" required="" maxlength="100" name="puk" id="puk" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Observaciones</label>
                                                            <input class="form-control" value="${linea?.observaciones}" maxlength="100" name="observaciones" id="observaciones" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Tarificación</label>
                                                            <select class="selectpicker form-control" name="tarificacion.id" id="tarificacion" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${linea?.tarificacion}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="a" in="${inventario.NomTarificacion?.all}">
                                                                    <option value="${a.id}"><li>${a}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Cuenta Facturación</label>
                                                            <select class="selectpicker form-control" name="cuentaFacturacion.id" id="cuentaFacturacion" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${linea?.cuentaFacturacion}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="b"
                                                                        in="${inventario.NomCuentaFacturacion?.all}">
                                                                    <option value="${b.id}"><li>${b}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Ubicación</label>
                                                            <select class="selectpicker form-control" name="ubicacion.id" id="ubicacion" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${linea?.ubicacion}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="c" in="${inventario.NomUbicacion?.all}">
                                                                    <option value="${c.id}"><li>${c}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <g:render template="/layouts/ImportacionModalAlerta"/>
                                                        <g:render template="/layouts/ImportacionValidarCambiosEditLinea"/>

                                                        <div class="form-group">
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input type="checkbox" disabled="disabled"
                                                                        <g:if test="${Objects.equals(true, linea?.lineaAsignada)}">
                                                                            checked="checked"
                                                                        </g:if>
                                                                           name="lineaAsignada"
                                                                           id="lineaAsignada">Asignada
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div id="paginaform">
                                                            <span class="izquierda">
                                                                <g:link action="index" controller="Linea">
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
