package tda;

import java.rmi.Remote;

public interface TDABusiness extends Remote {

	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto, String notas);
	
}
