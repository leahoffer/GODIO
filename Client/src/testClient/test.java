package testClient;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Presione 1 para Ingresar Pedido");
		String entradaTeclado = "";

        Scanner entradaEscaner = new Scanner (System.in); //Creaci�n de un objeto Scanner

        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un m�todo sobre un objeto Scanner
		
		if (entradaTeclado.equals("1"))
		{
			//Empezar generacion de pedido
			//listar
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
