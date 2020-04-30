<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="DAO.ClienteDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="Domain.Cliente"%>
<%@page import="DAO.CarroDAO"%>
<%@page import="DAO.VendedorDAO"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en"><!-- InstanceBegin template="/Templates/plantilla.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<!-- InstanceBeginEditable name="doctitle" -->
<title>new</title>
<!-- InstanceEndEditable --><link rel="stylesheet" href="file:///D|/Elian/Trabajos/plantillas%20web/Plantillas%20descargas/blackandblues/blackandblues/1.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="author" content="coollew - http://www.opendesigns.org/profile/?user=coollew" />
<!-- InstanceBeginEditable name="head" -->
<style type="text/css">
<!--
.Estilo3 {	color: #FFFFFF;
	font-weight: bold;
}
-->
</style>

<!-- InstanceEndEditable -->
<style type="text/css">
<!--
.Estilo1 {
	color: #0066FF;
	font-weight: bold;
	font-style: italic;
}
.Estilo2 {color: #fff}
-->
</style>
</head>
<body>
<div id="parent">
<div id="outer">      
<img src="omagenes\banner del curso.JPG" width="728" height="186" /><!-- InstanceBeginEditable name="EditRegion3" -->
<ul class="menu black">
  <li class="Estilo2"><a href="pagina2.htm" target="">Carros</a></li>
  <li><a href="index.htm">Principal</a></li>
  <li><a href="pagina3.htm" target="">Clientes</a></li>
  <li><a href="pagina4.htm" target="">Vendedor</a></li>
  <li><a href="pagina5.jsp">Información</a></li>
  <li><a href="pagina6.htm" target="">Ventas</a></li>  
</ul>
<!-- InstanceEndEditable --><!-- InstanceBeginEditable name="EditRegion2" -->
<div id="leftcolumn"> <img src="omagenes/kryjr.jpg" alt="#" width="212" height="70" class="left" />
    <h2>Delta Blues</h2>
    <p> Mercedes-Benz Cars, divisi&oacute;n del Grupo Daimler compuesta por las marcas Mercedes-Benz, Smart y Maybach, registr&oacute; un volumen de ventas de 62.200 unidades en todo el mundo durante el pasado mes de enero, lo que supone un descenso del 31,2% frente a los datos del mismo mes de 2008, inform&oacute; hoy la compa&ntilde;&iacute;a. </p>
    <p>STUTTGART (ALEMANIA), 6 (EUROPA PRESS) </p>
    <p>La multinacional alemana explic&oacute; que este retroceso de sus matriculaciones mundiales se ha producido por la reducci&oacute;n experimentada en la demanda en sus principales mercados, a causa de la actual situaci&oacute;n econ&oacute;mica. </p>
    <p>Por marcas, Mercedes-Benz matricul&oacute; 53.900 veh&iacute;culos el mes pasado en todo el mundo, lo que representa un descenso del 34,5% en comparaci&oacute;n con las 82.300 unidades vendidas en el mismo mes del ejercicio precedente. </p>
    <p>Smart, por su parte, registr&oacute; un nivel de ventas de 8.300 unidades el mes pasado, lo que se traduce en un incremento del 3,1% respecto a las matriculaciones de la firma en el primer mes del a&ntilde;o anterior. </p>
    <p>Por regiones, Mercedes-Benz Cars redujo un 32,3% sus ventas en Europa Occidental en enero, hasta 34.100 unidades, mientras que sus entregas en Norteam&eacute;rica disminuyeron un 32,7%, hasta 14.000 unidades. </p>
    <p>Asimismo, la multinacional automovil&iacute;stica alemana alcanz&oacute; un volumen de ventas de 8.900 unidades en la regi&oacute;n de Asia-Pac&iacute;fico, lo que se traduce en una reducci&oacute;n del 24,3%. Las matriculaciones de la firma en China crecieron un 3,9%, hasta 3.400 unidades. </p>
    <blockquote>
      <div id="endquote"> </div>
    </blockquote>
    </div>
<!-- InstanceEndEditable -->
<!-- End left Column -->
<!-- InstanceBeginEditable name="EditRegion1" -->
<div id="rightcolumn">
  <h3>Registro de Ventas </h3>
  
  <form name="form1" id="form1" method="post" action="insertarVenta">
    <p>
      <center class="Estilo3">
      Solap&iacute;n del Vendedor 
      </center>
    </p>
    <p>
    <% 
    	Vector<String> vendedor;
    	vendedor = VendedorDAO.Vendedores();    	
    	    	
    	int cReal =vendedor.size();
    	int i=0;
    %>
      <center>
      <select name="menu1" onchange="">
      <option selected="selected">---vacio----</option>
       <%     while (i<cReal  ) {
            
          %>          
          <option value= "<%=vendedor.get(i)%>" ><%=vendedor.get(i) %></option>
          <% 
            i++;
            }
    	%>          
        </select>
      </center>
    </p>
    <p>
      <center class="Estilo3">
        <strong> CI del Cliente </strong>
      </center>
    </p>
    <p>
    <% 
    	Vector<String> cliente;
    	cliente = ClienteDAO.Clientes();   	
    	    	
    	cReal =cliente.size();
    	i=0;
    %>
      <center>
         <select name="menu2" onchange="">
      <option selected="selected">---vacio----</option>
       <%     while (i<cReal  ) {
            
          %>          
          <option value="<%=cliente.get(i)%>" > <%=cliente.get(i) %> </option>
          <% 
            i++;
            }
    	%>          
        </select>
      </center>
    </p>
    <p>
      <center class="Estilo3">
      ID del Carro
      </center>
    </p>
    <p>
     <% 
    	Vector<String> carros;
    	carros = CarroDAO.Carros();   	
    	
    	cReal =carros.size();
    	i=0;
    %>
      <center>
         <select name="menu3" onchange="">
      <option selected="selected">---vacio----</option>
       <%     while (i<cReal  ) {
            
          %>          
          <option value= "<%=carros.get(i)%>" > <%=carros.get(i)%> </option>
          <% 
            i++;
            }
    	%>          
        </select>
      </center>
    </p>
    <p>
      <center class="Estilo3">
        <input type="submit" name="Submit" value="Enviar" />
      </center>
    </p>
    <p>&nbsp;</p>
  </form>
  <p>&nbsp; </p>
  </div>
<!-- InstanceEndEditable -->
<!-- End Right Column -->
<div id="footer">
</div>			 		 
</div>
<!-- End outer -->
</div>
<!-- End parent -->
</body>
<!-- InstanceEnd --></html>
    