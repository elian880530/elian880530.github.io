<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@page import="dao.Conection"%><html>
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
<meta name="generator" content="Web Page Maker">

<style type="text/css">
/*----------Text Styles----------*/
.ws6 {font-size: 8px;}
.ws7 {font-size: 9.3px;}
.ws8 {font-size: 11px;}
.ws9 {font-size: 12px;}
.ws10 {font-size: 13px;}
.ws11 {font-size: 15px;}
.ws12 {font-size: 16px;}
.ws14 {font-size: 19px;}
.ws16 {font-size: 21px;}
.ws18 {font-size: 24px;}
.ws20 {font-size: 27px;}
.ws22 {font-size: 29px;}
.ws24 {font-size: 32px;}
.ws26 {font-size: 35px;}
.ws28 {font-size: 37px;}
.ws36 {font-size: 48px;}
.ws48 {font-size: 64px;}
.ws72 {font-size: 96px;}
.wpmd {font-size: 13px;font-family: 'Arial';font-style: normal;font-weight: normal;}
/*----------Para Styles----------*/
DIV,UL,OL /* Left */
{
 margin-top: 0px;
 margin-bottom: 0px;
}
</style>

<style type="text/css">
div#container
{
	position:relative;
	width: 1028px;
	margin-top: 0px;
	margin-left: auto;
	margin-right: auto;
	text-align:left; 
}
body {text-align:center;margin:0}
</style>
     <script src="js/fecha.js">

 </script>
  <script>
function cerrar(){

	var cierre = confirm('Estas seguro que deseas cerrar la sesión',false);
	if(cierre){
		document.closeSesion.submit();
	}else{
		return;
	}	
}

function cambiar_contraseña(a,n,c){
	window.open("changePass.html","new","width=260,height=135,menubar=no,top=250,left=390,menubar=no,scrollbars=no,toolbar=no,location=no,directories=no,resizable=no");
}
function cambiarContrasenna(a,n,c){
	document.getElementById("anterior").value = a;
	document.getElementById("nueva").value = n;
	document.getElementById("confirmar").value = c;
	document.cambiarPass.submit();	
	}
 </script>
</head>

<body link="#606060" vlink="#606060" alink="#606060" onload="mostrarHora()">
<% if (request.getSession().getAttribute("usuario") == null){
	response.sendRedirect("index.jsp");
	return;
} %>
<div style="position: absolute;left: 450px;top: 28px;z-index: 45">Bienvenido:</div><div style="position: absolute;left: 519px;top:28px;z-index: 48"><%=request.getSession().getAttribute("usuario")%></div>

<form name='closeSesion' action="closeSesion" method="post">
</form>
<div id="container">
<div style="position: absolute;left: 735px;top: 28px;z-index: 48"><a href="MostrarTodosOficiales.jsp">Trazas</a></div>
<div style="position: absolute;left: 190px;top: 25px;z-index: 47"><a href="javascript:cambiar_contraseña()"><b>Cambiar contraseña:</b></a></div>

<div id="image1" style="position:absolute; overflow:hidden; left:175px; top:39px; width:678px; height:120px; z-index:0"><img src="images/img18421203.JPG" alt="" border=0 width=678 height=120></div>

<div id="roundrect1" style="position:absolute; overflow:hidden; left:204px; top:50px; width:621px; height:98px; z-index:1"><img border=0 width="100%" height="100%" alt="" src="images/roundrect15438828.gif"></div>

<div id="image7" style="position:absolute; overflow:hidden; left:176px; top:157px; width:677px; height:400px; z-index:2"><img src="images/middle_line.jpg" alt="" border=0 width=677 height=400></div>

<div id="image6" style="position:absolute; overflow:hidden; left:176px; top:556px; width:677px; height:33px; z-index:3"><img src="images/bottom102.jpg" alt="" border=0 width=677 height=33></div>

<div id="hr1" style="position:absolute; overflow:hidden; left:185px; top:162px; width:661px; height:56px; z-index:4">
<hr size=2 width=661 color="#E0E0E0">
</div>

<div id="hr3" style="position:absolute; overflow:hidden; left:192px; top:200px; width:645px; height:16px; z-index:5">
<hr size=2 width=645 color="#C0C0C0">
</div>

<div id="shape1" style="position:absolute; overflow:hidden; left:194px; top:188px; width:643px; height:19px; z-index:6; background-image:url(images/dot.gif)"></div>

<div id="text3" style="position:absolute; overflow:hidden; left:392px; top:563px; width:306px; height:20px; z-index:7"><div class="wpmd">
<div><font class="ws8" face="Tahoma">2010 Design By L3@Corporation. All Rights Reserved.</font></div>
</div></div>

<div id="image4" style="position:absolute; overflow:hidden; left:176px; top:20px; width:677px; height:20px; z-index:8"><img src="images/img14439093.jpg" alt="" border=0 width=677 height=20></div>

<div id="roundrect2" style="position:absolute; overflow:hidden; left:294px; top:57px; width:28px; height:25px; z-index:9"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18536390.gif"></div>

<div id="roundrect3" style="position:absolute; overflow:hidden; left:276px; top:85px; width:15px; height:16px; z-index:10"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18567671.gif"></div>

<div id="roundrect5" style="position:absolute; overflow:hidden; left:244px; top:113px; width:25px; height:21px; z-index:11"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18582640.gif"></div>

