package businessdelegate;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import dto.ClienteDTO;
import dto.OrdenPedidoDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import exception.ClienteException;
import exception.ProductoException;
import tda.TDABusiness;

public class BusinessDelegate implements TDABusiness {

	private static BusinessDelegate instance;
	
	private TDABusiness RemoteObject;
	
	public static BusinessDelegate getInstance(){
		if (instance == null)
			instance = new BusinessDelegate();
		return instance;
	}
	
	private BusinessDelegate()
	{
		try
		{
			RemoteObject = (TDABusiness)Naming.lookup("//localhost/RemoteObject");
			System.out.println("Connected to Remote Object!");
		}
		catch (Exception e)
		{
			System.out.println("Error fetching Remote Object");
			e.printStackTrace();
		}
	}
	
	public void test() {}
	@Override
	public void crearCliente(ClienteDTO cliente) throws RemoteException, ClienteException {
		RemoteObject.crearCliente(cliente);

	}

	@Override
	public void crearPedido(PedidoDTO p) throws RemoteException, ClienteException, ProductoException {
		RemoteObject.crearPedido(p);
	}

	@Override
	public List<ProductoDTO> listarProductosDisponibles() throws RemoteException, ProductoException {
		
		try {
			return RemoteObject.listarProductosDisponibles();
		} catch (ProductoException e) {
			throw new  ProductoException("Error al buscar Productos");
			
		}
	}

	public ClienteDTO traerCliente(String cuit) throws RemoteException, ClienteException {
		return RemoteObject.traerCliente(cuit);
	}

	public void modificarCliente(ClienteDTO c) throws RemoteException, ClienteException {
		RemoteObject.modificarCliente(c);
	}

	public ProductoDTO mostrarProducto(String codbarras) throws RemoteException {
		return RemoteObject.mostrarProducto(codbarras);
	}

	public List<UbicacionDTO> traerUbicaciones() throws RemoteException {
		return RemoteObject.traerUbicaciones();
	}

	public void agregarAjusteStock(String producto, String tipo, UbicacionDTO u, String motivo, int cantidad,
			String responsable) throws RemoteException {
		RemoteObject.agregarAjusteStock(producto, tipo, u, motivo, cantidad, responsable);
	}

	public void autorizarPedido(int nropedido) throws RemoteException {
		RemoteObject.autorizarPedido(nropedido);
	}

	public PedidoDTO validarCreditoCliente(int nropedido) throws RemoteException {
		return RemoteObject.validarCreditoCliente(nropedido);
	}

	public void completarOP(int nro) throws RemoteException {
		RemoteObject.completarOP(nro);
	}
	
	public List<PedidoDTO> listarPedidosPendientes() throws RemoteException {
		return RemoteObject.listarPedidosPendientes();
	}


	public List<PedidoDTO> listarPedidosADespachar() throws RemoteException {
		return RemoteObject.listarPedidosADespachar();
	}

	
	public List<UbicacionDTO> despacharPedido(PedidoDTO pdto) throws RemoteException {
		List<UbicacionDTO> ubicaciones = RemoteObject.despacharPedido(pdto);
		return ubicaciones;
		
	}

	public List<UbicacionDTO> despacharPedido(int nro) throws RemoteException {
		// TODO Auto-generated method stub
		List<UbicacionDTO> ubicaciones = RemoteObject.despacharPedido(nro);
		return ubicaciones;
	}

	public void noautorizarPedido(int nroPedido) throws RemoteException {
		// TODO Auto-generated method stub
		RemoteObject.noautorizarPedido(nroPedido);
	}

	public List<OrdenPedidoDTO> traerOrdenesPendientes() throws RemoteException
	{
		return RemoteObject.traerOrdenesPendientes();
	}

}
