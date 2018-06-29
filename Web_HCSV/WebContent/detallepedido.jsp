<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.util.*" %>
			  <%@ page import="businessdelegate.BusinessDelegate" %>
			  <%@ page import="dto.*" %>
    <% 
    
			PedidoDTO pdto = (PedidoDTO) request.getSession().getAttribute("pedido"); 


				%> 
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
<body>
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="#">Das Verrückte Lagerhaus</a></h1>
		</div>
		<div id="menu">
			<ul>
			<li><a href="index.jsp" accesskey="1" title="">Home</a></li>
				<li><a href="nuevopedido.jsp" accesskey="2" title="">Nuevo Pedido</a></li>
				<li class="active"><a href="autorizarpedido.jsp" accesskey="3" title="">Autorizacion</a></li>
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
	  <h2>Detalles del pedido</h2>
		</div>

					    <b>Saldo del cliente:</b> <%= pdto.getCliente().getCuentaCorriente().getSaldo() %><br></br>
					    	<b> Límite de cuenta:</b> <%= pdto.getCliente().getCuentaCorriente().getLimite() %><br></br>
					    	
					    	
							<table class="blueTable">
							<thead>
							<tr>
								<th>Producto</th>
								<th>Cantidad</th>
								<th>Subtotal</th>
							</tr>
							</thead>
							<tfoot>
								<tr>
								<td colspan="3">
								</td>
								</tr>
							</tfoot>
							<tbody>
							
							<% for(DetallePedidoDTO d: pdto.getDetalle()){ %>
							<td><%= d.getProducto().getDescripcion() %></td>
							<td><%= d.getCantidad() %></td>
							<td><%= d.getSubtotal() %></td>
						     <% } %>
							</tbody>
							</table>
						<% 
						String a="";
						
						if(pdto.getEstado().equals("Rechazado"))
							{a=" no";} %>

						<p>El total del pedido es de <%= pdto.getTotal_bruto() %>$. El cliente<%=a %> tiene suficiente credito para realizar el pedido.</p><br></br>
						<a href="/Web_HCSV/Autorizar">Autorizar.</a>						





	</div>
	
<div id="copyright">
	<p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
</div>
</body>
</html>
