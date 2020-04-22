<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Principal</title>
    <g:render template="/layouts/ImportacionCSS"/>
    <g:render template="/layouts/ImportacionGraficosCSS"/>
</head>

<body>
<div id="wrapper">

    <g:render template="/layouts/ImportacionBODY"/>

    <div id="page-wrapper">
        <div id="page-inner">

            <div class="row">
                <div class="col-md-12">
                    <h1 class="page-header">
                        SISTEMA DE GESTIÓN DE INVENTARIOS:
                    </h1>
                </div>
            </div>

            <!-- /. ROW  -->

            <div class="row">
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-green green">
                        <div class="panel-left pull-left green">
                            <i class="fa fa-bar-chart-o fa-5x"></i>

                        </div>

                        <div class="panel-right pull-right">

                            <h3>${inventario.Asignacion?.count}</h3>
                            <strong>No. Asignaciones</strong>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-blue blue">
                        <div class="panel-left pull-left blue">
                            <i class="fa fa-shopping-cart fa-5x"></i>
                        </div>

                        <div class="panel-right pull-right">
                            <h3>${inventario.NomUbicacion?.count}</h3>
                            <strong>No. Ubicaciones</strong>

                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-red red">

                        <div class="panel-left pull-left red">
                            <i class="fa fa fa-comments fa-5x"></i>
                        </div>

                        <div class="panel-right pull-right">
                            <h3>${inventario.Dispositivo?.count}</h3>
                            <strong>No. Dispositivos</strong>

                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-brown brown">

                        <div class="panel-left pull-left brown">
                            <i class="fa fa-users fa-5x"></i>
                        </div>

                        <div class="panel-right pull-right">
                            <h3>${inventario.Persona?.count}</h3>
                            <strong>No. Usuarios</strong>
                        </div>

                    </div>
                </div>
            </div>

            <!-- /. ROW  -->

            <div class="row">
                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Moviles</h4>
                            <%
                                int var1 = 1
                                if (inventario.Movil?.count != 0 && inventario.Dispositivo?.count != 0) {
                                    var1 = (inventario.Movil?.count * 100) / inventario.Dispositivo?.count
                                }
                            %>
                            <div class="easypiechart" id="easypiechart-blue" data-percent="${var1}"><span
                                    class="percent">${var1}%</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Tablet</h4>
                            <%
                                int var2 = 1
                                if (inventario.Tablet?.count != 0 && inventario.Dispositivo?.count != 0) {
                                    var2 = (inventario.Tablet?.count * 100) / inventario.Dispositivo?.count
                                }
                            %>
                            <div class="easypiechart" id="easypiechart-orange" data-percent="${var2}"><span
                                    class="percent">${var2}%</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>PC</h4>
                            <%
                                int var3 = 1
                                if (inventario.Pc?.count != 0 && inventario.Dispositivo?.count != 0) {
                                    var3 = (inventario.Pc?.count * 100) / inventario.Dispositivo?.count
                                }
                            %>
                            <div class="easypiechart" id="easypiechart-teal" data-percent="${var3}"><span
                                    class="percent">${var3}%</span>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Portátiles</h4>
                            <%
                                int var4 = 1
                                if (inventario.Portatil?.count != 0 && inventario.Dispositivo?.count != 0) {
                                    var4 = (inventario.Portatil?.count * 100) / inventario.Dispositivo?.count
                                }
                            %>
                            <div class="easypiechart" id="easypiechart-red" data-percent="${var4}"><span
                                    class="percent">${var4}%</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- /. ROW  -->

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            No. Artículos Asignados
                        </div>
                        <div class="panel-body">
                            <div id="morris-line-chart"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- /. ROW  -->

            <div class="row">
                <div class="col-md-9 col-sm-12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            No. Asignaciones por Departamentos
                        </div>

                        <div class="panel-body">
                            <div id="morris-bar-chart"></div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Existencia en Inventario
                        </div>

                        <div class="panel-body">
                            <div id="morris-donut-chart"></div>
                        </div>
                    </div>
                </div>

            </div>

            <!-- /. ROW  -->

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Líneas y Móviles
                        </div>

                        <div class="panel-body">
                            <div id="morris-area-chart"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- /. ROW  -->

            <g:render template="/layouts/ImportacionFirma"/>

        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->

</div>

<g:render template="/layouts/ImportacionJS"/>
<g:render template="/layouts/ImportacionGraficosJS"/>

</body>

</html>