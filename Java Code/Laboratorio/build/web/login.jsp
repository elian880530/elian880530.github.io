<%-- 
    Document   : index
    Created on : Dec 15, 2009, 2:34:11 PM
    Author     : iskael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logueandose al Sistema</title>
    </head>
    <body>
        <form action="loguearse" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Usuario</td>
                        <td>Cotrase&ntilde;a</td>
                    </tr>
                    <tr>
                        <td><input type="text" name="usuario"></td>
                        <td><input type="password" name="contrasenna"></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <
        <% String error = request.getParameter("error");
            if (error != null) {
        %>
        <h3> <%= error %> </h3>
        <%}
        %>
    </body>
</html>
