<%-- 
    Document   : index
    Created on : 14-ene-2010, 5:17:36
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
    <body background="blue">

        <form action="InsertarDatosCorreo" method="post" name="formulario" id="formulario">
            <table border="0" align="center">
                <tr>
                    <td>De:</td>
                </tr>

                <tr>
                    <td><input type="text" name="De" value="" size="20"  /></td>
                </tr>

                <tr>
                    <td>Para:</td>
                </tr>

                <tr>
                    <td><input type="text" name="Para" value="" size="20"  /></td>
                </tr>


                <tr>
                    <td>CC:</td>
                </tr>

                <tr>
                    <td><input type="text" name="CC" value="" size="20" /></td>
                </tr>

                <tr>
                    <td>Asunto:</td>
                </tr>
                
                <tr>
                    <td><input type="text" name="Asunto" value="" size="20" /></td>
                </tr>

                <tr>
                    <td>
                        <textarea name="Mensaje" cols="30" rows="10"></textarea>
                        <p><input type="submit" value="Enviar" name="Enviar" />&nbsp;<input type="reset" value="Cancelar" name="Cancelar" /></p>
                    </td>
                </tr>

                <tr>
                    <td><a href="./EliminarCorreo.jsp">Eliminar y Modificar</a></td>
                </tr>

                <tr>
                    <td><a href="InsertarUsuario.jsp">Insertar Usuario</a></td>
                </tr>
                
            </table>

        </form>


    </body>
</html>
