<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Identifíquese</title>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


    <!------ Include the above in your HEAD tag ---------->
</head>

<body style="background-color: #2b2e33">
<div class="container" style="margin-top:200px">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong>Identifíquese para continuar</strong>
                </div>

                <div class="panel-body" id="login">
                    <form action="/login/authenticate" method="POST" id="loginForm" autocomplete="off">
                        <fieldset>
                            <div class="row">
                                <div class="col-sm-12 col-md-10  col-md-offset-1 ">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                            <input class="form-control" placeholder="Usuario" name="username"
                                                   id="username" type="text" autofocus>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-lock"></i>
                                            </span>
                                            <input class="form-control" placeholder="Contraseña" name="password"
                                                   id="password" type="password" value="">
                                        </div>
                                    </div>

                                    <p id="remember_me_holder">
                                        <input type="checkbox" class="chk" name="remember-me" id="remember_me"/>
                                        <label for="remember_me">Recordar</label>
                                    </p>

                                    <div class="form-group">
                                        <input type="submit" id="submit" class="btn btn-lg btn-primary btn-block"
                                               value="Identificarse">
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>

                <div class="panel-footer ">

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    (function () {
        document.forms['loginForm'].elements['username'].focus();
    })();
</script>

<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;">
    Loading&hellip;
</div>

<script type="text/javascript" src="/assets/jquery-2.2.0.min.js?compile=false"></script>
<script type="text/javascript" src="/assets/bootstrap.js?compile=false"></script>
<script type="text/javascript" src="/assets/application.js?compile=false"></script>

</body>
</html>