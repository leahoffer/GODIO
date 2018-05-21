package dao;

public class AlmacenDAO {

	private static AlmacenDAO instance;
	
	public static AlmacenDAO getInstance() {
		if (instance == null)
			instance = new AlmacenDAO();
		return instance;
	}
	private AlmacenDAO() {}
	
	
}
