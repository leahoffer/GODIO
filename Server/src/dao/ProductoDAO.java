package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.Lote;
import business.Producto;
import business.Proveedor;
import business.Ubicacion;
import dto.ProductoDTO;
import entity.LoteEntity;
import entity.ProductoEntity;
import entity.UbicacionEntity;
import entity.UbicacionId;
import enumeration.EstadoProducto;
import enumeration.Presentacion;
import exception.ProductoException;
import hibernate.HibernateUtil;

public class ProductoDAO {

	private static ProductoDAO instance;
	
	public  static ProductoDAO getInstance() {
		if (instance == null)
			instance = new ProductoDAO();
		return instance;
	}
	
	private ProductoDAO() {
	}
	
	
	public void createOrUpdate(Producto p)
	{
		ProductoEntity pe = productoToEntity(p);
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			System.out.println("Guardando Producto...");
			s.saveOrUpdate(pe);
			s.getTransaction().commit();
			System.out.println("Producto guardado!");
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			System.out.println("Error al crear producto"); //CREAR EXCEPTIONS 
			e.printStackTrace();
		}
	}
	
	public ProductoEntity productoToEntity(Producto p)
	{
		List<UbicacionEntity> ues = new ArrayList<UbicacionEntity>();
		ProductoEntity pe = new ProductoEntity();
		LoteEntity le = loteToEntity(p.getLote());
		pe.setCantAComprar(p.getCantAComprar());
		pe.setCantPosicion(p.getCantPosicion());
		pe.setCodBarras(p.getCodBarras());
		pe.setDescripcion(p.getDescripcion());
		pe.setEstado(p.getEstado().toString());
		pe.setLote(loteToEntity(p.getLote()));
		pe.setMarca(p.getMarca());
		pe.setPrecio(p.getPrecio());
		pe.setPresentacion(p.getPresentacion().toString());
		pe.setTamaño(p.getTamaño());
		pe.setUnidad(p.getUnidad());
		
		for (Ubicacion u : p.getUbicaciones())
		{
			UbicacionEntity ue = new UbicacionEntity();
			UbicacionId uid = new UbicacionId();
			uid.setBloque(u.getBloque());
			uid.setCalle(u.getCalle());
			uid.setCantidadActual(u.getCantidadActual());
			uid.setEstante(u.getEstante());
			uid.setEstanteria(u.getEstanteria());
			uid.setPosicion(u.getPosicion());
			ue.setIdUbicacion(uid);
			ues.add(ue);
		}
		pe.setUbicaciones(ues);
		pe.setLote(le);
		return pe;
	}

	/**
	 * IMPORTANTE:
	 *		PUSE QUE GUARDE INFO DE PROVEEDOR COMO ATRIBUTOS STRING Y FLOAT. No tiene sentido persistir una clase Proveedor va a ser un bajón.
	 *		Cuando haya que traer de la base, con los datos de Proveedor y Precio se crea instancia de Proveedor(nombre, precio) y fue. :D
	 */
	public LoteEntity loteToEntity(Lote lote) {
		LoteEntity le = new LoteEntity();
		le.setNumero(lote.getNumero());
		le.setPrecioventa(lote.getPrecioventa());
		le.setProveedor(lote.getProveedor().getNombreProv());
		le.setPrecioventa(lote.getProveedor().getPrecio());
		return le;
	}

	public List<ProductoEntity> findAll() throws ProductoException {
		// TODO Auto-generated method stub
		
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			List<ProductoEntity> lista = (List<ProductoEntity>)s.createQuery("from ProductoEntity").list();
			s.close();
			return lista;
		}
		catch (Exception e)
		{
			throw new ProductoException("Error al traer los Productos");
			
		}
	
	}

	public Producto productoToNegocio(ProductoEntity pe) {
		Producto p = new Producto();
		Lote l = new Lote();
		List<Ubicacion> us = new ArrayList<Ubicacion>();
		p.setCantAComprar(pe.getCantAComprar());
		p.setCantPosicion(pe.getCantPosicion());
		p.setCodBarras(pe.getCodBarras());
		p.setDescripcion(pe.getDescripcion());
		p.setEstado(EstadoProducto.valueOf(pe.getEstado()));
		p.setMarca(pe.getMarca());
		p.setPrecio(pe.getPrecio());
		p.setPresentacion(Presentacion.valueOf(pe.getPresentacion()));
		p.setTamaño(pe.getTamaño());
		p.setUnidad(pe.getUnidad());
		l.setNumero(pe.getLote().getNumero());
		l.setPrecioventa(pe.getLote().getPrecioventa());
		l.setProveedor(new Proveedor(pe.getLote().getProveedor(), pe.getLote().getPrecioventa()));
		l.setVencimiento(pe.getLote().getVencimiento());
		for(UbicacionEntity ue : pe.getUbicaciones())
		{
			Ubicacion u = new Ubicacion();
			u.setBloque(ue.getIdUbicacion().getBloque());
			u.setCalle(ue.getIdUbicacion().getCalle());
			u.setCantidadActual(ue.getIdUbicacion().getCantidadActual());
			u.setEstante(ue.getIdUbicacion().getEstante());
			u.setEstanteria(ue.getIdUbicacion().getEstanteria());
			u.setPosicion(ue.getIdUbicacion().getPosicion());
			us.add(u);
		}
		p.setLote(l);
		p.setUbicaciones(us);
		return p;
	}
	
}
