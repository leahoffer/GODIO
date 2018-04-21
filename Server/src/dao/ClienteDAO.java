package dao;

public class ClienteDAO {

	private static ClienteDAO instance;
	
	public ClienteDAO getInstance() {
		if(instance == null)
			instance = new ClienteDAO();
		return instance;
	}
	
	
	
}
