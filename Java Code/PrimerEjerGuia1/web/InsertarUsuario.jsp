<%-- 
    Document   : InsertarUsuario
    Created on : 19-ene-2010, 11:58:42
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
        <form action="InsertarUsuario" method="post">
             <table border="0" align="center">
            
            <tbody>
                <tr>
                    <td><h1>Insertar Usuario</h1></td>
                </tr>
                <tr>
                    <td><center>Usuario</center></td>
                    <td><center><input type="text" name="usuario" size="10" ></center></td>
                </tr>
                
                <tr>
                    <td><center>Password</center></td>
                    <td><center><input type="password" name="password" size="10"></center></td>
                </tr>
                
                <tr>
                    <td><center><input type="submit" value="Enviar"></center></td>
                </tr>
            </tbody>
        </table>
        </form>

         <form action="EntrarBienvenido" method="post">
             <table border="0" align="center">

            <tbody>

                <tr>
                    <td><h1>Entrar a Bienvenidos</h1></td>
                </tr>
                <tr>
                    <td><center>Usuario</center></td>
                    <td><center><input type="text" name="usuario" size="10" ></center></td>
                </tr>

                <tr>
                    <td><center>Password</center></td>
                    <td><center><input type="password" name="password" size="10"></center></td>
                </tr>

                <tr>
                    <td><center><input type="submit" value="Enviar"></center></td>
                </tr>
            </tbody>
        </table>
        </form>
    </body>
</html>
