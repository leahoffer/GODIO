package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controller.Controller;
import tda.TDABusiness;

public class RemoteObject extends UnicastRemoteObject implements TDABusiness {

	private static final long serialVersionUID = 1L;
	private Controller controlador;

	protected RemoteObject() throws RemoteException {
		super();
		controlador= Controller.getInstance();
	}

	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto,
			String notas) 
	{
		controlador.crearCliente(cuit, razon_social, telefono, direccion, r_inscripto, notas);
	}
	

}
