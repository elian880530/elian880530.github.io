<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Editar Móviles</title>
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
                        Editar <small>Móviles</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="Movil">
                                Listado Móviles
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
                            Móviles
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="edit-movil" class="paginaform" role="main">
                                                    <g:form class="form-inline" role="form" resource="${this.movil}" method="PUT" onsubmit="return validacion()">

                                                        <div class="form-group dataselect">
                                                            <label>Fecha de Compra</label>
                                                            <g:datePicker type="date" name="fechaCompra" id="fechaCompra" precision="day" value="${this.movil?.fechaCompra}"/>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Marca Movil</label>
                                                            <select class="selectpicker form-control" name="marcaMovil.id" id="marcaMovil" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${this.movil?.marcaMovil}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="a" in="${inventario.NomMarcaMovil?.all}">
                                                                    <option value="${a.id}"><li>${a}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Proveedor</label>
                                                            <select class="selectpicker form-control" name="proveedor.id" id="proveedor" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${this.movil?.proveedor}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="b" in="${inventario.NomProveedor?.all}">
                                                                    <option value="${b.id}"><li>${b}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Tipo de Micro</label>
                                                            <select class="selectpicker form-control" name="tipoMicro.id" id="tipoMicro" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${this.movil?.tipoMicro}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="c" in="${inventario.NomTipoMicro?.all}">
                                                                    <option value="${c.id}"><li>${c}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Cantidad de Ram</label>
                                                            <select class="selectpicker form-control" name="cantRam.id" id="cantRam" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${this.movil?.cantRam}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="d" in="${inventario.NomCantRam?.all}">
                                                                    <option value="${d.id}"><li>${d}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Cantidad de Almacenamiento</label>
                                                            <select class="selectpicker form-control" name="cantAlmacenamiento.id" id="cantAlmacenamiento" data-size="5" data-live-search="true">
                                                                <g:set var="valor" value="${this.movil?.cantAlmacenamiento}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li></option>
                                                                <g:each var="e"
                                                                        in="${inventario.NomCantAlmacenamiento?.all}">
                                                                    <option value="${e.id}"><li>${e}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>IMEI</label>
                                                            <input class="form-control" value="${this.movil?.imei}" required="" maxlength="100" name="imei" id="imei" type="text">
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Número de Serie</label>
                                                            <input class="form-control" value="${this.movil?.numSerie}" required="" maxlength="100" name="numSerie" id="numSerie" type="text">
                                                        </div>

                                                        <g:render template="/layouts/ImportacionModalAlerta"/>
                                                        <g:render template="/layouts/ImportacionValidarCambiosEditMovil"/>

                                                        <div class="form-group">
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input type="checkbox"
                                                                        <g:if test="${Objects.equals(true, movil?.dispositivoAsignado)}">
                                                                            checked="checked"
                                                                        </g:if>
                                                                           disabled="disabled"
                                                                           name="dispositivoAsignado"
                                                                           id="dispositivoAsignado">Asignado
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div id="paginaform">
                                                            <span class="izquierda">
                                                                <g:link action="index" controller="Movil">
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
