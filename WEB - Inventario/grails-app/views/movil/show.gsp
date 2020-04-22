<%@ page import="inventario.AsignacionController;inventario.Movil;inventario.Persona;inventario.Linea;inventario.Asignacion"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Mostrar Movil</title>
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
                        Nuevo <small>Movil</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="movil">
                                Listado Móviles
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
                            Movil
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="create-movil" class="col-lg-12" role="main">

                                                    <div id="show-movil" class="content scaffold-show"
                                                         role="main">
                                                        <div class="alert alert-success">
                                                            <strong>¡Correcto!</strong> Nuevo Movil.
                                                        </div>
                                                        <g:form resource="${this.movil}"
                                                                method="DELETE">
                                                            <g:link action="edit" resource="${this.movil}">
                                                                <button type="button" class="btn btn-primary">
                                                                    <i class="glyphicon glyphicon-pencil ieditar"></i>
                                                                    Editar
                                                                </button>
                                                            </g:link>

                                                            <g:set var="asignado" value="${0}"/>
                                                            <g:each var="asignacionMovil"
                                                                    in="${inventario.Asignacion?.all}"
                                                                    status="j">
                                                                <g:if test="${Objects.equals(movil.id, asignacionMovil.movil?.id)}">
                                                                    <g:set var="asignado" value="${1}"/>
                                                                </g:if>
                                                            </g:each>
                                                            <g:if test="${asignado == 0}">
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

                                                            <g:link action="index" controller="movil">
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

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            Asignación
                                        </div>

                                        <div class="panel-body">
                                            <div class="row">

                                                <g:set var="persona" value="${""}"/>
                                                <g:set var="asignacion" value="${""}"/>
                                                <g:set var="linea" value="${""}"/>
                                                <g:set var="movilAsignado" value="${0}"/>
                                                <g:each var="listadoAsignaciones" in="${Asignacion?.all}" status="j">
                                                    <g:if test="${Objects.equals(movil.id, listadoAsignaciones.movil?.id)}">
                                                        <g:set var="asignacion" value="${Asignacion?.get(listadoAsignaciones.id)}"/>
                                                        <g:set var="persona" value="${listadoAsignaciones.persona}"/>
                                                        <g:set var="movilAsignado" value="${1}"/>
                                                        <g:if test="${!Objects.equals(null, asignacion.linea?.id)}">
                                                            <g:set var="linea" value="${Linea?.get(listadoAsignaciones.linea.id)}"/>
                                                        </g:if>
                                                    </g:if>
                                                </g:each>

                                                <g:if test="${Objects.equals(movilAsignado,0)}">
                                                    <div id="create-asignacion" class="paginaform" role="main">
                                                        <form class="form-inline" role="form" action="/asignacion/saveMovil" method="post">

                                                            <div class="form-group">
                                                                <label>Persona</label>
                                                                <select class="selectpicker form-control" name="persona.id"  data-size="5" data-live-search="true">
                                                                    <g:each var="b" in="${Persona?.all}">
                                                                        <option value="${b.id}"><li>${b.nombre+" "+b.apellido1+" "+ b.apellido2}</li></option>
                                                                    </g:each>
                                                                </select>
                                                            </div>

                                                            <div class="form-group dataselect">
                                                                <label>Fecha de Asignación</label>
                                                                <g:set var="asignacionFecha" value="${""}"/>
                                                                <g:if test="${!Objects.equals("",asignacion)}">
                                                                    <g:set var="asignacionFecha" value="${asignacion?.fechaAsignacion}"/>
                                                                </g:if>
                                                                <g:datePicker type="date" name="fechaAsignacion" precision="day" value="${asignacionFecha}"/>
                                                            </div>

                                                            <div class="form-group">
                                                                <label>IMEI Movil</label>
                                                                <select resource="${movil}" class="selectpicker form-control" name="movil.id">
                                                                    <option value="${movil.id}"><li>${movil.imei}</li></option>
                                                                </select>
                                                            </div>

                                                            <div class="form-group">
                                                                <g:set var="resultLineaListAux" value="${[]}"/>
                                                                <g:if test="${Asignacion?.all == []}">
                                                                    <% resultLineaListAux = Linea?.all %>
                                                                </g:if>
                                                                <g:else>
                                                                    <g:each var="lineaAux" in="${Linea?.all}" status="i">
                                                                        <g:set var="resultLineaAux" value="${0}"/>
                                                                        <g:each var="asignacionLineaAux" in="${Asignacion?.all}" status="j">
                                                                            <g:if test="${Objects.equals(lineaAux.id, asignacionLineaAux.linea?.id)}">
                                                                                <g:set var="resultLineaAux" value="${1}"/>
                                                                            </g:if>
                                                                        </g:each>
                                                                        <g:if test="${resultLineaAux == 0}">
                                                                            <% resultLineaListAux.add(lineaAux) %>
                                                                        </g:if>
                                                                    </g:each>
                                                                </g:else>
                                                                <label>Número Sim Línea</label>
                                                                <select class="selectpicker form-control" name="linea.id" id="linea" data-size="5" data-live-search="true">
                                                                    <g:if test="${resultLineaListAux == []}">
                                                                        <option value=""><li>Todas las líneas han sido asignadas</li></option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value=""><li>Seleccione un valor</li></option>
                                                                        <g:each var="d" in="${resultLineaListAux}" status="k">
                                                                            <option value="${d.id}"><li>${d.numSim}</li></option>
                                                                        </g:each>
                                                                    </g:else>
                                                                </select>
                                                            </div>

                                                            <span class="izquierda">
                                                                <button type="submit" class="btn btn-primary" name="create" value="Crear" id="create">
                                                                    <i class="glyphicon glyphicon-ok"></i>
                                                                    Asignar
                                                                </button>
                                                            </span>

                                                        </form>
                                                    </div>
                                                </g:if>

                                                <g:else>
                                                    <div id="edit-asignacion" class="paginaform" role="main">
                                                        <g:form class="form-inline" role="form" resource="${asignacion}" method="PUT" onsubmit="return validacion()">

                                                            <div class="form-group">
                                                                <label>Persona</label>
                                                                <select class="selectpicker form-control" name="persona.id" id="persona" data-size="5" data-live-search="true">
                                                                    <g:if test="${!Objects.equals(persona,"")}">
                                                                        <option value="${persona.id}" selected><li>${persona.nombre+" "+persona.apellido1+" "+ persona.apellido2}</li></option>
                                                                        <g:each var="a" in="${Persona?.all}">
                                                                            <option value="${a.id}"><li>${a.nombre+" "+a.apellido1+" "+ a.apellido2}</li></option>
                                                                        </g:each>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <g:each var="b" in="${Persona?.all}">
                                                                            <option value="${b.id}"><li>${b.nombre+" "+b.apellido1+" "+ b.apellido2}</li></option>
                                                                        </g:each>
                                                                    </g:else>
                                                                </select>
                                                            </div>

                                                            <div class="form-group dataselect">
                                                                <label>Fecha de Asignación</label>
                                                                <g:datePicker type="date" name="fechaAsignacion" precision="day" value="${asignacion?.fechaAsignacion}"/>
                                                            </div>

                                                            <div class="form-group">
                                                                <label>IMEI Movil</label>
                                                                <select resource="${movil}" class="selectpicker form-control" name="movil.id">
                                                                    <option value="${movil.id}"><li>${movil.imei}</li></option>
                                                                </select>
                                                            </div>

                                                            <g:if test="${!Objects.equals("", linea)}">
                                                                <div class="form-group">
                                                                <g:set var="resultLineaListAux" value="${[]}"/>
                                                                <g:if test="${Asignacion?.all == []}">
                                                                    <% resultLineaListAux = Linea?.all %>
                                                                </g:if>
                                                                <g:else>
                                                                    <g:each var="lineaAux" in="${Linea?.all}" status="i">
                                                                        <g:set var="resultLineaAux" value="${0}"/>
                                                                        <g:each var="asignacionLineaAux" in="${Asignacion?.all}" status="j">
                                                                            <g:if test="${Objects.equals(lineaAux.id, asignacionLineaAux.linea?.id)}">
                                                                                <g:set var="resultLineaAux" value="${1}"/>
                                                                            </g:if>
                                                                        </g:each>
                                                                        <g:if test="${resultLineaAux == 0}">
                                                                            <% resultLineaListAux.add(lineaAux) %>
                                                                        </g:if>
                                                                    </g:each>
                                                                </g:else>
                                                                <label>Número Sim Línea</label>
                                                                <select class="selectpicker form-control" name="linea.id" id="movilLineaAsignado" data-size="5" data-live-search="true">
                                                                    <option value=""><li>Ninguno</li></option>
                                                                    <option value="${linea.id}" selected><li>${linea.numSim}</li></option>
                                                                    <g:each var="d" in="${resultLineaListAux}" status="k">
                                                                        <option value="${d.id}"><li>${d.numSim}</li></option>
                                                                    </g:each>
                                                                </select>
                                                                </div>
                                                                <g:render template="/layouts/ImportacionValidarCambiosMovilConLinea"/>
                                                                <g:render template="/layouts/ImportacionModalAlerta"/>
                                                            </g:if>

                                                            <g:if test="${Objects.equals("", linea)}">
                                                                <div class="form-group">
                                                                <g:set var="resultLineaListAux" value="${[]}"/>
                                                                <g:if test="${Asignacion?.all == []}">
                                                                    <% resultLineaListAux = Linea?.all %>
                                                                </g:if>
                                                                <g:else>
                                                                    <g:each var="lineaAux" in="${Linea?.all}" status="i">
                                                                        <g:set var="resultLineaAux" value="${0}"/>
                                                                        <g:each var="asignacionLineaAux" in="${Asignacion?.all}" status="j">
                                                                            <g:if test="${Objects.equals(lineaAux.id, asignacionLineaAux.linea?.id)}">
                                                                                <g:set var="resultLineaAux" value="${1}"/>
                                                                            </g:if>
                                                                        </g:each>
                                                                        <g:if test="${resultLineaAux == 0}">
                                                                            <% resultLineaListAux.add(lineaAux) %>
                                                                        </g:if>
                                                                    </g:each>
                                                                </g:else>
                                                                <label>Número Sim Línea</label>
                                                                <select class="selectpicker form-control" name="linea.id" id="movilNoLineaAsignado" data-size="5" data-live-search="true">
                                                                    <g:if test="${resultLineaListAux == []}">
                                                                        <option value=""><li>Todas las líneas han sido asignadas</li></option>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <option value=""><li>Seleccione un valor</li></option>
                                                                        <g:each var="d" in="${resultLineaListAux}" status="k">
                                                                            <option value="${d.id}"><li>${d.numSim}</li></option>
                                                                        </g:each>
                                                                    </g:else>
                                                                </select>
                                                                </div>
                                                                <g:render template="/layouts/ImportacionValidarCambiosMovilSinLinea"/>
                                                                <g:render template="/layouts/ImportacionModalAlerta"/>
                                                            </g:if>

                                                            <span class="izquierda">
                                                                <button type="submit" class="btn btn-primary" name="update" value="Actualizar" id="update">
                                                                    <i class="glyphicon glyphicon-ok"></i>
                                                                    Confirmar
                                                                </button>
                                                            </span>

                                                        </g:form>
                                                        <g:render template="/layouts/ImportacionBotonEliminarAsignacion"/>
                                                    </div>
                                                </g:else>

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
