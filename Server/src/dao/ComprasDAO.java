package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.OrdenPedido;
import hibernate.HibernateUtil;

public class ComprasDAO {

	private static ComprasDAO instance;
	
	public static ComprasDAO getInstance() {
		if (instance == null)
			instance = new ComprasDAO();
		return instance;
	}

	public OrdenPedido buscarOPConDisponibilidad() {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.createQuery("from OrdenPedidoEntity where )
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
}
