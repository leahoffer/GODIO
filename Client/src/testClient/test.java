package testClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import businessdelegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.CuentaCorrienteDTO;
import dto.ProductoDTO;

public class test {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub

		
		System.out.println("Presione 0 para Test de RMI");
		System.out.println("Presione 1 para Ingresar Cliente");
		System.out.println("Presione 2 para Ingresar Pedido");
		String entradaTeclado = "";

        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner

        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
		
        if (entradaTeclado.equals("0"))
		{
			//Test para ver si funciona el BD y la conexión al servidor
			BusinessDelegate.getInstance().test();
			
		}
        if (entradaTeclado.equals("1"))
		{
			ClienteDTO cliente=new ClienteDTO();
			cliente.setCuentaCorriente(new CuentaCorrienteDTO());
			cliente.setCuit("Cuit");
			cliente.setDireccion("Direccion");
			cliente.setR_inscripto(true);
			cliente.setRazon_social("Razon Social");
			cliente.setTelefono("Telefono");
			cliente.getCuentaCorriente().setId("001");
			cliente.getCuentaCorriente().setLimite(10000);
			cliente.getCuentaCorriente().setSaldo(0);
			cliente.setCondicionEsp("Condicion Especial");
			BusinessDelegate.getInstance().crearCliente(cliente);
			System.out.println("Cliente Creado Con Éxito!");
		}
		
		if (entradaTeclado.equals("2"))
		{
			//Empezar generacion de pedido
			List<ProductoDTO> prods = new ArrayList<ProductoDTO>();			
			prods = BusinessDelegate.getInstance().listarProductosDisponibles();
			
			for (ProductoDTO p : prods)
			{
				System.out.println("Producto:" + p.getDescripcion() + " Cantidad:" + p.getCantPosicion());
				System.out.println();
				
				
				
			}
			System.out.println("Se va a hacer un pedido con estos materiales y cantidad 5");
			BusinessDelegate.getInstance().crearPedido("20366543598", prods);
			
		}
		
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

}
