<%-- 
    Document   : Bienvenido
    Created on : 19-ene-2010, 12:52:02
    Author     : Elian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Usted ya esta registrado:<%= request.getSession().getAttribute("usuario")%> sea usted bienvenido</h1>
    </body>
</html>
