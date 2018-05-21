package controller;

import java.util.ArrayList;
import java.util.List;

import business.Bonificacion;
import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.Descuento;
import business.DetallePedido;
import business.Pedido;
import business.Producto;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dto.BonificacionDTO;
import dto.ClienteDTO;
import dto.CondicionDTO;
import dto.CuentaCorrienteDTO;
import dto.DescuentoDTO;
import dto.ProductoDTO;
import entity.ClienteEntity;
import entity.CondicionEntity;
import entity.ProductoEntity;
import exception.ClienteException;
import exception.ProductoException;

public class Controller {

	private static Controller instance;
	private List<ClienteDTO> clientes;
	
	
	public static Controller getInstance() {
		if (instance==null)
			instance = new Controller();
		return instance;
	}
	
	private Controller() {
		/**
		 * Ver si no tenemos ganas de crearlo con algo particular, o lo creamos así vacío sin nada.
		 * Pero es Singleton así que sale constructor privado
		 */
	}

	public void crearCliente(ClienteDTO c) {
		/**
		 * Creo un método para que me deje crear el RO y esas otras cosas
		 */
		try {
			if(ClienteDAO.getInstance().findByCuit(c.getCuit()) == null)
			{
				Cliente cliente=new Cliente();
				CuentaCorriente cc= new CuentaCorriente();
				cc.setId(c.getCuentaCorriente().getId());
				cc.setLimite(c.getCuentaCorriente().getLimite());
				cc.setSaldo(c.getCuentaCorriente().getSaldo());
				
				cliente.setCondicionEsp(c.getCondicionEsp());
				cliente.setCuentaCorriente(cc);
				cliente.setCuit(c.getCuit());
				cliente.setDireccion(c.getDireccion());
				cliente.setR_inscripto(c.isR_inscripto());
				cliente.setRazon_social(c.getRazon_social());
				cliente.setTelefono(c.getTelefono());
				
				cliente.saveOrUpdate();
			}
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void crearPedido (String cuit , ArrayList<DetallePedido> ped) throws ClienteException, ProductoException
	{
		
		buscarCliente(cuit);
		listarProductos(); // ver como pasar los productos para que los pueda elegir.
		
		float subtotal = 0;
		float totalbruto = 0;
		
		for (DetallePedido pedido: ped)
		{
	     
	     subtotal = pedido.getCantidad() *  pedido.getProducto().getPrecio();;
	     pedido.setSubtotal(subtotal);
	     totalbruto = totalbruto + subtotal;
		}
	
		
	}
	
	public List<ProductoDTO> listarProductos() throws ProductoException
	{
		List<ProductoEntity> prods = new ArrayList<ProductoEntity>();
		prods=ProductoDAO.getInstance().findAll();
		
		List <ProductoDTO> prodsvo = new ArrayList<ProductoDTO>();
		
		for (ProductoEntity pe : prods)
		{ 
			ProductoDTO p= new ProductoDTO();
			p.setCodBarras(pe.getCodBarras());
			p.setMarca(pe.getMarca());
			p.setDescripcion(pe.getDescripcion());
			p.setEstado(pe.getEstado());
			prodsvo.add(p);
		}
		
		return prodsvo;
	
	}
	
	public ClienteDTO buscarCliente (String cuit) throws ClienteException
	{
		for (ClienteDTO clie : clientes)
		{
			if (clie.getCuit().equals(cuit));
				return clie; //Busqueda en memoria.
		}
		
		//Los DAO reciben y devuelven objetos de negocio
		Cliente cliente = ClienteDAO.getInstance().findByCuit(cuit);
		
		ClienteDTO cli = new ClienteDTO();
		CuentaCorrienteDTO cta = new CuentaCorrienteDTO();
		List<CondicionDTO> conds = new ArrayList<CondicionDTO>(); 
		/** Comento esto para que no putee por ahora, porque solo falta ver la herencia. Después se puede descomentar**/
		for (Condicion c :cliente.getCuentaCorriente().getCondiciones())
		{
			if (c instanceof Bonificacion)
			{
				BonificacionDTO b = new BonificacionDTO();
				b.setCondicion(c.getCondicion());
				b.setMonto(((Bonificacion) c).getMonto());
				conds.add(b);
			}
			else if (c instanceof Descuento)
			{
				DescuentoDTO d = new DescuentoDTO();
				d.setCondicion(c.getCondicion());
				d.setPorcentaje(((Descuento) c).getPorcentaje());
				conds.add(d);
			}
		}
		
		cta.setCondiciones(conds);
		cli.setCondicionEsp(cliente.getCondicionEsp());
		cli.setCuentaCorriente(cta);
		cli.setCuit(cliente.getCuit());
		cli.setDireccion(cliente.getDireccion());
		cli.setR_inscripto(cli.isR_inscripto()); 
		cli.setRazon_social(cli.getRazon_social());
		cli.setTelefono(cli.getTelefono());
		clientes.add(cli);
		return cli;
		
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	
	
}
