package dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.ItemRemito;
import business.MovimientoStock;
import business.Producto;
import business.Remito;
import business.Reserva;
import business.Ubicacion;
import entity.ItemRemitoEntity;
import entity.MovimientoStockEntity;
import entity.RemitoEntity;
import entity.ReservaEntity;
import entity.UbicacionEntity;
import entity.UbicacionId;
import enumeration.TipoMovimientoStock;
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
			@SuppressWarnings("unchecked")
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
			@SuppressWarnings("unchecked")
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
			UbicacionEntity ue= new UbicacionEntity(new UbicacionId(u.getCalle(), u.getBloque(), u.getEstanteria(), u.getEstante(), u.getPosicion()), u.getCantidadActual());
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.update(ue);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public List<Ubicacion> traerTodasLasUbicaciones() {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			List<UbicacionEntity> lista = (List<UbicacionEntity>) s.createQuery("from UbicacionEntity").list();
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
		Ubicacion u = new Ubicacion(ue.getIdUbicacion().getCalle(), ue.getIdUbicacion().getBloque(), ue.getIdUbicacion().getEstanteria(), ue.getIdUbicacion().getEstante(), ue.getIdUbicacion().getPosicion());
		u.setCantidadActual(ue.getCantidadActual());
		return u;
	}
	
	
	
	public Ubicacion traerPrimeraUbicacionVacia() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UbicacionEntity> lista = (List<UbicacionEntity>) s.createQuery("from UbicacionEntity ue where ue.cantidadActual=0").list();
		List<Ubicacion> listafinal = new ArrayList<Ubicacion>();			
		for (UbicacionEntity ue: lista)
		{ 
			Ubicacion u = this.UbicacionToNegocio(ue);
			listafinal.add(u);
			
		}
		return listafinal.get(0);
		
	}

	public void updateReserva(Reserva r) {
		// TODO Auto-generated method stub
		try
		{
			ReservaEntity re= this.reservaToEntity(r);
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.update(re);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void saveRemito(Remito r) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.save(remitoToEntity(r));
			s.beginTransaction().commit();
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private RemitoEntity remitoToEntity(Remito r) {
		RemitoEntity re = new RemitoEntity();
		List<ItemRemitoEntity> ires = new ArrayList<ItemRemitoEntity>();
		re.setCliente(ClienteDAO.getInstance().clienteToEntity(r.getCliente()));
		re.setDespachado(r.isDespachado());
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		re.setFecha(sqlDate);
		for (ItemRemito ir : r.getItems())
		{
			ItemRemitoEntity ire = new ItemRemitoEntity();
			ire.setCantidad(ir.getCantidad());
			ire.setProducto(ProductoDAO.getInstance().productoToEntity(ir.getProducto()));
			ires.add(ire);
		}
		re.setItems(ires);
		return re;
	}
	
	
	
}
