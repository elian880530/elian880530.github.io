<%-- 
    Document   : MostrarDatos
    Created on : 18-ene-2010, 5:23:57
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

        <%

            //Creamos la conexion a la base de datos
            java.sql.Statement stm = null;
            java.sql.ResultSet rs = null;
            java.sql.Connection connection = DAO.conection.getConnection();
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery("SELECT * FROM correo");
            } catch (Exception e) {
                e.printStackTrace();
            }


         %>
        
        <table width=\"60%\" border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\">
                <tr bgcolor=\"#CC3299\">

                    <th ><b><font color=\"#ffffff\">No.</font></b></th>
                    <th ><b><font color=\"#ffffff\"> De </font></b></th>
                    <th ><b><font color=\"#ffffff\"> Para </font></b></th>
                    <th><b><font color=\"#ffffff\" > CC </font></b></th>
                    <th ><b><font color=\"#ffffff\" > Asunto </font></b></th>
                    <th ><b><font color=\"#ffffff\" > Mensaje </font></b></th>
                    <th ><b><font color=\"#ffffff\" > Accion </font></b></th>


                    <%
            int cont = 0;

            while (rs.next()) {
                if (cont % 2 == 0) {
                    out.println("<tr bgcolor=\"#CDCDCD\">");
                } else {
                    out.println("<tr bgcolor=\"#ffffff\">");
                }

                String de = rs.getString("de");
                String para = rs.getString("para");
                String cc = rs.getString("cc");
                String asunto = rs.getString("asunto");
                String mensaje = rs.getString("mensaje");

                out.println("<td><b> " + (cont + 1) + " </b></td>");

                out.println("<td><b> " + de + " </b></td>");

                out.println("<td><b> " + para + " </b></td>");

                out.println("<td><b> " + cc + "</b></td>");

                out.println("<td><b> " + asunto + "</b></td>");

                out.println("<td><b> " + mensaje + "</b></td>");

                out.println("<td><b> <a href=\"./EliminarUserTabla?de=" + de + "\">Eliminar</a></b></td>");
               
                out.println("</tr>");
                cont++;
            }
            connection.close();
                    %>
                </tr>
            </table>
    </body>
</html>
