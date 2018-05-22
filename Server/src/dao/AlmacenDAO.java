package dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.OrdenPedido;
import business.Producto;
import business.Reserva;
import entity.ProductoEntity;
import entity.ReservaEntity;
import hibernate.HibernateUtil;

public class AlmacenDAO {

	private static AlmacenDAO instance;
	
	public static AlmacenDAO getInstance() {
		if (instance == null)
			instance = new AlmacenDAO();
		return instance;
	}
	private AlmacenDAO() {}
	public void create(Reserva reserva) {
		try 
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.save(reservaToEntity(reserva));
			s.getTransaction().commit();
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	//NO ASIGNA NUMERO POR LAS DUDAS PORQUE CUANDO CREO NO TIENE NUMERO AAAA
	private ReservaEntity reservaToEntity(Reserva r) {
		ReservaEntity re = new ReservaEntity();
		re.setCantidad(r.getCantidad());
		re.setCompleta(r.isCompleta());
		re.setFecha(r.getFecha());
		re.setPedido(PedidoDAO.getInstance().pedidoToEntity(r.getPedido()));
		re.setProducto(ProductoDAO.getInstance().productoToEntity(r.getProducto()));
		return re;
	}

	
	
	
}
