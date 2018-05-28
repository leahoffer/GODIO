package dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.MovimientoReserva;
import business.MovimientoStock;
import business.OrdenPedido;
import business.Producto;
import business.Reserva;
import business.Ubicacion;
import entity.MovimientoReservaEntity;
import entity.MovimientoStockEntity;
import entity.OrdenPedidoEntity;
import entity.ProductoEntity;
import entity.ReservaEntity;
import entity.UbicacionEntity;
import enumeration.EstadoOP;
import enumeration.TipoMovimientoStock;
import exception.ProductoException;
import hibernate.HibernateUtil;

public class AlmacenDAO {

	private static AlmacenDAO instance;
	
	public static AlmacenDAO getInstance() {
		if (instance == null)
			instance = new AlmacenDAO();
		return instance;
	}
	private AlmacenDAO() {}
	public void createReserva(Reserva reserva) {
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
	public ReservaEntity reservaToEntity(Reserva r) {
		ReservaEntity re = new ReservaEntity();
		re.setCantidad(r.getCantidad());
		re.setCompleta(r.isCompleta());
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		re.setFecha(sqlDate);
		re.setPedido(PedidoDAO.getInstance().pedidoToEntity(r.getPedido()));
		re.setProducto(ProductoDAO.getInstance().productoToEntity(r.getProducto()));
		return re;
	}
	
	public Reserva reservaToNegocio(ReservaEntity re){
		Reserva r = new Reserva();
		r.setCantidad(re.getCantidad());
		r.setCompleta(re.isCompleta());
		java.util.Date utilDate = new java.util.Date(re.getFecha().getTime());
		r.setFecha(utilDate);
		r.setNumero(re.getNumero());
		r.setPedido(PedidoDAO.getInstance().pedidoToNegocio(re.getPedido()));
		r.setProducto(ProductoDAO.getInstance().productoToNegocio(re.getProducto()));
		return r;
	}
	
	public List<MovimientoStock> movimientosStockProducto(Producto p) {
		List<MovimientoStock> mss = new ArrayList<MovimientoStock>();
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			List<MovimientoStockEntity> mses = (List<MovimientoStockEntity>)s.createQuery("from MovimientoStockEntity mse where mse.producto.codBarras = '" + p.getCodBarras()+"'").list();
			for (MovimientoStockEntity mse : mses)
			{
				MovimientoStock ms = new MovimientoStock();
				ms.setCantidad(mse.getCantidad());
				ms.setMotivo(mse.getMotivo());
				ms.setProducto(ProductoDAO.getInstance().productoToNegocio(mse.getProducto()));
				ms.setResponsable(mse.getResponsable());
				ms.setTipo(TipoMovimientoStock.valueOf(mse.getTipo()));
				mss.add(ms);
			}
			s.close();
			return mss;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return mss;
		}
	}
	public List<Reserva> reservasProducto(Producto p) {
		List<Reserva> rs = new ArrayList<Reserva>();
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			List <ReservaEntity> res = (List<ReservaEntity>) s.createQuery("from ReservaEntity re where producto.codBarras = '"+p.getCodBarras()+"'").list();
			for (ReservaEntity re : res)
			{
				Reserva r = reservaToNegocio(re);
				rs.add(r);
			}
			return rs;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return rs;
		}
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
	/*
	 * SOLO PARA INICIALIZAR. USAR UN TEST PARA CREAR SI NO LAS TENES EN LA BASE
	 * */
	public void crearUbicacion(UbicacionEntity ue) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.save(ue);
			s.getTransaction().commit();
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void saveMovimientoStock(MovimientoStock movimientoStock) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.save(movimientoStockToEntity(movimientoStock));
			s.getTransaction().commit();
			s.flush();
			s.close();
			//UbicacionId uid = s.get(UbicacionId.class, )
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private MovimientoStockEntity movimientoStockToEntity(MovimientoStock ms) {
		MovimientoStockEntity mse = new MovimientoStockEntity();
		mse.setCantidad(ms.getCantidad());
		mse.setMotivo(ms.getMotivo());
		mse.setProducto(ProductoDAO.getInstance().productoToEntity(ms.getProducto()));
		mse.setResponsable(ms.getResponsable());
		mse.setTipo(ms.getTipo().toString());
		return mse;
	}
	public Ubicacion traerUbicacion(Ubicacion u) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			UbicacionEntity ue = (UbicacionEntity) s.createQuery("from UbicacionEntity ue where ue.idUbicacion.calle = '"+u.getCalle()+"' AND ue.idUbicacion.bloque = "+Integer.toString(u.getBloque())+" AND ue.idUbicacion.estante = "+Integer.toString(u.getEstante())+" AND ue.idUbicacion.estanteria = "+Integer.toString(u.getEstanteria())+" AND ue.idUbicacion.posicion = "+Integer.toString(u.getPosicion())).uniqueResult();
			Ubicacion ub = this.UbicacionToNegocio(ue);
			return ub;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	public void updateUbicacion(Ubicacion u) {
		try
		{
			UbicacionEntity ue= new UbicacionEntity();
			ue.getIdUbicacion().setCalle(u.getCalle());
			ue.getIdUbicacion().setBloque(u.getBloque());
			ue.getIdUbicacion().setEstante(u.getEstante());
			ue.getIdUbicacion().setEstanteria(u.getEstanteria());
			ue.getIdUbicacion().setPosicion(u.getPosicion());
			ue.setCantidadActual(u.getCantidadActual());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.update(ue);
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public List<Ubicacion> traerTodasLasUbicaciones() {
		// TODO Auto-generated method stub
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			List<UbicacionEntity> lista = s.createQuery("from UbicacionEntity").list();
			List<Ubicacion> listafinal = new ArrayList<Ubicacion>();			
			for (UbicacionEntity ue: lista)
			{ 
				Ubicacion u = this.UbicacionToNegocio(ue);
				listafinal.add(u);
				
			}
			return listafinal;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	private Ubicacion UbicacionToNegocio(UbicacionEntity ue) {
		// TODO Auto-generated method stub
		Ubicacion u = new Ubicacion();
		u.setBloque(ue.getIdUbicacion().getBloque());
		u.setCalle(ue.getIdUbicacion().getCalle());
		u.setCantidadActual(ue.getCantidadActual());
		u.setEstante(ue.getIdUbicacion().getEstante());
		u.setEstanteria(ue.getIdUbicacion().getEstanteria());
		u.setPosicion(ue.getIdUbicacion().getPosicion());
		return u;
	}
	public Ubicacion traerPrimeraUbicacionVacia() {
		// TODO Auto-generated method stub
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		List<UbicacionEntity> lista = s.createQuery("from UbicacionEntity ue where ue.cantidadActual=0").list();
		List<Ubicacion> listafinal = new ArrayList<Ubicacion>();			
		for (UbicacionEntity ue: lista)
		{ 
			Ubicacion u = this.UbicacionToNegocio(ue);
			listafinal.add(u);
			
		}
		return listafinal.get(0);
		
	}
	public OrdenPedido findOPByNro(int nroOP) {
		try
		{
			OrdenPedidoEntity op;
			OrdenPedido resultado;
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			op = (OrdenPedidoEntity) s.get(OrdenPedidoEntity.class, nroOP);
			resultado = ordenPedidoToNegocio(op);
			s.close();
			return resultado;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	private OrdenPedido ordenPedidoToNegocio(OrdenPedidoEntity ope) {
		OrdenPedido op = new OrdenPedido();
		op.setCantidadPedida(ope.getCantidadPedida());
		op.setEstado(EstadoOP.valueOf(ope.getEstado()));
		op.setNro(ope.getNro());
		op.setPedidoOrigen(PedidoDAO.getInstance().pedidoToNegocio(ope.getPedidoOrigen()));
		op.setProducto(ProductoDAO.getInstance().productoToNegocio(ope.getProducto()));
		for (MovimientoReservaEntity mre : ope.getMovReserva())
		{
			MovimientoReserva mr = new MovimientoReserva();
			mr.setCantidad(mre.getCantidad());
			mr.setCompleta(mre.isCompleta());
			java.util.Date fecha = new java.util.Date(mre.getFecha().getTime());
			mr.setFecha(fecha);
			mr.setNro(mre.getNro());
			mr.setPedido(PedidoDAO.getInstance().pedidoToNegocio(mre.getPedido()));
			op.getMovReserva().add(mr);
		}
		return op;
	}
	public List<OrdenPedido> buscarOPSPendientesOReservadas() {
		try
		{
			List<OrdenPedido> resultado = new ArrayList<OrdenPedido>();
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			List<OrdenPedidoEntity> ordenes = (List<OrdenPedidoEntity>) s.createQuery("from OrdenPedidoEntity ope where ope.estado='Pendiente' or ope.estado='Reservada'").list();
			for (OrdenPedidoEntity ope : ordenes)
			{
				resultado.add(ordenPedidoToNegocio(ope));
			}
			return resultado;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	



	
	
	
}
