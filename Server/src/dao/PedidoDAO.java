package dao;

import java.util.Date;

import org.hibernate.classic.Session;

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
	
	public void save(Pedido ped){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(this.toEntity(ped));
		session.getTransaction().commit();
		session.close();
	}

	private PedidoEntity toEntity(Pedido ped) {
		// TODO Auto-generated method stub
		PedidoEntity pedido = new PedidoEntity(ped.getNroPedido(), null, null, null, ped.getTotal_bruto(), ped.getEstado());
		
		
		return pedido;
	}
	
}
