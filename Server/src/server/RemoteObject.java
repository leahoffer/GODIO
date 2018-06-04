package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controller.Almacen;
import controller.Compras;
import controller.Controller;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import exception.ClienteException;
import exception.ProductoException;
import tda.TDABusiness;

public class RemoteObject extends UnicastRemoteObject implements TDABusiness {

	private static final long serialVersionUID = 1L;
	private Controller controlador;
	private Compras compras;
	private Almacen almacen;

	protected RemoteObject() throws RemoteException {
		super();
		controlador = Controller.getInstance();
		compras = Compras.getInstance();
		almacen = Almacen.getInstance();
	}

	public void crearCliente(ClienteDTO cliente) throws ClienteException 
	{
		controlador.crearCliente(cliente);
	}
	
	public List<ProductoDTO> listarProductosDisponibles() throws ProductoException{
		
		return almacen.listarProductos();
	}

	@Override
	public void crearPedido(PedidoDTO p) throws RemoteException, ClienteException, ProductoException {
		controlador.crearPedido(p);
	}

	@Override
	public ClienteDTO traerCliente(String cuit) throws RemoteException, ClienteException {
		return controlador.mostrarCliente(cuit);
	}

	@Override
	public void modificarCliente(ClienteDTO c) throws RemoteException, ClienteException {
		controlador.modificarCliente(c);
	}

	@Override
	public ProductoDTO mostrarProducto(String codbarras) throws RemoteException{
		return almacen.mostrarProducto(codbarras);
	}

	@Override
	public List<UbicacionDTO> traerUbicaciones() throws RemoteException {
		return Almacen.getInstance().mostrarUbicaciones();
	}

	@Override
	public void agregarAjusteStock(String producto, String tipo, UbicacionDTO u, String motivo, int cantidad,
			String responsable) throws RemoteException {
		Almacen.getInstance().agregarAjusteStock(producto, tipo, u, motivo, cantidad, responsable);
	}

	@Override
	public void autorizarPedido(int nropedido) throws RemoteException {
		controlador.autorizarPedido(nropedido);
	}

	@Override
	public String validarCreditoCliente(int nropedido) throws RemoteException {
		return controlador.validarCreditoCliente(nropedido);
	}

	@Override
	public void completarOP(int nro) throws RemoteException {
		compras.cerrarOP(nro);
	}

	@Override
	public void despacharPedido(PedidoDTO pdto) throws RemoteException {
		controlador.despacharPedido(pdto);
	}


	
	

}
