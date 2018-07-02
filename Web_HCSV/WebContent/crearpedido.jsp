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

    
    <!-- BEGIN FOXYCART FILES -->
    <script src="js/foxycart_includes.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="css/foxybox.css" type="text/css" media="screen" charset="utf-8" />
    <link rel="stylesheet" href="css/theme.foxybox.css" type="text/css" media="screen" charset="utf-8" />
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
			<h1><a href="#">Das Verrückte Lagerhaus</a></h1>
		</div>
		<div id="menu">
			<ul>
				<li><a href="index.jsp" accesskey="1" title="">Home</a></li>
				<li class="active"><a href="crearpedido.jsp" accesskey="2" title="">Nuevo Pedido</a></li>
				<li><a href="autorizarpedido.jsp" accesskey="3" title="">Autorizacion</a></li>
				<li><a href="despacharpedido.jsp" accesskey="4" title="">Despachar</a></li>
				<li><a href="#" accesskey="5" title="">Orden de Compra</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="wrapper">
	<div id="welcome" class="container">
   	
<div class="title">
	  <h2>Solicitar un Pedido</h2>
		</div>

 <form action="/Web_HCSV/CrearPedido">
    <label for="cuit">CUIT</label>
    <input type="text" id="cuit" name="cuit" placeholder="Su número de CUIT" required  oninvalid="setCustomValidity('El CUIT no puede estar vacío.')" onblur="validarNombre()">
	<br></br>
	
    <label for="direccion">Dirección de Entrega</label>
    <input type="text" id="direccion" name="direccion" placeholder="La dirección donde quiere recibir el pedido">
<br></br>
 	<label for="aclaracion">Aclaraciones Especiales</label>
    <input type="text" id="aclaracion" name="aclaracion" placeholder="Aclaraciones sobre el pedido">
      <br></br>
   <label for="ctac">Cuenta Corriente</label>
    <label id = "ctac"> </label>
  <br></br>
  
  <br></br>
  
   <label for="detalles">DETALLES</label>
    	<table id="order-table">
    	    <tr>
    	         <th>Codigo de Barras</th> 
    	         <th>Cantidad Deseada</th>
    	         <th>X</th>
    	         <th>Precio/unidad</th>
    	         <th>=</th>
    	         <th style="text-align: center; padding-right: 30px;">Subtotal</th> 
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
                <td class="num-pallets"><input type="text" class="num-pallets-input" id="cantidad<%=p.getCodBarras()%>" name="cantidad<%=p.getCodBarras()%>" ></input></td>
                <td class="times" style="width: 100px;">X</td>
                <td class="price-per-pallet" style="width: 250px;">$<span><%=p.getPrecio() %></span></td>
                <td class="equals" style="width: 100px;">=</td>
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
<script type="text/javascript">
	
	function validarNombre() {
			
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	
		    	if (this.responseText == "1")
		    	{
                    alert("No existe el cliente");
                    document.getElementById("ctac").innerHTML = "";
		    
		    	}
		    	else
		    	{
		    		$("#output").removeClass(' alert-danger');
		      document.getElementById("ctac").innerHTML =
		      this.responseText;
		    	}
		    }
		  };
		  var url = "/Web_HCSV/obtenerCuentaCorriente?cuit=" + escape(document.getElementById("cuit").value);
		  xhttp.open("GET",url , true);		 	    
		  xhttp.send();
		
	}
</script>
    	<div class="clear"></div>
    	
    
    
  

</body>

</html>
