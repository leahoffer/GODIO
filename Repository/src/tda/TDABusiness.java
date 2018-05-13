package tda;

import java.rmi.Remote;

import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;

import java.rmi.RemoteException;

public interface TDABusiness extends Remote {

	public abstract void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto, String notas) throws RemoteException;
	
	public abstract void crearPedido(String cuit, List<ProductoDTO> productos) throws RemoteException;
	
	public abstract List<ProductoDTO> listarProductosDisponibles() throws RemoteException;

	
			
}
