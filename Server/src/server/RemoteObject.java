package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controller.Controller;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import exception.ClienteException;
import exception.ProductoException;
import tda.TDABusiness;

public class RemoteObject extends UnicastRemoteObject implements TDABusiness {

	private static final long serialVersionUID = 1L;
	private Controller controlador;

	protected RemoteObject() throws RemoteException {
		super();
		controlador= Controller.getInstance();
	}

	public void crearCliente(ClienteDTO cliente) throws ClienteException 
	{
		controlador.crearCliente(cliente);
	}
	
	public List<ProductoDTO> listarProductosDisponibles() throws ProductoException{
		
		return controlador.listarProductos();
	}

	@Override
	public void crearPedido(PedidoDTO p) throws RemoteException, ClienteException, ProductoException {
		// TODO Auto-generated method stub
		controlador.crearPedido(p);
	}

	@Override
	public ClienteDTO traerCliente(String cuit) throws RemoteException, ClienteException {
		// TODO Auto-generated method stub
		return controlador.mostrarCliente(cuit);
	}

	@Override
	public void modificarCliente(ClienteDTO c) throws RemoteException, ClienteException {
		// TODO Auto-generated method stub
		controlador.modificarCliente(c);
	}

	@Override
	public ProductoDTO mostrarProducto(String codbarras) {
		// TODO Auto-generated method stub
		return controlador.mostrarProducto(codbarras);
	}

	
	

}
