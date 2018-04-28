package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.MovimientoCC;
import entity.ClienteEntity;
import entity.CondicionEntity;
import entity.CuentaCorrienteEntity;
import entity.MovimientoCCEntity;
import hibernate.HibernateUtil;

public class ClienteDAO {

	private static ClienteDAO instance;
	
	public ClienteDAO getInstance() {
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
			CondicionEntity ce = new CondicionEntity();
			//Crear bien la condicionEntity. No me acuerdo herencias :(
			ces.add(ce);
		}
		cce.setCondiciones(ces);
		cce.setMovimientos(mcces);
		return cce;	
	}
}
