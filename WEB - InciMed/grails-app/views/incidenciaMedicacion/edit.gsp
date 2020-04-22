<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Editar Incidencia Medicación</title>
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
                        Editar <small>Incidencia Medicación</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="IncidenciaMedicacion">
                                Listado Incidencia Medicación
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
                            Incidencia Medicación
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="edit-incidenciaMedicacion" class="paginaform" role="main">
                                                    <g:form class="form-inline" role="form"
                                                            resource="${this.incidenciaMedicacion}" method="PUT"
                                                            onsubmit="return validacion()">
                                                        <div class="form-group dataselect">
                                                            <label>Fecha Incidencia Medicación</label>
                                                            <g:datePicker type="date" name="fechaIncidenciaMedicacion"
                                                                          id="fechaIncidenciaMedicacion"
                                                                          value="${this.incidenciaMedicacion?.fechaIncidenciaMedicacion}"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Error Alcanzó Persona</label>
                                                            <select class="selectpicker form-control"
                                                                    name="errorAlcanzoPersona" id="errorAlcanzoPersona">
                                                                <g:set var="valor"
                                                                       value="${this.incidenciaMedicacion?.errorAlcanzoPersona}"/>
                                                                <option value="${valor}" selected><li>${valor}</li>
                                                                </option>
                                                                <option value="0"><li>No</li></option>
                                                                <option value="1"><li>Si</li></option>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Persona Detectó Error</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.personaDetectoError}"
                                                                   required=""
                                                                   maxlength="100" name="personaDetectoError"
                                                                   id="personaDetectoError"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Persona Afectada</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.personaAfectada}"
                                                                   required=""
                                                                   maxlength="100" name="personaAfectada"
                                                                   id="personaAfectada"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Nombre Medicamento</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.nombreMedicamento}"
                                                                   required=""
                                                                   maxlength="100" name="nombreMedicamento"
                                                                   id="nombreMedicamento"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Cómo se Descubrió</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.comoDescubierto}"
                                                                   required=""
                                                                   maxlength="100" name="comoDescubierto"
                                                                   id="comoDescubierto"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Personal Implicado</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.personalImplicado}"
                                                                   required=""
                                                                   maxlength="100" name="personalImplicado"
                                                                   id="personalImplicado"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Relato Hechos</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.relatoHechos}"
                                                                   required=""
                                                                   maxlength="100" name="relatoHechos" id="relatoHechos"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Intervenciones Realizadas</label>
                                                            <input class="form-control"
                                                                   value="${this.incidenciaMedicacion?.intervencionesRealizadas}"
                                                                   required=""
                                                                   maxlength="100" name="intervencionesRealizadas"
                                                                   id="intervencionesRealizadas"
                                                                   type="text">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Momento Día</label>
                                                            <select class="selectpicker form-control" name="momentoDia"
                                                                    id="momentoDia" data-size="5"
                                                                    data-live-search="true">
                                                                <g:set var="valor"
                                                                       value="${this.incidenciaMedicacion?.momentoDia}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li>
                                                                </option>
                                                                <g:each var="a" in="${incimed.NomMomentoDia?.all}">
                                                                    <option value="${a.id}"><li>${a}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Casa</label>
                                                            <select class="selectpicker form-control" name="casa"
                                                                    id="casa" data-size="5" data-live-search="true">
                                                                <g:set var="valor"
                                                                       value="${this.incidenciaMedicacion?.casa}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li>
                                                                </option>
                                                                <g:each var="a" in="${incimed.NomCasa?.all}">
                                                                    <option value="${a.id}"><li>${a}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Gravedad Error</label>
                                                            <select class="selectpicker form-control"
                                                                    name="gravedadError" id="gravedadError"
                                                                    data-size="5" data-live-search="true">
                                                                <g:set var="valor"
                                                                       value="${this.incidenciaMedicacion?.gravedadError}"/>
                                                                <option value="${valor.id}" selected><li>${valor}</li>
                                                                </option>
                                                                <g:each var="a" in="${incimed.NomGravedadError?.all}">
                                                                    <option value="${a.id}"><li>${a}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Características Error</label>
                                                            <g:select class="selectpicker form-control"
                                                                      name="caracteristicasError"
                                                                      from="${incimed.CaracteristicasError.list()}"
                                                                      value="${this.incidenciaMedicacion?.caracteristicasError*.id}"
                                                                      optionKey="id" multiple="true" data-size="5"
                                                                      data-live-search="true"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Tipo Error</label>
                                                            <g:select class="selectpicker form-control" name="tipoError"
                                                                      from="${incimed.TipoError.list()}"
                                                                      value="${this.incidenciaMedicacion?.tipoError*.id}"
                                                                      optionKey="id" multiple="true" data-size="5"
                                                                      data-live-search="true"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Causas Error</label>
                                                            <g:select class="selectpicker form-control"
                                                                      name="causasError"
                                                                      from="${incimed.CausasError.list()}"
                                                                      value="${this.incidenciaMedicacion?.causasError*.id}"
                                                                      optionKey="id" multiple="true" data-size="5"
                                                                      data-live-search="true"/>
                                                        </div>
                                                        <g:render template="/layouts/ImportacionModalAlerta"/>
                                                        <g:render template="/layouts/ImportacionValidarCambiosEditIncidenciaMedicacion"/>
                                                        <div id="paginaform">
                                                            <span class="izquierda">
                                                                <g:link action="index"
                                                                        controller="IncidenciaMedicacion">
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
