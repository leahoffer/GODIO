package tda;

import java.rmi.Remote;

import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.ProductoDTO;

import java.rmi.RemoteException;

public interface TDABusiness extends Remote {

	public abstract void crearCliente(ClienteDTO cliente) throws RemoteException;
	
	public abstract void crearPedido(String cuit, List<ProductoDTO> productos) throws RemoteException;
	
	public abstract List<ProductoDTO> listarProductosDisponibles() throws RemoteException;

	
			
}
