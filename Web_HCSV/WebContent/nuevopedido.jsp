<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : Undeviating 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20140322

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900|Quicksand:400,700|Questrial" rel="stylesheet" />
<link href="css/default.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" media="all" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>

<style>
input[type=text], select {
    width: 100%;
    padding: 8px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}


</style>

<body>
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Das Verr�ckte Lagerhaus</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li><a href="index.jsp" accesskey="1" title="">Home</a></li>
				<li class="active"><a href="nuevopedido.jsp" accesskey="2" title="">Nuevo Pedido</a></li>
				<li><a href="autorizarpedido.jsp" accesskey="3" title="">Autorizacion</a></li>
				<li><a href="#" accesskey="4" title="">Careers</a></li>
				<li><a href="#" accesskey="5" title="">Contact Us</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="wrapper">
	<div id="banner" class="container"><img src="images/banner.jpg" width="1200" height="500" alt="" /></div>
	<div id="welcome" class="container">
    	
<div class="title">
	  <h2>Solicitar un Pedido</h2>
		</div>
<form action="/Web_HCSV/CrearPedido">
    <label for="cuit">CUIT</label>
    <input type="text" id="cuit" name="cuit" placeholder="Su n�mero de CUIT">
	<br></br>
    <label for="direccion">Direcci�n de Entrega</label>
    <input type="text" id="direccion" name="direccion" placeholder="La direcci�n donde quiere recibir el pedido">
<br></br>
 	<label for="aclaracion">Aclaraciones Especiales</label>
    <input type="text" id="aclaracion" name="aclaracion" placeholder="Aclaraciones sobre el pedido">
  <br></br>
  <br></br>
   <label for="detalles">DETALLES</label>
   <hr></hr>
    <br></br>
  <div class="two-col">
    <div class="col1">
    <%@ page import="java.util.*" %>
   <%@ page import="businessdelegate.BusinessDelegate" %>
    <%@ page import="dto.ProductoDTO" %>
  <%
	
  		List<ProductoDTO> productos= BusinessDelegate.getInstance().listarProductosDisponibles();
 			
	%>
        <label for="producto">Producto</label>
        <select id="producto" name="producto">
      <%  for(ProductoDTO p: productos){ %>
            <option><%= p.getCodBarras() %></option>
        <% } %>
    </select>
    </div>

    <div class="col2">
        <label for="cantidad">Cantidad</label>
        <input id="cantidad" name="cantidad" type="text">
    </div>
</div>
    <br></br>
    <input type="submit" value="Enviar Pedido">
  </form>
	</div>
	
<div id="copyright">
	<p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
</div>
</body>
</html>
