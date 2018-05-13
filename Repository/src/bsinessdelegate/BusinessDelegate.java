package bsinessdelegate;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import business.Producto;
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
	
	@Override
	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto,
			String notas) throws RemoteException {
		RemoteObject.crearCliente(cuit, razon_social, telefono, direccion, r_inscripto, notas);

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
