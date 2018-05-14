package controller;

import java.util.ArrayList;
import java.util.List;

import business.*;
import dao.ProductoDAO;
import dto.ClienteDTO;
import dto.ProductoDTO;
import entity.ProductoEntity;
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

	public void crearCliente(ClienteDTO c) {
		/**
		 * Creo un método para que me deje crear el RO y esas otras cosas
		 */
		Cliente cliente=new Cliente();
		CuentaCorriente cc= new CuentaCorriente();
		cc.setId(c.getCuentaCorriente().getId());
		cc.setLimite(c.getCuentaCorriente().getLimite());
		cc.setSaldo(c.getCuentaCorriente().getSaldo());
		
		cliente.setCondicionEsp(c.getCondicionEsp());
		cliente.setCuentaCorriente(cc);
		cliente.setCuit(c.getCuit());
		cliente.setDireccion(c.getDireccion());
		cliente.setR_inscripto(c.isR_inscripto());
		cliente.setRazon_social(c.getRazon_social());
		cliente.setTelefono(c.getTelefono());
		
		cliente.saveOrUpdate();
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
		List<ProductoEntity> prods = new ArrayList<ProductoEntity>();
		prods=ProductoDAO.getInstance().findAll();
		
		List <ProductoDTO> prodsvo = new ArrayList<ProductoDTO>();
		
		for (ProductoEntity pe : prods)
		{ 
			ProductoDTO p= new ProductoDTO();
			p.setCodBarras(pe.getCodBarras());
			p.setMarca(pe.getMarca());
			p.setDescripcion(pe.getDescripcion());
			p.setEstado(pe.getEstado());
			prodsvo.add(p);
		}
		
		return prodsvo;
		
		//aca habria que llamar a la funcion que trae datos.
		//List<Producto> prods = ProductoDAO.getInstance.findAll()
		
	}
			

}
