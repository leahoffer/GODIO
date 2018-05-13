package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import tda.TDABusiness;

public class Server {

	TDABusiness RO;
	
	public static void main(String[] args) {
		new Server();
	}
	
	

	private Server()
	{
		try
		{
			System.out.println("Publishing Remote Object...");
			LocateRegistry.createRegistry(1099);
			RO = new RemoteObject();
			Naming.rebind("//localhost/RemoteObject", RO);
			System.out.println("Remote Object successfully published!");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
	
}
