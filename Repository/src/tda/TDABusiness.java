package tda;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ClienteDTO;
import dto.DetallePedidoDTO;
import dto.ProductoDTO;
import exception.ProductoException;

public interface TDABusiness extends Remote {

	public abstract void crearCliente(ClienteDTO cliente) throws RemoteException;
	
	public abstract void crearPedido(String cuit, List<DetallePedidoDTO> detalle) throws RemoteException;
	
	public abstract List<ProductoDTO> listarProductosDisponibles() throws RemoteException, ProductoException;

	
			
}
