package tda;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import business.Producto;

public interface TDABusiness extends Remote {

	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto, String notas);
	
	public void crearPedido(String cuit, List<Producto> productos);
	
	public ArrayList<Producto> listarProductosDisponibles();
	
			
}
