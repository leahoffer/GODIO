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
<script src="jquery-3.3.1.min.js"></script>
</head>

<body>
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Das Verrückte Lagerhaus</a></h1>
			
		</div>
		<div id="menu">
			<ul>
			<li><a href="index.jsp" accesskey="1" title="">Home</a></li>
				<li><a href="crearpedido.jsp" accesskey="2" title="">Nuevo Pedido</a></li>
				<li><a href="autorizarpedido.jsp" accesskey="3" title="">Autorizacion</a></li>
				<li class="active"><a href="despacharpedido.jsp" accesskey="4" title="">Despachar</a></li>
				<li><a href="cerrarop.jsp" accesskey="5" title="">Orden de Compra</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="wrapper">
	<div id="banner" class="container"></div>
	<div id="welcome" class="container">
    	
		<div class="title">
	  <h2>Despacho</h2>
		</div>
		

		<table class="blueTable">
			<thead>
				<tr>
				<th>Cliente</th>
				<th>Pedido</th>
				<th></th>
				</tr>
			</thead>
			<tfoot>
				<tr>
				<td colspan="3">
				</td>
				</tr>
			</tfoot>
			<tbody>
			
			  <%@ page import="java.util.*" %>
			  <%@ page import="businessdelegate.BusinessDelegate" %>
			  <%@ page import="dto.*" %>
			  <%
				List<PedidoDTO> pedidos= BusinessDelegate.getInstance().listarPedidosADespachar();
			 	 for(PedidoDTO p: pedidos){ %>
				<tr>
							<td>CUIT <%= p.getCliente().getCuit() %> - <%= p.getCliente().getRazon_social() %></td>
							<td>NRO <%= p.getNroPedido() %> - DIR <%= p.getDir_entrega() %> - TOTAL <%= p.getTotal_bruto() %>$</td>
							<td><form action="/Web_HCSV/Despachar">
									<input type="hidden" name="nroPedido" value="<%= p.getNroPedido() %>"> 
								    <input type="submit" id="boton" value="Despachar">
								    
								    </form></td>
			 	 </tr>
			 	    <% } %>
			</tbody>
		</table> 	 











	</div>
<div id="copyright">
	<p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
</div>
</body>
</html>
