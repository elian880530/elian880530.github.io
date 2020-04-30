<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="java.util.Vector"%>
<%@page import="DAO.CarroDAO"%>
<%@page import="DAO.VentaDAO"%>
<%@page import="DAO.VendedorDAO"%><html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en"><!-- InstanceBegin template="/Templates/plantilla.dwt" codeOutsideHTMLIsLocked="false" -->
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
.Estilo4 {color: #7993ce}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
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
<script>
function validarTexto(){
var error=false
var variable2 = document.form1.textfield2.value

var patron2 = (/^[0-9]{1,7}$/)

if(variable2.match(patron2)){
}else{
alert('El nuevo costo deve contener al menos 7 números ')
error=true
}
if(!error)
document.form1.submit();
return
}

</script>

<ul class="menu black">
  <li class="Estilo2"><a href="pagina2.htm" target="">Carros</a></li>
  <li><a href="index.htm">Principal</a></li>
  <li><a href="pagina3.htm" target="">Clientes</a></li>
  <li><a href="pagina4.htm" target="">Vendedor</a></li>
  <li><a href="pagina5.htm" target="">Información</a></li>
  <li><a href="paginaVenta.jsp">Ventas</a></li>  
</ul>
<!-- InstanceEndEditable --><!-- InstanceBeginEditable name="EditRegion2" -->
<div id="leftcolumn"> 
  <h2 class="Estilo4">&nbsp;</h2>
  <p>&nbsp;</p>
  <p><strong>Modificar Costo del Carro </strong></p>
  <form action="modificarCostoCarro" method="post" name="form1" class="Estilo3" id="form1">
    <p>Marca del Carro </p>
    
    <% 
    	Vector<String> marca;
    	marca = CarroDAO.Carrosmarca();    	
    	    	
    	int cReal =marca.size();
    	int i=0;
    %>
       <p>
      <select name="menu1" onchange="">
        <option selected="selected">-----vacio------</option>
       <%     while (i<cReal  ) {
            
          %>          
          <option value= "<%=marca.get(i)%>" ><%=marca.get(i) %></option>
          <% 
            i++;
            }
    	%>   
      </select>
    </p>
    <p>&nbsp;</p>
    <p>Nuevo Valor de Venta </p>
    <p>
      <input type="text" name="textfield2" /> 
    </p>
    <p>&nbsp;</p>
    <p>
      <input type="button" name="Submit" value="Enviar" onclick="validarTexto()" />    
        </p>
  </form>
  <p>&nbsp;</p>
  <p class="Estilo4"><strong>Eliminar un Trabajador </strong></p>
  <form action="eliminarVendedor" method="post" name="form2" class="Estilo3" id="form2">
    <p>Solap&iacute;n del Trabajador </p>
    
    <% 
    	Vector<String> solapin;
    	solapin = VendedorDAO.Vendedores();   	
    	    	
    	int Real =solapin.size();
    	int j=0;
    %>
       
    <p>
      <select name="menu2" onchange="">
        <option selected="selected">----vacio-----</option>
         <%     while (j<Real  ) {
            
          %>          
          <option value= "<%=solapin.get(j)%>" ><%=solapin.get(j) %></option>
          <% 
            j++;
            }
    	%>
        
      </select>
    </p>
    <p>&nbsp;</p>
    <p>
      <input type="submit" name="Submit2" value="Enviar" />
    </p>
  </form>
  <p>&nbsp;</p>
  <h2>&nbsp;</h2>
    </div>
<!-- InstanceEndEditable -->
<!-- End left Column -->
<!-- InstanceBeginEditable name="EditRegion1" -->
<div id="rightcolumn">
  <h3>
    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="209" height="225">
        <param name="movie" value="flash/clock12.swf" />
        <param name="quality" value="high" />
        <embed src="flash/clock12.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="209" height="225"></embed>
    </object>
  </h3>
  
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
