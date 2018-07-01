package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controller.Almacen;
import controller.Compras;
import controller.Controller;
import dto.ClienteDTO;
import dto.OrdenPedidoDTO;
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
	public PedidoDTO validarCreditoCliente(int nropedido) throws RemoteException {
		return controlador.validarCreditoCliente(nropedido);
	}

	@Override
	public void completarOP(int nro) throws RemoteException {
		compras.cerrarOP(nro);
	}

	@Override
	public List<UbicacionDTO> despacharPedido(PedidoDTO pdto) throws RemoteException {
		List<UbicacionDTO> ubicaciones = controlador.despacharPedido(pdto.getNroPedido());
		return ubicaciones;
	}
	
	@Override
	public  List<PedidoDTO> listarPedidosPendientes() throws RemoteException  {
		return controlador.listarPedidosPendientes();
	}

	@Override
	public List<UbicacionDTO> despacharPedido(int nro) throws RemoteException {
		// TODO Auto-generated method stub
		List<UbicacionDTO> ubicaciones = controlador.despacharPedido(nro);
		return ubicaciones;
	}

	@Override
	public List<PedidoDTO> listarPedidosADespachar() throws RemoteException {
		// TODO Auto-generated method stub
		return controlador.listarPedidosPendientesDespacho();
	}

	@Override
	public void noautorizarPedido(int nroPedido) {
		// TODO Auto-generated method stub
		controlador.noautorizarPedido(nroPedido);
	}

	@Override
	public List<OrdenPedidoDTO> traerOrdenesPendientes() throws RemoteException {
		// TODO Auto-generated method stub
		 return Compras.getInstance().traerOrdenesPendientes();
	}


	
	

}
