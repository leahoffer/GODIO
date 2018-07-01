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
	<link rel="stylesheet" type="text/css" href="css/style.css" />
    
    <!-- BEGIN FOXYCART FILES -->
    <script src="https://css-tricks.foxycart.com/files/foxycart_includes.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="https://css-tricks.foxycart.com/files/foxybox.css" type="text/css" media="screen" charset="utf-8" />
    <link rel="stylesheet" href="https://css-tricks.foxycart.com/themes/standard/theme.foxybox.css" type="text/css" media="screen" charset="utf-8" />
    <!-- END FOXYCART FILES -->

    
	<script type='text/javascript' src='js/order.js'></script>
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
    <input type="text" id="cuit" name="cuit" placeholder="Su n�mero de CUIT" required  oninvalid="setCustomValidity('El CUIT no puede estar vac�o.')">
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
    
    
    	<table id="order-table">
    	    <tr>
    	         <th>Codigo de Barras</th> 
    	         <th>Cantidad Deseada</th>
    	         <th>X</th>
    	         <th>Precio por unidad</th>
    	         <th>=</th>
    	         <th style="text-align: right; padding-right: 30px;">Subtotal</th> 
    	    </tr>
    	      <%@ page import="java.util.*" %>
   <%@ page import="businessdelegate.BusinessDelegate" %>
    <%@ page import="dto.ProductoDTO" %>
  <%
  		List<ProductoDTO> productos= BusinessDelegate.getInstance().listarProductosDisponibles();
	%>
	  <%  for(ProductoDTO p: productos){ %>
            <tr class="odd">
                <td class="product-title"><%=p.getCodBarras() %></td>
                <td class="num-pallets"><input type="text" class="num-pallets-input" id="cantidad<%=p.getCodBarras()%>" name="cantidad<%=p.getCodBarras()%>"></input></td>
                <td class="times">X</td>
                <td class="price-per-pallet">$<span><%=p.getPrecio() %></span></td>
                <td class="equals">=</td>
                <td class="row-total"><input type="text" class="row-total-input" id="sparkle-row-total" disabled="disabled"></input></td>
            </tr>
        <% } %>
          
            <tr>
                <td colspan="6" style="text-align: right;">
                    Subtotal Pedido: <input type="text" class="total-box" value="$0" id="product-subtotal" disabled="disabled"></input>
                </td>
            </tr>
    	</table>
    	
     <input type="submit" value="Enviar Pedido"></input>
     
  </form>
</div>
</div>

    	<div class="clear"></div>
    	
    
    
  

</body>

</html>
