<nav class="navbar navbar-default top-navbar" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${createLink(uri: '/')}"><b>Item</b>Box</a>
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
                <a href="#"><i class="glyphicon glyphicon-briefcase"></i> Ambito<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="#"><i class="fa fa-sitemap"></i>Informática<span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level">
                            <li>
                                <a href="#"><i class="fa fa-sitemap"></i>Listados Gestión<span
                                        class="fa arrow"></span></a>
                                <ul class="nav nav-four-level">
                                    <li>
                                        <a href="/nomCantAlmacenamiento/index" class="list"><i
                                                class="fa fa-sitemap"></i>Almacenamiento</a>
                                        <a href="/nomCantRam/index" class="list"><i class="fa fa-sitemap"></i>Ram
                                        </a>
                                        <a href="/nomCuentaFacturacion/index" class="list"><i
                                                class="fa fa-sitemap"></i>Facturación</a>
                                        <a href="/nomMarcaMovil/index" class="list"><i
                                                class="fa fa-sitemap"></i>Marcas Móviles
                                        </a>
                                        <a href="/nomMarcaPc/index" class="list"><i class="fa fa-sitemap"></i>Marcas Pc
                                        </a>
                                        <a href="/nomMarcaPortatil/index" class="list"><i
                                                class="fa fa-sitemap"></i>Marcas Portátiles
                                        </a>
                                        <a href="/nomMarcaTablet/index" class="list"><i
                                                class="fa fa-sitemap"></i>Marcas Tablet
                                        </a>
                                        <a href="/nomProveedor/index" class="list"><i
                                                class="fa fa-sitemap"></i>Proveedores</a>
                                        <a href="/nomTarificacion/index" class="list"><i
                                                class="fa fa-sitemap"></i>Tarifas</a>
                                        <a href="/nomTipoMicro/index" class="list"><i
                                                class="fa fa-sitemap"></i>Micros</a>
                                        <a href="/nomUbicacion/index" class="list"><i
                                                class="fa fa-sitemap"></i>Ubicación</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-sitemap"></i>Listados Artículos<span
                                        class="fa arrow"></span></a>
                                <ul class="nav nav-four-level">
                                    <li>
                                        <a href="/movil/index" class="list"><i class="fa fa-sitemap"></i>Móviles</a>
                                        <a href="/pc/index" class="list"><i class="fa fa-sitemap"></i>PC</a>
                                        <a href="/portatil/index" class="list"><i class="fa fa-sitemap"></i>Portátiles</a>
                                        <a href="/linea/index" class="list"><i class="fa fa-sitemap"></i>Líneas</a>
                                        <a href="/tablet/index" class="list"><i class="fa fa-sitemap"></i>Tablet</a>
                                    </li>
                                </ul>
                            </li>

                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-sitemap"></i> Jardinería<span class="fa arrow"></span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-sitemap"></i> Mantenimiento<span class="fa arrow"></span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-sitemap"></i> Restauración<span class="fa arrow"></span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-sitemap"></i> Limpieza<span class="fa arrow"></span></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="/asignacion/index"><i class="glyphicon glyphicon-edit"></i>Asignaciones</a>
            </li>
            <li>
                <a href="/historico/index"><i class="glyphicon glyphicon-lock"></i>Seguridad</a>
            </li>
            <li>
                <a href="/persona/index"><i class="fa fa-user fa-fw"></i>Usuarios</a>
            </li>
        </ul>
    </div>

</nav>
<!-- /. NAV SIDE  -->