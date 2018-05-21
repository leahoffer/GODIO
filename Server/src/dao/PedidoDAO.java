package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
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
	

}
