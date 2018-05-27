package tda;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ClienteDTO;
import dto.DetallePedidoDTO;
import dto.ProductoDTO;
import exception.ClienteException;
import exception.ProductoException;

public interface TDABusiness extends Remote {

	public abstract void crearCliente(ClienteDTO cliente) throws RemoteException, ClienteException;
	
	public abstract void crearPedido(String cuit, List<DetallePedidoDTO> detalle) throws RemoteException;
	
	public abstract List<ProductoDTO> listarProductosDisponibles() throws RemoteException, ProductoException;

	public abstract ClienteDTO traerCliente(String cuit) throws RemoteException, ClienteException;

	public abstract void modificarCliente(ClienteDTO c) throws RemoteException, ClienteException;

	
			
}
