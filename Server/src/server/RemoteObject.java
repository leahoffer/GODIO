package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controller.Controller;
import dto.ClienteDTO;
import dto.DetallePedidoDTO;
import dto.ProductoDTO;
import exception.ProductoException;
import tda.TDABusiness;

public class RemoteObject extends UnicastRemoteObject implements TDABusiness {

	private static final long serialVersionUID = 1L;
	private Controller controlador;

	protected RemoteObject() throws RemoteException {
		super();
		controlador= Controller.getInstance();
	}

	public void crearCliente(ClienteDTO cliente) 
	{
		controlador.crearCliente(cliente);
	}
	
	public List<ProductoDTO> listarProductosDisponibles() throws ProductoException{
		
		return controlador.listarProductos();
	}

	@Override
	public void crearPedido(String cuit, List<DetallePedidoDTO> detalle) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	
	

}
