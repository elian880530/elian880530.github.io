<%-- 
    Document   : EliminarCorreo
    Created on : 18-ene-2010, 6:15:51
    Author     : Elian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Vector"%>
<%@page import="DAO.CorreoDAO"%>
<html>
    <head>
        
        <title>Eliminar Correo</title>
    </head>
        <body>

            <%
    	Vector<String> correo;
    	correo = CorreoDAO.ListaDe();

    	int cReal =correo.size();
    	int i=0;
    %>

            <form action="eliminarCorreo" method="post" name="form1" >
                <table border="0" align="center">
                  <tbody>
                    <tr>
                        <td><h1>Eliminar Correo</h1></td>
                   </tr>
                    
                    <tr>
                        <td>Seleccione el correo a eliminar</td>
                        
                        <td>

                            <select name="menu1" onchange="">
      <option selected="selected">---vacio----</option>

      <%     while (i<cReal  ) {
            
          %>          
          <option value= "<%=correo.get(i)%>" ><%=correo.get(i) %></option>
          <% 
            i++;
            }
    	%>          
        </select>
                        </td>
                    </tr>
                    
                    <tr>
                        <td> <input type="submit" value="Eliminar" name="eliminar"></td>
                        <td></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    
                </tbody>
            </table>
            </form>
        <br>
        <br>
        <br>
        <%
    	Vector<String> correomodificado;
    	correomodificado = CorreoDAO.ListaDe();

    	int Cant =correomodificado.size();
    	int j=0;
    %>
        <form action="ModificarCorreo" method="post">
            <table border="0" align="center">

                <tbody>

                    <tr>
                        <td><h1><center>Modificar Correo</center></h1></td>

                    </tr>

                    <tr>
                        <td>Elegir el destinatario a modificar</td>
                        <td>
                            <select name="menu2" onchange="">
                             <option selected="selected">---vacio----</option>

          <%     while (j<Cant  ) {

          %>
          <option value= "<%=correomodificado.get(j)%>" ><%=correomodificado.get(j) %></option>
          <%
            j++;
            }
    	%>
        </select>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td><label>Introducir el asunto a modificar</label></td>
                        <td><input type="text" size="10" name="asunto"></td>
                    </tr>
              
                    <tr>
                        <td><input type="submit" value="Enviar"></td>

                    </tr>
                  
                   
                </tbody>
            </table>

        </form>

    </body>
</html>
