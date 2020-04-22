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
                        INCIDENCIAS DE MEDICACIÓN:
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
                            <h3>${incimed.IncidenciaMedicacion?.count}</h3>
                            <strong>No. Incidencias</strong>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-blue blue">
                        <div class="panel-left pull-left blue">
                            <i class="fa fa-shopping-cart fa-5x"></i>
                        </div>
                        <div class="panel-right pull-right">
                            <h3>${incimed.NomGravedadError?.count}</h3>
                            <strong>No. Gravedad</strong>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-red red">
                        <div class="panel-left pull-left red">
                            <i class="fa fa fa-comments fa-5x"></i>
                        </div>
                        <div class="panel-right pull-right">
                            <h3>${incimed.CaracteristicasError?.count}</h3>
                            <strong>No. Características</strong>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <div class="panel panel-primary text-center no-boder bg-color-brown brown">
                        <div class="panel-left pull-left brown">
                            <i class="fa fa-users fa-5x"></i>
                        </div>
                        <div class="panel-right pull-right">
                            <h3>${incimed.CausasError?.count}</h3>
                            <strong>No. Causas</strong>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /. ROW  -->
            <div class="row">
                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Incidencias Medicación</h4>
                            <div class="easypiechart" id="easypiechart-blue"
                                 data-percent="${incimed.IncidenciaMedicacion?.count}"><span
                                    class="percent">${incimed.IncidenciaMedicacion?.count}%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Gravedad Error</h4>
                            <div class="easypiechart" id="easypiechart-orange"
                                 data-percent="${incimed.NomGravedadError?.count}"><span
                                    class="percent">${incimed.NomGravedadError?.count}%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Características Error</h4>

                            <div class="easypiechart" id="easypiechart-teal"
                                 data-percent="${incimed.CaracteristicasError?.count}"><span
                                    class="percent">${incimed.CaracteristicasError?.count}%</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-body easypiechart-panel">
                            <h4>Causas Error</h4>
                            <div class="easypiechart" id="easypiechart-red"
                                 data-percent="${incimed.CausasError?.count}"><span
                                    class="percent">${incimed.CausasError?.count}%</span>
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
<g:render template="/layouts/ImportacionJS"/>
<g:render template="/layouts/ImportacionGraficosJS"/>
</body>
</html>