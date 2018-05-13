package controller;

import java.util.ArrayList;
import java.util.List;

import business.Producto;
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
	
	public ArrayList<Producto> listarProductos()
	{
		ArrayList<Producto> prods = new ArrayList<Producto>();
		
		Producto p = new Producto();
		p.setCodBarras("12345678");
		p.setDescripcion("Producto 1");
		p.setPrecio(100);
		p.setEstado(EstadoProducto.Activo);
			
		prods.add(p);

		p.setCodBarras("12345678");
		p.setDescripcion("Producto 2");
		p.setPrecio(100);
		p.setCantPosicion(100);
		p.setCantAComprar(100);
		p.setEstado(EstadoProducto.Activo);
				
		prods.add(p);
		
		p.setCodBarras("12345678");
		p.setDescripcion("Producto 3");
		p.setPrecio(100);
		p.setCantPosicion(100);
		p.setCantAComprar(100);
		p.setEstado(EstadoProducto.Activo);
				
		prods.add(p);
		
		return prods;
		
		//aca habria que llamar al a bd para trear los producgtos que esten activos.
		
		
	}
			

}
