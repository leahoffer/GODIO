package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.MovimientoReserva;
import business.OrdenPedido;
import business.Producto;
import entity.MovimientoReservaEntity;
import entity.OrdenPedidoEntity;
import enumeration.EstadoOP;
import hibernate.HibernateUtil;

public class ComprasDAO {

	private static ComprasDAO instance;
	
	public static ComprasDAO getInstance() {
		if (instance == null)
			instance = new ComprasDAO();
		return instance;
	}

	public void createOP(OrdenPedido op) {
		try
		{
			OrdenPedidoEntity ope = new OrdenPedidoEntity();
			ope.setCantidadPedida(op.getCantidadPedida());
			ope.setEstado(op.getEstado().toString());
			ope.setPedidoOrigen(PedidoDAO.getInstance().pedidoToEntity(op.getPedidoOrigen()));
			ope.setProducto(ProductoDAO.getInstance().productoToEntity(op.getProducto()));
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.save(ope);
			s.getTransaction().commit();
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void updateOP(OrdenPedido op) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.update(ordenPedidoToEntity(op));
			s.getTransaction().commit();
			s.flush();
			s.close();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private OrdenPedidoEntity ordenPedidoToEntity(OrdenPedido op) {
		OrdenPedidoEntity ope = new OrdenPedidoEntity();
		List<MovimientoReservaEntity> mres = new ArrayList<MovimientoReservaEntity>();
		ope.setCantidadPedida(op.getCantidadPedida());
		ope.setEstado(op.getEstado().toString());
		ope.setNro(op.getNro());
		ope.setPedidoOrigen(PedidoDAO.getInstance().pedidoToEntity(op.getPedidoOrigen()));
		ope.setProducto(ProductoDAO.getInstance().productoToEntity(op.getProducto()));
		for (MovimientoReserva mr : op.getMovReserva())
		{
			MovimientoReservaEntity mre = new MovimientoReservaEntity();
			java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
			mre.setCantidad(mr.getCantidad());
			mre.setCompleta(mr.isCompleta());
			mre.setFecha(sqlDate);
			mre.setPedido(PedidoDAO.getInstance().pedidoToEntity(mr.getPedido()));
			if (mr.getNro() != 0)
				mre.setNro(mr.getNro());
			mres.add(mre);
		}
		ope.setMovReserva(mres);
		return ope;
	}
	
	
	public OrdenPedido buscarOPConDisponibilidad(Producto p) {
		OrdenPedido op = new OrdenPedido();
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			OrdenPedidoEntity ope = (OrdenPedidoEntity) s.createQuery("from OrdenPedidoEntity ope where ope.producto.codBarras = '"+p.getCodBarras()+"' AND ope.estado = 'Pendiente'").uniqueResult();
			if (ope!=null)
			{
				op.setCantidadPedida(ope.getCantidadPedida());
				op.setEstado(EstadoOP.valueOf(ope.getEstado()));
				op.setNro(ope.getNro());
				op.setPedidoOrigen(PedidoDAO.getInstance().pedidoToNegocio(ope.getPedidoOrigen()));
				op.setProducto(ProductoDAO.getInstance().productoToNegocio(ope.getProducto()));
				if (!ope.getMovReserva().isEmpty())
				{
					for (MovimientoReservaEntity mre : ope.getMovReserva())
					{
						MovimientoReserva mr = new MovimientoReserva();
						java.util.Date utilDate = new java.util.Date(mre.getFecha().getTime());
						mr.setCantidad(mre.getCantidad());
						mr.setCompleta(mre.isCompleta());
						mr.setFecha(utilDate);
						mr.setNro(mre.getNro());
						mr.setPedido(PedidoDAO.getInstance().pedidoToNegocio(mre.getPedido()));
						op.getMovReserva().add(mr);
					}
				}
				return op;
			}
			else 
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return op;
		}
	}
	
	
	
}
