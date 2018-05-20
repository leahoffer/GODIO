package dao;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import business.Cliente;
import business.Pedido;
import entity.ClienteEntity;
import entity.PedidoEntity;
import enumeration.EstadoPedido;
import hibernate.HibernateUtil;

public class PedidoDAO {

	private static PedidoDAO instancia;
	
	public  static PedidoDAO getInstance() {
		if (instancia == null)
			instancia = new PedidoDAO();
		return instancia;
	}
	
	public void createOrUpdate(Pedido ped){
		
		try {
			PedidoEntity pe = pedidoToEntity(ped);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			System.out.println("Guardando Pedido...");
			s.saveOrUpdate(pe);
			s.getTransaction().commit();
			System.out.println("Pedido guardado!");
			s.flush();
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error al guardar el Cliente");
			e.printStackTrace();
		}
	}
	
	

	private PedidoEntity pedidoToEntity(Pedido ped) {
		// TODO Auto-generated method stub

		ClienteEntity cliente = new ClienteEntity();
		return null;
	
	}
	

}
