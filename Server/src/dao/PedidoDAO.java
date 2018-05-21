package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import business.Descuento;
import business.Bonificacion;
import business.Condicion;
import business.DetallePedido;
import business.Factura;
import business.ItemFactura;
import business.Pedido;
import entity.BonificacionEntity;
import entity.ClienteEntity;
import entity.CondicionEntity;
import entity.DescuentoEntity;
import entity.DetallePedidoEntity;
import entity.FacturaEntity;
import entity.ItemFacturaEntity;
import entity.PedidoEntity;
import enumeration.EstadoPedido;
import enumeration.TipoFactura;
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
	
	

	public PedidoEntity pedidoToEntity(Pedido ped) {
		ClienteEntity ce = ClienteDAO.getInstance().clienteToEntity(ped.getCliente());
		PedidoEntity pe = new PedidoEntity();
		pe.setAclaracionEspecial(ped.getAclaracionEspecial());
		pe.setCliente(ce);
		pe.setDespachable(ped.isDespachable());
		pe.setDir_entrega(ped.getDir_entrega());
		pe.setEstado(ped.getEstado().toString());
		pe.setFactura(facturaToEntity(ped.getFactura()));
		pe.setFecha(ped.getFecha());
		pe.setFecha_despacho(ped.getFecha_despacho());
		pe.setMotivoEstado(ped.getMotivoEstado());
		pe.setNroPedido(ped.getNroPedido());
		pe.setTotal_bruto(ped.getTotal_bruto());
		List<CondicionEntity> caes = new ArrayList<CondicionEntity>();
		List<DetallePedidoEntity> dpes = new ArrayList<DetallePedidoEntity>();
		for (Condicion c : ped.getCondicionesAplicadas())
		{
			if (c instanceof Bonificacion)
			{
				BonificacionEntity be = new BonificacionEntity();
				be.setDescripcion(c.getCondicion());
				be.setMonto(((Bonificacion) c).getMonto());
				caes.add(be);
			}
			else if (c instanceof Descuento)
			{
				DescuentoEntity de = new DescuentoEntity();
				de.setDescripcion(c.getCondicion());
				de.setPorcentaje(((Descuento) c).getPorcentaje());
				caes.add(de);
			}
		}
		for (DetallePedido dp : ped.getDetalle())
		{
			DetallePedidoEntity dpe = new DetallePedidoEntity();
			dpe.setCantidad(dp.getCantidad());
			dpe.setProducto(ProductoDAO.getInstance().productoToEntity(dp.getProducto()));
			dpe.setSubtotal(dp.getSubtotal());
			dpes.add(dpe);
		}
		pe.setCondicionesAplicadas(caes);
		pe.setDetalle(dpes);
	return pe;
	}

	private FacturaEntity facturaToEntity(Factura factura) {
		FacturaEntity fe = new FacturaEntity();
		List<ItemFacturaEntity> ifes = new ArrayList<ItemFacturaEntity>(); 
		fe.setCancelado(factura.getCancelado());
		fe.setCliente(ClienteDAO.getInstance().clienteToEntity(factura.getCliente()));
		fe.setNro(factura.getNro());
		fe.setTipo(factura.getTipo().toString());
		fe.setTotal(factura.getTotal());
		for (ItemFactura ifa : factura.getItems())
		{
			ItemFacturaEntity ife = new ItemFacturaEntity();
			ife.setCantidad(ifa.getCantidad());
			ife.setProducto(ProductoDAO.getInstance().productoToEntity(ifa.getProducto()));
			ife.setSubtotal(ifa.getSubtotal());
			ifes.add(ife);
		}
		fe.setItems(ifes);
		return fe;
	}

	public List<Pedido> traerPedidosPendientes() {
		try
		{
			List<Pedido> ps = new ArrayList<Pedido>();
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			List <PedidoEntity> pes = (List<PedidoEntity>) s.createQuery("from PedidoEntity pe where pe.estado = 'PendienteAutorizacion'").list();
			for (PedidoEntity pe : pes)
			{
				Pedido p = pedidoToNegocio(pe);
				ps.add(p);
			}
			return ps;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private Pedido pedidoToNegocio(PedidoEntity pe) {
		Pedido p = new Pedido();
		List<Condicion> conds = new ArrayList<Condicion>();
		List<DetallePedido> ds = new ArrayList<DetallePedido>();
		p.setAclaracionEspecial(pe.getAclaracionEspecial());
		p.setCliente(ClienteDAO.getInstance().clienteToNegocio(pe.getCliente()));
		p.setDespachable(pe.isDespachable());
		p.setDir_entrega(pe.getDir_entrega());
		p.setEstado(EstadoPedido.valueOf(pe.getEstado()));
		p.setFecha(pe.getFecha());
		p.setFecha_despacho(pe.getFecha_despacho());
		p.setMotivoEstado(pe.getMotivoEstado());
		p.setNroPedido(pe.getNroPedido());
		p.setTotal_bruto(pe.getTotal_bruto());
		for (CondicionEntity ce : pe.getCondicionesAplicadas())
		{
			if (ce instanceof BonificacionEntity)
			{
				Bonificacion b = new Bonificacion();
				b.setCondicion(ce.getDescripcion());
				b.setMonto(((BonificacionEntity) ce).getMonto());
				conds.add(b);
			}
			else if (ce instanceof DescuentoEntity)
			{
				Descuento d = new Descuento();
				d.setCondicion(ce.getDescripcion());
				d.setPorcentaje(((DescuentoEntity) ce).getPorcentaje());
				conds.add(d);
			}
		}
		for (DetallePedidoEntity dpe : pe.getDetalle())
		{
			DetallePedido dp = new DetallePedido();
			dp.setCantidad(dpe.getCantidad());
			dp.setSubtotal(dp.getSubtotal());
			dp.setProducto(ProductoDAO.getInstance().productoToNegocio(dpe.getProducto()));
			ds.add(dp);
		}
		p.setCondicionesAplicadas(conds);
		p.setDetalle(ds);
		if (pe.getFactura() != null)
		{
			List<ItemFactura> ifs = new ArrayList<ItemFactura>();
			Factura f = new Factura();
			f.setCancelado(pe.getFactura().getCancelado());
			f.setCliente(p.getCliente());
			f.setNro(pe.getFactura().getNro());
			f.setTipo(TipoFactura.valueOf(pe.getFactura().getTipo()));
			f.setTotal(pe.getFactura().getTotal());
			for (ItemFacturaEntity ife : pe.getFactura().getItems())
			{
				ItemFactura ifa = new ItemFactura();
				ifa.setProducto(ProductoDAO.getInstance().productoToNegocio(ife.getProducto()));
				ifa.setCantidad(ife.getCantidad());
				ifa.setSubtotal(ife.getSubtotal());
				ifs.add(ifa);
			}
			f.setItems(ifs);
			p.setFactura(f);
		}
		return p;
	}

	public void update(Pedido pedido) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.update(pedidoToEntity(pedido));
			s.getTransaction().commit();
			s.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Pedido findByNro(int nro) {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			PedidoEntity pe = (PedidoEntity) s.get(PedidoEntity.class, nro);
			s.getTransaction().commit();
			s.close();
			return pedidoToNegocio(pe);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	

}
