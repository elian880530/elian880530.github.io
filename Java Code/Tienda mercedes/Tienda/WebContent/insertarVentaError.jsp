<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Vector"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="white">

<%String [] aux= ((String[])request.getSession().getAttribute("mensaje"));
for(int i= 0;i<aux.length;i++){%>
<%=aux[i] %>	
<% }%>



<a href="pagina6.html">siguiente</a>

</body>
</html>