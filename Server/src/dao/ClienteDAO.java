package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.Bonificacion;
import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.Descuento;
import business.Factura;
import business.MovimientoCC;
import entity.BonificacionEntity;
import entity.ClienteEntity;
import entity.CondicionEntity;
import entity.CuentaCorrienteEntity;
import entity.DescuentoEntity;
import entity.MovimientoCCEntity;
import exception.ClienteException;
import hibernate.HibernateUtil;

public class ClienteDAO {

	private static ClienteDAO instance;
	
	public static ClienteDAO getInstance() {
		if(instance == null)
			instance = new ClienteDAO();
		return instance;
	}
	
	private ClienteDAO(){}
	
	
	public void createOrUpdate (Cliente c)
	{
		ClienteEntity ce = clienteToEntity(c);
		try 
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			System.out.println("Guardando Cliente...");
			s.saveOrUpdate(ce);
			s.getTransaction().commit();
			System.out.println("Cliente guardado!");
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			System.out.println("Error al guardar el Cliente");
			e.printStackTrace();
		}
	}
	
	public void update (Cliente c)
	{
		ClienteEntity ce = clienteToEntity(c);
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			s.update(ce);
			s.getTransaction().commit();
			s.flush();
			s.close();
		}
		catch (Exception e)
		{
			System.out.println("Error al actualziar Cliente");
			e.printStackTrace();
		}
	}
	
	public ClienteEntity clienteToEntity(Cliente c) {
		ClienteEntity ce = new ClienteEntity();
		ce.setCondicionEsp(c.getCondicionEsp());
		ce.setCuentaCorriente(cuentaCorrienteToEntity(c.getCuentaCorriente()));
		ce.setCuit(c.getCuit());
		ce.setDireccion(c.getDireccion());
		ce.setR_inscripto(c.isR_inscripto());
		ce.setRazon_social(c.getRazon_social());
		ce.setTelefono(c.getTelefono());
		return ce;
	}
	
	public CuentaCorrienteEntity cuentaCorrienteToEntity(CuentaCorriente cc)
	{
		List<MovimientoCCEntity> mcces = new ArrayList<MovimientoCCEntity>();
		List<CondicionEntity> ces = new ArrayList<CondicionEntity>();
		CuentaCorrienteEntity cce = new CuentaCorrienteEntity();
		cce.setId(cc.getId());
		cce.setLimite(cc.getLimite());
		cce.setSaldo(cc.getSaldo());
		for (MovimientoCC mcc : cc.getMovimientos())
		{
			MovimientoCCEntity mcce = new MovimientoCCEntity();
			mcce.setMonto(mcc.getMonto());
			mcce.setSigno(mcc.isSigno());
			//Por ahora...
			mcce.setFacturaAplicada(null);
			mcces.add(mcce);
		}
		for (Condicion c : cc.getCondiciones())
		{
			if (c instanceof Bonificacion)
			{
				BonificacionEntity be = new BonificacionEntity();
				be.setDescripcion(c.getCondicion());
				be.setMonto(((Bonificacion) c).getMonto());
				ces.add(be);
			}
			else if (c instanceof Descuento)
			{
				DescuentoEntity de = new DescuentoEntity();
				de.setDescripcion(c.getCondicion());
				de.setPorcentaje(((Descuento) c).getPorcentaje());
				ces.add(de);
			}
		}
		cce.setCondiciones(ces);
		cce.setMovimientos(mcces);
		return cce;	
	}

	public Cliente findByCuit(String cuit) throws ClienteException {
		try
		{
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			s.beginTransaction();
			ClienteEntity cliente = (ClienteEntity)s.createQuery("from ClienteEntity where cuit = ?").setString(0, cuit).uniqueResult();
			s.close();
			return clienteToNegocio(cliente);
		}
		catch (Exception e)
		{
			throw new ClienteException("Error al traer los Clientes");
		
		}
	}
	
	public Cliente clienteToNegocio(ClienteEntity ce)
	{
		Cliente c = new Cliente();
		CuentaCorriente cc = new CuentaCorriente();
		List<MovimientoCC> mccs = new ArrayList<MovimientoCC>();
		List<Condicion> cs = new ArrayList<Condicion>();
		c.setCondicionEsp(ce.getCondicionEsp());
		c.setCuit(ce.getCuit());
		c.setDireccion(ce.getDireccion());
		c.setR_inscripto(ce.isR_inscripto());
		c.setRazon_social(ce.getRazon_social());
		c.setTelefono(ce.getTelefono());
		cc.setLimite(ce.getCuentaCorriente().getLimite());
		cc.setSaldo(ce.getCuentaCorriente().getSaldo());
		for (CondicionEntity conde : ce.getCuentaCorriente().getCondiciones())
		{
			if (conde instanceof BonificacionEntity)
			{
				Bonificacion b = new Bonificacion();
				b.setCondicion(conde.getDescripcion());
				b.setMonto(((BonificacionEntity) conde).getMonto());
				cs.add(b);
			}
			else if (conde instanceof DescuentoEntity)
			{
				Descuento d = new Descuento();
				d.setCondicion(conde.getDescripcion());
				d.setPorcentaje(((DescuentoEntity) conde).getPorcentaje());
				cs.add(d);
			}
		}
		for(MovimientoCCEntity mcce : ce.getCuentaCorriente().getMovimientos())
		{
			//Esto en realidad es horrible, pero en el peor de los casos, del cliente en la factura no nos va a importar los movimientos de la CC.. o si?
			MovimientoCC mcc = new MovimientoCC(mcce.getMonto(), mcce.isSigno());
			if (mcce.getFacturaAplicada() != null)
			{
				Factura f = new Factura();
				f.setCancelado(mcce.getFacturaAplicada().getCancelado());
				f.setCliente(c);
			}
			mccs.add(mcc);
		}
		cc.setCondiciones(cs);
		cc.setMovimientos(mccs);
		c.setCuentaCorriente(cc);
		return c;
	}

}
