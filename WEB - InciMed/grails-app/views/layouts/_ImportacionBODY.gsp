<nav class="navbar navbar-default top-navbar" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${createLink(uri: '/')}"><b>Inci</b>Med</a>
    </div>
    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>
                <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li><a href="/logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
</nav>
<!--/. NAV TOP  -->
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <a href="${createLink(uri: '/')}"><i class="glyphicon glyphicon-home"></i>Principal</a>
            </li>
            <li>
                <a href="/incidenciaMedicacion/index"><i class="glyphicon glyphicon-edit"></i>Incidencias</a>
            </li>
            <li>
                <a href="#"><i class="glyphicon glyphicon-briefcase"></i> Ambito<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="#"><i class="fa fa-sitemap"></i>Gestión<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="#"><i class="fa fa-sitemap"></i>Listados<span
                                        class="fa arrow"></span></a>
                                <ul class="nav nav-four-level">
                                    <li>
                                        <a href="/nomCasa/index" class="list"><i
                                                class="fa fa-sitemap"></i>Casas
                                        </a>
                                        <a href="/nomGravedadError" class="list">
                                            <i class="fa fa-sitemap"></i>Gravedad
                                        </a>
                                        <a href="/nomMomentoDia" class="list"><i
                                                class="fa fa-sitemap"></i>Momento Día
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-sitemap"></i>Errores<span
                                        class="fa arrow"></span></a>
                                <ul class="nav nav-four-level">
                                    <li>
                                        <a href="/caracteristicasError/index" class="list"><i
                                                class="fa fa-sitemap"></i>Características</a>
                                        <a href="/causasError/index" class="list"><i class="fa fa-sitemap"></i>Causas
                                        </a>
                                        <a href="/tipoError/index" class="list"><i class="fa fa-sitemap"></i>Tipos</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
<!-- /. NAV SIDE  -->