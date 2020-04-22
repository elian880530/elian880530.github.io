<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Asignaciones</title>
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
                        Nueva <small>Asignación</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="asignacion">
                                Listado Asignaciones
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
                            Asignación
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="row">
                                                <div id="create-asignacion" class="paginaform" role="main">
                                                    <form class="form-inline" role="form" action="/asignacion/save" method="POST" onsubmit="return validacion()">

                                                        <g:render template="/layouts/ImportacionModalAlerta"/>

                                                        <div class="form-group">
                                                            <label>Persona</label>
                                                            <select class="selectpicker form-control" name="persona.id"
                                                                    id="persona" data-size="5" data-live-search="true">
                                                                <g:each var="a" in="${inventario.Persona?.all}"
                                                                        status="r">
                                                                    <option value="${a.id}"><li>${a.nombre+" "+a.apellido1+" "+ a.apellido2}</li></option>
                                                                </g:each>
                                                            </select>
                                                        </div>

                                                        <div class="form-group dataselect">
                                                            <label>Fecha de Asignación</label>
                                                            <g:datePicker type="date" name="fechaAsignacion"
                                                                          id="fechaAsignacion" precision="day"
                                                                          value="${asignacion?.fechaAsignacion}"/>
                                                        </div>

                                                        <div class="form-group">
                                                            <label>Tipo Dispositivo</label>
                                                            <select class="form-control" name="oculMenu" id="oculMenu" onChange="oculta_select(this.value);">
                                                                <option value="SL">Seleccione una opción</option>
                                                                <option value="c1"><li>Movil</li></option>
                                                                <option value="c2"><li>Pc</li></option>
                                                                <option value="c3"><li>Portatil</li></option>
                                                                <option value="c4"><li>Tablet</li></option>
                                                                <option value="c5"><li>Linea</li></option>
                                                            </select>
                                                        </div>

                                                        <div id="c1" class="form-group">
                                                            <g:set var="resultMovilListAux" value="${[]}"/>
                                                            <g:if test="${inventario.Asignacion?.all == []}">
                                                                <% resultMovilListAux = inventario.Movil?.all %>
                                                            </g:if>
                                                            <g:else>
                                                                <g:each var="movilAux" in="${inventario.Movil?.all}"
                                                                        status="i">
                                                                    <g:set var="resultMovilAux" value="${0}"/>
                                                                    <g:each var="asignacionMovilAux"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
                                                                        <g:if test="${Objects.equals(movilAux.id, asignacionMovilAux.movil?.id)}">
                                                                            <g:set var="resultMovilAux" value="${1}"/>
                                                                        </g:if>
                                                                    </g:each>
                                                                    <g:if test="${resultMovilAux == 0}">
                                                                        <% resultMovilListAux.add(movilAux) %>
                                                                    </g:if>
                                                                </g:each>
                                                            </g:else>
                                                            <label>IMEI Movil</label>
                                                            <select class="selectpicker form-control" name="movil.id"
                                                                    id="movil" data-size="5" data-live-search="true">
                                                                <g:if test="${resultMovilListAux == []}">
                                                                    <option value=""><li>Todos los móviles han sido asignados</li>
                                                                    </option>
                                                                </g:if>
                                                                <g:else>
                                                                    <option value=""><li>Seleccione un valor</li>
                                                                    </option>
                                                                    <g:each var="d" in="${resultMovilListAux}"
                                                                            status="k">
                                                                        <option value="${d.id}"><li>${d.imei}</li>
                                                                        </option>
                                                                    </g:each>
                                                                </g:else>
                                                            </select>
                                                        </div>

                                                        <div id="c2" class="form-group">
                                                            <g:set var="resultPcListAux" value="${[]}"/>
                                                            <g:if test="${inventario.Asignacion?.all == []}">
                                                                <% resultPcListAux = inventario.Pc?.all %>
                                                            </g:if>
                                                            <g:else>
                                                                <g:each var="pcAux" in="${inventario.Pc?.all}"
                                                                        status="i">
                                                                    <g:set var="resultPcAux" value="${0}"/>
                                                                    <g:each var="asignacionPcAux"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
                                                                        <g:if test="${Objects.equals(pcAux.id, asignacionPcAux.pc?.id)}">
                                                                            <g:set var="resultPcAux" value="${1}"/>
                                                                        </g:if>
                                                                    </g:each>
                                                                    <g:if test="${resultPcAux == 0}">
                                                                        <% resultPcListAux.add(pcAux) %>
                                                                    </g:if>
                                                                </g:each>
                                                            </g:else>
                                                            <label>Pegatina Pc</label>
                                                            <select class="selectpicker form-control" name="pc.id"
                                                                    id="pc" data-size="5" data-live-search="true">
                                                                <g:if test="${resultPcListAux == []}">
                                                                    <option value=""><li>Todas las pc han sido asignadas</li>
                                                                    </option>
                                                                </g:if>
                                                                <g:else>
                                                                    <option value=""><li>Seleccione un valor</li>
                                                                    </option>
                                                                    <g:each var="d" in="${resultPcListAux}" status="k">
                                                                        <option value="${d.id}"><li>${d.pegatina}</li>
                                                                        </option>
                                                                    </g:each>
                                                                </g:else>
                                                            </select>
                                                        </div>

                                                        <div id="c3" class="form-group">
                                                            <g:set var="resultPortatilListAux" value="${[]}"/>
                                                            <g:if test="${inventario.Asignacion?.all == []}">
                                                                <% resultPortatilListAux = inventario.Portatil?.all %>
                                                            </g:if>
                                                            <g:else>
                                                                <g:each var="portatilAux"
                                                                        in="${inventario.Portatil?.all}" status="i">
                                                                    <g:set var="resultPortatilAux" value="${0}"/>
                                                                    <g:each var="asignacionPortatilAux"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
                                                                        <g:if test="${Objects.equals(portatilAux.id, asignacionPortatilAux.portatil?.id)}">
                                                                            <g:set var="resultPortatilAux"
                                                                                   value="${1}"/>
                                                                        </g:if>
                                                                    </g:each>
                                                                    <g:if test="${resultPortatilAux == 0}">
                                                                        <% resultPortatilListAux.add(portatilAux) %>
                                                                    </g:if>
                                                                </g:each>
                                                            </g:else>
                                                            <label>Pegatina Portatil</label>
                                                            <select class="selectpicker form-control" name="portatil.id"
                                                                    id="portatil" data-size="5" data-live-search="true">
                                                                <g:if test="${resultPortatilListAux == []}">
                                                                    <option value=""><li>Todos los portátiles han sido asignados</li>
                                                                    </option>
                                                                </g:if>
                                                                <g:else>
                                                                    <option value=""><li>Seleccione un valor</li>
                                                                    </option>
                                                                    <g:each var="d" in="${resultPortatilListAux}"
                                                                            status="k">
                                                                        <option value="${d.id}"><li>${d.pegatina}</li>
                                                                        </option>
                                                                    </g:each>
                                                                </g:else>
                                                            </select>
                                                        </div>

                                                        <div id="c4" class="form-group">
                                                            <g:set var="resultTabletListAux" value="${[]}"/>
                                                            <g:if test="${inventario.Asignacion?.all == []}">
                                                                <% resultTabletListAux = inventario.Tablet?.all %>
                                                            </g:if>
                                                            <g:else>
                                                                <g:each var="tabletAux" in="${inventario.Tablet?.all}"
                                                                        status="i">
                                                                    <g:set var="resultTabletAux" value="${0}"/>
                                                                    <g:each var="asignacionTabletAux"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
                                                                        <g:if test="${Objects.equals(tabletAux.id, asignacionTabletAux.tablet?.id)}">
                                                                            <g:set var="resultTabletAux" value="${1}"/>
                                                                        </g:if>
                                                                    </g:each>
                                                                    <g:if test="${resultTabletAux == 0}">
                                                                        <% resultTabletListAux.add(tabletAux) %>
                                                                    </g:if>
                                                                </g:each>
                                                            </g:else>
                                                            <label>IMEI Tablet</label>
                                                            <select class="selectpicker form-control" name="tablet.id"
                                                                    id="tablet" data-size="5" data-live-search="true">
                                                                <g:if test="${resultTabletListAux == []}">
                                                                    <option value=""><li>Todos los tablet han sido asignados</li>
                                                                    </option>
                                                                </g:if>
                                                                <g:else>
                                                                    <option value=""><li>Seleccione un valor</li>
                                                                    </option>
                                                                    <g:each var="d" in="${resultTabletListAux}"
                                                                            status="k">
                                                                        <option value="${d.id}"><li>${d.imei}</li>
                                                                        </option>
                                                                    </g:each>
                                                                </g:else>
                                                            </select>
                                                        </div>

                                                        <div id="c5" class="form-group">
                                                            <g:set var="resultLineaListAux" value="${[]}"/>
                                                            <g:if test="${inventario.Asignacion?.all == []}">
                                                                <% resultLineaListAux = inventario.Linea?.all %>
                                                            </g:if>
                                                            <g:else>
                                                                <g:each var="lineaAux" in="${inventario.Linea?.all}"
                                                                        status="i">
                                                                    <g:set var="resultLineaAux" value="${0}"/>
                                                                    <g:each var="asignacionLineaAux"
                                                                            in="${inventario.Asignacion?.all}"
                                                                            status="j">
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
                                                            <select class="selectpicker form-control" name="linea.id"
                                                                    id="linea" data-size="5" data-live-search="true">
                                                                <g:if test="${resultLineaListAux == []}">
                                                                    <option value=""><li>Todas las líneas han sido asignadas</li>
                                                                    </option>
                                                                </g:if>
                                                                <g:else>
                                                                    <option value=""><li>Seleccione un valor</li>
                                                                    </option>
                                                                    <g:each var="d" in="${resultLineaListAux}"
                                                                            status="k">
                                                                        <option value="${d.id}"><li>${d.numSim}</li>
                                                                        </option>
                                                                    </g:each>
                                                                </g:else>
                                                            </select>
                                                        </div>

                                                        <div id="paginaform">
                                                            <span class="izquierda">
                                                                <g:link action="index" controller="Asignacion">
                                                                    <button type="button" class="btn btn-success">
                                                                        <i class="glyphicon glyphicon-remove"></i>
                                                                        Cancelar
                                                                    </button>
                                                                </g:link>
                                                            </span>
                                                            <span class="izquierda">
                                                                <button type="submit" class="btn btn-primary" name="create" value="Crear" id="create">
                                                                    <i class="glyphicon glyphicon-ok"></i>
                                                                    Confirmar
                                                                </button>
                                                            </span>
                                                        </div>
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
<g:render template="/layouts/ImportacionMostrarOcultarDispositivos"/>

</body>
</html>
