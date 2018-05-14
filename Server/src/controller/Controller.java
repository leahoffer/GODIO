package controller;

import java.util.ArrayList;
import java.util.List;

import business.Cliente;
import business.DetallePedido;
import business.Pedido;
import business.Producto;
import dto.ProductoDTO;
import enumeration.EstadoProducto;

public class Controller {

	private static Controller instance;
	
	public static Controller getInstance() {
		if (instance==null)
			instance = new Controller();
		return instance;
	}
	
	private Controller() {
		/**
		 * Ver si no tenemos ganas de crearlo con algo particular, o lo creamos así vacío sin nada.
		 * Pero es Singleton así que sale constructor privado
		 */
	}

	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto,
			String notas) {
		/**
		 * Creo un método para que me deje crear el RO y esas otras cosas
		 */
		
	}
	
	public void crearPedido (String cuit , ArrayList<ProductoDTO> prod)
	{
		//Como va a hacer muestra le hardcodeo la cantidad del pedido 
		//Buscar Cliente con el cuit ingresado
		Cliente cli = new Cliente();
		cli.setCuit(cuit);
		cli.setRazon_social("Mcampero");
		
		List <DetallePedido> detalles = new ArrayList<DetallePedido>();
		DetallePedido dp = new DetallePedido();
		float subtotal = 0;
		float totalbruto = 0;
		for (ProductoDTO producto: prod)
		{
			
	     Producto p = new Producto();
	     p.setCodBarras(producto.getCodBarras());
	     p.setDescripcion(producto.getDescripcion());
	     p.setPrecio(producto.getPrecio());
	     p.setEstado(producto.getEstado());
	     
	    subtotal = 5 * producto.getPrecio(); 
	     
		dp.setCantidad(5);
		dp.setSubtotal(subtotal);
		dp.setProducto(p);
		detalles.add(dp);
		
		}
	
		Pedido ped = new Pedido();
		ped.setCliente(cli);
		ped.setNroPedido(1);
		for (DetallePedido dp2 : detalles)
		{
			totalbruto = dp2.getSubtotal() + totalbruto;
		}
		ped.setTotal_bruto(totalbruto);
		
	}
	
	public List<ProductoDTO> listarProductos()
	{
		List<ProductoDTO> prods = new ArrayList<ProductoDTO>();
		
		ProductoDTO p = new ProductoDTO();
		
		p.setCodBarras("1000001");
		p.setDescripcion("Producto 1");
		p.setPrecio(100);
		p.setCantAComprar(100);
		p.setEstado(EstadoProducto.Activo);
			
		prods.add(p);
		
		p.setCodBarras("1000002");
		p.setDescripcion("Producto 2");
		p.setPrecio(100);
		p.setCantPosicion(100);
		p.setCantAComprar(100);
		p.setEstado(EstadoProducto.Activo);
				
		prods.add(p);
		
		p.setCodBarras("1000003");
		p.setDescripcion("Producto 3");
		p.setPrecio(100);
		p.setCantPosicion(100);
		p.setCantAComprar(100);
		p.setEstado(EstadoProducto.Activo);
				
		prods.add(p);
		
		return prods;
		
		//aca habria que llamar a la funcion que trae datos.
		//List<Producto> prods = ProductoDAO.getInstance.findAll()
		
	}
			

}