<div id="roundrect6" style="position:absolute; overflow:hidden; left:277px; top:117px; width:15px; height:15px; z-index:12"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18590609.gif"></div>

<div id="roundrect8" style="position:absolute; overflow:hidden; left:335px; top:63px; width:17px; height:16px; z-index:13"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18612015.gif"></div>

<div id="roundrect7" style="position:absolute; overflow:hidden; left:330px; top:86px; width:24px; height:17px; z-index:14"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18606015.gif"></div>

<div id="roundrect9" style="position:absolute; overflow:hidden; left:321px; top:113px; width:28px; height:25px; z-index:15"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18633609.gif"></div>

<div id="roundrect4" style="position:absolute; overflow:hidden; left:297px; top:98px; width:21px; height:18px; z-index:16"><img border=0 width="100%" height="100%" alt="" src="images/roundrect18573718.gif"></div>

<div id="image2" style="position:absolute; overflow:hidden; left:515px; top:80px; width:32px; height:32px; z-index:17"><img src="images/img21750515.JPG" alt="" border=0 width=32 height=32></div>

<div id="text4" style="position:absolute; overflow:hidden; left:556px; top:88px; width:266px; height:17px; z-index:18"><div class="ws12">
<div id='div'></div>
</div></div>



            <%
            //Creamos la conexion a la base de datos
            java.sql.Statement stm = null;
            java.sql.ResultSet rs = null;
            java.sql.Connection connection = Conection.getConection();
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery("SELECT * FROM registro");
            } catch (Exception e) {
                e.printStackTrace();
            }

            %>
            <div style="position: absolute;left: 400px;top: 250px;width: 335px;height: 300px;z-index: 25;overflow: auto">
            <table  border="0" style="overflow: auto;z-index: 21">
                <tr bgcolor="white">
                    <th><b><font color="black" style="background: white">No.</font></b></th>
                    <th><b><font color="black" style="background: white"> Usuario </font></b></th>
                    <th><b><font color="black" style="background: white"> Nro Serie </font></b></th>
                    <th ><b><font color="black" style="background: white"> Id Registro </font></b></th>
                    <th><b><font color="black" style="background: white"> Estado </font></b></th>
                    
                    
                    <%
            int cont = 0;

            while (rs.next()) {
                if (cont % 2 == 0) {
                    out.println("<tr bgcolor=\"#CDCDCD\">");
                } else {
                    out.println("<tr bgcolor=\"#ffffff\">");
                }
                String usuario = rs.getString("usuario");
                String nroserie = rs.getString("nroserie");
                String idregistro = rs.getString("id");
                out.println("<td><b> " + (cont + 1) + " </b></td>");
                out.println("<td><b> " + usuario + "</b></td>");
                out.println("<td><b> " + nroserie + "</b></td>");
                out.println("<td><b> " + idregistro + "</b></td>");
                out.println("<td><b> <a href="+ "actualizar?nroserie=" + nroserie + ">" + "Entregar" + "</a> </b></td>");
                out.println("</tr>");
                cont++;
            }
            connection.close();
                    %>
                </tr>
            </table>
            </div>

</div>

<div id="text5" style="position:absolute; overflow:hidden; left:287px; top:223px; width:500px; height:18px; z-index:20"><div class="wpmd">
<div><font color="#808080"><B>Usuarios que poseen armas dentro de la prisión:</B></font></div>
</div></div>

<div id="text2" style="position:absolute; overflow:hidden; left:261px; top:183px; width:530px; height:19px; z-index:21"><div class="wpmd">
<div><font face="Arial"><B><a href="index.jsp" title="">Inicio</a></B></font><font color="#5B5B5B" face="Arial"><B>&nbsp;&nbsp;&nbsp;&nbsp; |&nbsp;&nbsp;&nbsp; </B></font><font face="Arial"><B><a href="Insertar oficial.jsp" title="">Insertar oficial</a></B></font><font color="#5B5B5B" face="Arial"><B>&nbsp;&nbsp;&nbsp; |&nbsp; </B></font><font face="Arial"><B><a href="Insertar arma.jsp" title="">Insertar arma</a></B></font><font color="#5B5B5B" face="Arial"><B>&nbsp;&nbsp;&nbsp; |&nbsp;&nbsp;&nbsp; </B></font><font face="Arial"><B><a href="Entregar arma.jsp" title="">Entregar arma</a></B></font><font color="#5B5B5B" face="Arial"><B>&nbsp;&nbsp; |&nbsp; </B></font><font face="Arial"><B><a href="Mostrar registro.jsp" title="">Mostrar registro</a></B></font></div>
</div></div>

<div id="text1" style="position:absolute; overflow:hidden; left:779px; top:25px; width:48px; height:17px; z-index:22"><div class="wpmd">
<div><font class="ws11" color="#646464" face="Arial">[ <a href="javascript:cerrar()">Salir</a> ]</font></div>
</div></div>

<div id="image3" style="position:absolute; overflow:hidden; left:827px; top:23px; width:22px; height:22px; z-index:23"><img src="images/img24688281.png" alt="" border=0 width=22 height=22></div>

<div id="image5" style="position:absolute; overflow:hidden; left:827px; top:23px; width:22px; height:22px; z-index:24"><img src="images/img24688281.png" alt="" border=0 width=22 height=22></div>

</body>
</html>
