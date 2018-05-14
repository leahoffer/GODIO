package businessdelegate;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.ProductoDTO;
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
	public void crearCliente(ClienteDTO cliente) throws RemoteException {
		RemoteObject.crearCliente(cliente);

	}

	@Override
	public void crearPedido(String cuit, List<ProductoDTO> productos) throws RemoteException {
		// TODO Auto-generated method stub
		RemoteObject.crearPedido(cuit, productos);
	}

	@Override
	public List<ProductoDTO> listarProductosDisponibles() throws RemoteException {
		
		return RemoteObject.listarProductosDisponibles();
	}

}
