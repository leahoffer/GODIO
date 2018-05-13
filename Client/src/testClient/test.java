package testClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bsinessdelegate.BusinessDelegate;
import dto.ProductoDTO;

public class test {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub

		
		System.out.println("Presione 1 para Ingresar Pedido");
		String entradaTeclado = "";

        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner

        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
		
		if (entradaTeclado.equals("1"))
		{
			//Empezar generacion de pedido
			List<ProductoDTO> prods = new ArrayList<ProductoDTO>();			
			prods = BusinessDelegate.getInstance().listarProductosDisponibles();
			
			for (ProductoDTO p : prods)
			{
				System.out.println("Producto:" + p.getDescripcion() + " Cantidad:" + p.getCantPosicion());
				System.out.println();
				System.out.println("Se va a hacer un pedido con estos materiales y cantidad 5");
				
				BusinessDelegate.getInstance().crearPedido("20366543598", prods);
			}
			
			
			
		}
		else
			System.out.println("Error, numero mal ingresado"); 
		
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

}
