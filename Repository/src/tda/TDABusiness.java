package tda;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TDABusiness extends Remote {

	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto, String notas) throws RemoteException;
	
}
