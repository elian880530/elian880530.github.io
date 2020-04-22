<%@ page import="inventario.AsignacionController;inventario.Persona;inventario.Movil;inventario.Tablet;inventario.Linea;inventario.Asignacion;inventario.Pc;inventario.Portatil"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Editar Asignaciones</title>
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
                        Editar <small>Asignaciones</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                        </li>
                        <li><a href="#">
                            <g:link action="index" controller="asignacion">
                                Listado Asignaciones
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
                            Asignaciones
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div id="edit-asignacion" class="paginaform" role="main">
                                    <g:form class="form-inline" role="form" resource="${asignacion}" method="PUT" onsubmit="return validacion()">

                                        <g:set var="pc" value="${""}"/>
                                        <g:set var="pcAsignada" value="${0}"/>
                                        <g:if test="${!Objects.equals(null,asignacion.pc)}">
                                            <g:set var="pc" value="${asignacion.pc}"/>
                                            <g:set var="pcAsignada" value="${1}"/>
                                        </g:if>

                                        <g:set var="portatil" value="${""}"/>
                                        <g:set var="portatilAsignada" value="${0}"/>
                                        <g:if test="${!Objects.equals(null,asignacion.portatil)}">
                                            <g:set var="portatil" value="${asignacion.portatil}"/>
                                            <g:set var="portatilAsignada" value="${1}"/>
                                        </g:if>

                                        <g:set var="tablet" value="${""}"/>
                                        <g:set var="tabletAsignada" value="${0}"/>
                                        <g:if test="${!Objects.equals(null,asignacion.tablet)}">
                                            <g:set var="tablet" value="${asignacion.tablet}"/>
                                            <g:set var="tabletAsignada" value="${1}"/>
                                        </g:if>

                                        <g:set var="movil" value="${""}"/>
                                        <g:set var="movilAsignada" value="${0}"/>
                                        <g:if test="${!Objects.equals(null,asignacion.movil)}">
                                            <g:set var="movil" value="${asignacion.movil}"/>
                                            <g:set var="movilAsignada" value="${1}"/>
                                        </g:if>

                                        <g:set var="linea" value="${""}"/>
                                        <g:set var="lineaAsignada" value="${0}"/>
                                        <g:if test="${!Objects.equals(null,asignacion.linea)}">
                                            <g:set var="linea" value="${asignacion.linea}"/>
                                            <g:set var="lineaAsignada" value="${1}"/>
                                        </g:if>

                                        <g:set var="persona" value="${asignacion.persona}"/>

                                        <div class="form-group">
                                            <label>Persona</label>
                                            <select class="selectpicker form-control" name="persona.id" id="persona" data-size="5" data-live-search="true">
                                                <option value="${persona.id}" selected><li>${persona.nombre+" "+persona.apellido1+" "+ persona.apellido2}</li></option>
                                                <g:each var="a" in="${Persona?.all}">
                                                    <option value="${a.id}"><li>${a.nombre+" "+a.apellido1+" "+ a.apellido2}</li></option>
                                                </g:each>
                                            </select>
                                        </div>

                                        <div class="form-group dataselect">
                                            <label>Fecha de Asignación</label>
                                            <g:datePicker type="date" name="fechaAsignacion" precision="day" value="${asignacion?.fechaAsignacion}"/>
                                        </div>

                                        <g:if test="${Objects.equals(pcAsignada,1)}">
                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionPc" onChange="oculta_selectAsignacionPc(this.value);">
                                                    <option value="c2APc" selected><li>Pc</li></option>
                                                    <option value="c3APc"><li>Portatil</li></option>
                                                    <option value="c1APc"><li>Movil</li></option>
                                                    <option value="c4APc"><li>Tablet</li></option>
                                                    <option value="c5APc"><li>Linea</li></option>
                                                </select>
                                            </div>

                                            <div id="c2APc" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" id="c2APcSelect" name="pc.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${pc.id}" selected><li>${pc.pegatina}</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3APc" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select id="c3APcSelect" class="selectpicker form-control" id="c3APcSelect" name="portatil.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1APc" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" id="c1APcSelect" name="movil.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4APc" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" id="c4APcSelect" name="tablet.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5APc" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" id="c5APcSelect" name="linea.id" data-size="5" data-live-search="true">
                                                    <option value="" selected><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionPc"/>

                                        </g:if>

                                        <g:if test="${Objects.equals(portatilAsignada,1)}">

                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionPortatil" onChange="oculta_selectAsignacionPortatil(this.value);">
                                                    <option value="c2APortatil"><li>Pc</li></option>
                                                    <option value="c3APortatil" selected><li>Portatil</li></option>
                                                    <option value="c1APortatil"><li>Movil</li></option>
                                                    <option value="c4APortatil"><li>Tablet</li></option>
                                                    <option value="c5APortatil"><li>Linea</li></option>
                                                </select>
                                            </div>

                                            <div id="c2APortatil" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" name="pc.id" id="c2APortatilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3APortatil" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select class="selectpicker form-control" name="portatil.id" id="c3APortatilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${portatil.id}" selected><li>${portatil.pegatina}</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1APortatil" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" name="movil.id" id="c1APortatilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4APortatil" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" name="tablet.id" id="c4APortatilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5APortatil" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" name="linea.id" id="c5APortatilSelect" data-size="5" data-live-search="true">
                                                    <option value="" selected><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionPortatil"/>

                                        </g:if>

                                        <g:if test="${Objects.equals(tabletAsignada,1) && Objects.equals(lineaAsignada,0)}">

                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionTablet" onChange="oculta_selectAsignacionTablet(this.value);">
                                                    <option value="c2ATablet"><li>Pc</li></option>
                                                    <option value="c3ATablet"><li>Portatil</li></option>
                                                    <option value="c1ATablet"><li>Movil</li></option>
                                                    <option value="c4ATablet" selected><li>Tablet</li></option>
                                                    <option value="c5ATablet"><li>Linea</li></option>
                                                </select>
                                            </div>

                                            <div id="c2ATablet" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" id="c2ATabletSelect" name="pc.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3ATablet" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select class="selectpicker form-control" id="c3ATabletSelect" name="portatil.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1ATablet" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" id="c1ATabletSelect" name="movil.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4ATablet" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" id="c4ATabletSelect" name="tablet.id" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${tablet.id}" selected><li>${tablet.imei}</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5ATablet" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" id="c5ATabletSelect" name="linea.id" data-size="5" data-live-search="true">
                                                    <option value="" selected><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionTablet"/>

                                        </g:if>

                                        <g:if test="${Objects.equals(tabletAsignada,1) && Objects.equals(lineaAsignada,1)}">

                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionTabletLinea" onChange="oculta_selectAsignacionTabletLinea(this.value);">
                                                    <option value="c2ATabletLinea"><li>Pc</li></option>
                                                    <option value="c3ATabletLinea"><li>Portatil</li></option>
                                                    <option value="c1ATabletLinea"><li>Movil</li></option>
                                                    <option value="c4ATabletLinea" selected><li>Tablet</li></option>
                                                    <option value="c5ATabletLinea"><li>Linea</li></option>
                                                </select>
                                            </div>

                                            <div id="c2ATabletLinea" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" name="pc.id" id="c2ATabletLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3ATabletLinea" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select class="selectpicker form-control" name="portatil.id" id="c3ATabletLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1ATabletLinea" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" name="movil.id" id="c1ATabletLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4ATabletLinea" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" name="tablet.id" id="c4ATabletLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${tablet.id}" selected><li>${tablet.imei}</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5ATabletLinea" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" name="linea.id" id="c5ATabletLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${linea.id}" selected><li>${linea.numSim}</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionTabletLinea"/>

                                        </g:if>

                                        <g:if test="${Objects.equals(movilAsignada,1) && Objects.equals(lineaAsignada,0)}">

                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionMovil" onChange="oculta_selectAsignacionMovil(this.value);">
                                                    <option value="c2AMovil"><li>Pc</li></option>
                                                    <option value="c3AMovil"><li>Portatil</li></option>
                                                    <option value="c1AMovil" selected><li>Movil</li></option>
                                                    <option value="c4AMovil"><li>Tablet</li></option>
                                                    <option value="c5AMovil"><li>Linea</li></option>
                                                </select>
                                            </div>

                                            <div id="c2AMovil" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" name="pc.id" id="c2AMovilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3AMovil" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select class="selectpicker form-control" name="portatil.id" id="c3AMovilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1AMovil" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" name="movil.id" id="c1AMovilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${movil.id}" selected><li>${movil.imei}</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4AMovil" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" name="tablet.id" id="c4AMovilSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5AMovil" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" name="linea.id" id="c5AMovilSelect" data-size="5" data-live-search="true">
                                                    <option value="" selected><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionMovil"/>

                                        </g:if>

                                        <g:if test="${Objects.equals(movilAsignada,1) && Objects.equals(lineaAsignada,1)}">

                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionMovilLinea" onChange="oculta_selectAsignacionMovilLinea(this.value);">
                                                    <option value="c2AMovilLinea"><li>Pc</li></option>
                                                    <option value="c3AMovilLinea"><li>Portatil</li></option>
                                                    <option value="c1AMovilLinea" selected><li>Movil</li></option>
                                                    <option value="c4AMovilLinea"><li>Tablet</li></option>
                                                    <option value="c5AMovilLinea"><li>Linea</li></option>
                                                </select>
                                            </div>

                                            <div id="c2AMovilLinea" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" name="pc.id" id="c2AMovilLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3AMovilLinea" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select class="selectpicker form-control" name="portatil.id" id="c3AMovilLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1AMovilLinea" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" name="movil.id" id="c1AMovilLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${movil.id}" selected><li>${movil.imei}</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4AMovilLinea" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" name="tablet.id" id="c4AMovilLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5AMovilLinea" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" name="linea.id" id="c5AMovilLineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${linea.id}" selected><li>${linea.numSim}</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionMovilLinea"/>

                                        </g:if>

                                        <g:if test="${Objects.equals(lineaAsignada,1) && Objects.equals(movilAsignada,0) && Objects.equals(tabletAsignada,0)}">

                                            <div class="form-group">
                                                <label>Tipo Dispositivo</label>
                                                <select class="form-control" name="oculMenu"
                                                        id="oculMenuAsignacionLinea" onChange="oculta_selectAsignacionLinea(this.value);">
                                                    <option value="c2ALinea"><li>Pc</li></option>
                                                    <option value="c3ALinea"><li>Portatil</li></option>
                                                    <option value="c1ALinea"><li>Movil</li></option>
                                                    <option value="c4ALinea"><li>Tablet</li></option>
                                                    <option value="c5ALinea" selected><li>Linea</li></option>
                                                 </select>
                                            </div>

                                            <div id="c2ALinea" class="form-group">
                                                <label>Pegatina Pc</label>
                                                <select class="selectpicker form-control" name="pc.id" id="c2ALineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Pc?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c3ALinea" class="form-group">
                                                <label>Pegatina Portatil</label>
                                                <select class="selectpicker form-control" name="portatil.id" id="c3ALineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Portatil?.all}">
                                                        <option value="${a.id}"><li>${a.pegatina}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c1ALinea" class="form-group">
                                                <label>IMEI Movil</label>
                                                <select class="selectpicker form-control" name="movil.id" id="c1ALineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Movil?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c4ALinea" class="form-group">
                                                <label>IMEI Tablet</label>
                                                <select class="selectpicker form-control" name="tablet.id" id="c4ALineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <g:each var="a" in="${Tablet?.all}">
                                                        <option value="${a.id}"><li>${a.imei}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <div id="c5ALinea" class="form-group">
                                                <label>Num Sim Línea</label>
                                                <select class="selectpicker form-control" name="linea.id" id="c5ALineaSelect" data-size="5" data-live-search="true">
                                                    <option value=""><li>Ninguno</li></option>
                                                    <option value="${linea.id}" selected><li>${linea.numSim}</li></option>
                                                    <g:each var="a" in="${Linea?.all}">
                                                        <option value="${a.id}"><li>${a.numSim}</li></option>
                                                    </g:each>
                                                </select>
                                            </div>

                                            <g:render template="/layouts/ImportacionModalAlerta"/>
                                            <g:render template="/layouts/ImportacionMostrarOcultarDispositivosAsignacionLinea"/>

                                        </g:if>


                                        <div id="panelbotones">
                                            <span class="izquierda">
                                                <button type="submit" class="btn btn-primary" name="update" value="Actualizar" id="update">
                                                    <i class="glyphicon glyphicon-ok"></i>
                                                    Confirmar
                                                </button>
                                            </span>

                                            <span class="izquierda">
                                                <g:link action="index" controller="Asignacion">
                                                    <button type="button" class="btn btn-success">
                                                        <i class="glyphicon glyphicon-remove"></i>
                                                        Cancelar
                                                    </button>
                                                </g:link>
                                            </span>
                                        </div>


                                    </g:form>
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
