package tda;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ClienteDTO;
import dto.OrdenPedidoDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import exception.ClienteException;
import exception.ProductoException;

public interface TDABusiness extends Remote {

	public abstract void crearCliente(ClienteDTO cliente) throws RemoteException, ClienteException;
	
	public abstract void crearPedido(PedidoDTO p) throws RemoteException, ClienteException, ProductoException;
	
	public abstract List<ProductoDTO> listarProductosDisponibles() throws RemoteException, ProductoException;

	public abstract ClienteDTO traerCliente(String cuit) throws RemoteException, ClienteException;

	public abstract void modificarCliente(ClienteDTO c) throws RemoteException, ClienteException;

	public abstract ProductoDTO mostrarProducto(String codbarras) throws RemoteException;

	public abstract List<UbicacionDTO> traerUbicaciones() throws RemoteException;

	public abstract void agregarAjusteStock(String producto, String tipo, UbicacionDTO u, String motivo, int cantidad,
			String responsable) throws RemoteException;

	public abstract void autorizarPedido(int nropedido) throws RemoteException;

	public abstract PedidoDTO validarCreditoCliente(int nropedido) throws RemoteException;

	public abstract void completarOP(int nro) throws RemoteException;
	
	public abstract List<UbicacionDTO> despacharPedido(PedidoDTO pdto) throws RemoteException;

	public abstract List<PedidoDTO> listarPedidosPendientes() throws RemoteException;

	public abstract List<UbicacionDTO> despacharPedido(int nro) throws RemoteException;

	public abstract List<PedidoDTO> listarPedidosADespachar() throws RemoteException;

	public abstract void noautorizarPedido(int nroPedido) throws RemoteException;

	public abstract List<OrdenPedidoDTO> traerOrdenesPendientes() throws RemoteException;


	
			
}
