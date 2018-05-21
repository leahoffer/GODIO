package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.Bonificacion;
import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.Descuento;
import business.DetallePedido;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
import business.Reserva;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dto.BonificacionDTO;
import dto.ClienteDTO;
import dto.CondicionDTO;
import dto.CuentaCorrienteDTO;
import dto.DescuentoDTO;
import dto.DetallePedidoDTO;
import dto.LoteDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import entity.ClienteEntity;
import entity.CondicionEntity;
import entity.ProductoEntity;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;
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
	//Qué es esto? Jajaja
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public List<PedidoDTO> listarPedidosPendientes ()
	{
		List<PedidoDTO> pdtos = new ArrayList<PedidoDTO>();
		List<Pedido> ps = PedidoDAO.getInstance().traerPedidosPendientes();
		for (Pedido p : ps)
		{
			PedidoDTO pdto = new PedidoDTO();
			List<DetallePedidoDTO> dpdtos = new ArrayList<DetallePedidoDTO>();
			pdto.setAclaracionEspecial(p.getAclaracionEspecial());
			pdto.setDespachable(p.isDespachable());
			pdto.setDir_entrega(p.getDir_entrega());
			pdto.setEstado(p.getEstado().toString());
			pdto.setFecha(p.getFecha());
			pdto.setFecha_despacho(p.getFecha_despacho());
			pdto.setMotivoEstado(p.getMotivoEstado());
			pdto.setNroPedido(p.getNroPedido());
			pdto.setTotal_bruto(p.getTotal_bruto());
			for (DetallePedido dp : p.getDetalle())
			{
				DetallePedidoDTO dpdto = new DetallePedidoDTO();
				ProductoDTO prdto = new ProductoDTO();
				dpdto.setCantidad(dp.getCantidad());
				dpdto.setSubtotal(dp.getSubtotal());
				prdto.setCantAComprar(dp.getProducto().getCantAComprar());
				prdto.setCantPosicion(dp.getProducto().getCantPosicion());
				prdto.setCodBarras(dp.getProducto().getCodBarras());
				prdto.setDescripcion(dp.getProducto().getDescripcion());
				prdto.setEstado(dp.getProducto().getEstado().toString());
				prdto.setPresentacion(dp.getProducto().getPresentacion().toString());
				dpdto.setProducto(prdto);
				dpdtos.add(dpdto);
			}
			pdto.setDetalle(dpdtos);
			pdtos.add(pdto);
		}
		return pdtos;
	}
	
	public boolean validarCreditoCliente(ClienteDTO cdto, PedidoDTO pdto) throws ClienteException
	{
		Cliente c = ClienteDAO.getInstance().findByCuit(cdto.getCuit());
		if (c.excedeLimite(pdto.getTotal_bruto()))
			return false;
		return true;
	}
	
	public void autorizarPedido (int nro)
	{
		Pedido p = PedidoDAO.getInstance().findByNro(nro);
		if (validarCompletarPedido(p))
		{
			p.setEstado(EstadoPedido.PendienteDespacho);
			p.update();
		}
		else
		{
			p.setEstado(EstadoPedido.PendienteOrdenCompra);
			p.update();
		}
	}

	//FALTA COMPLETAAAAARRRRRR
	private boolean validarCompletarPedido(Pedido p) {
		boolean resultado = true;
		for (DetallePedido dp : p.getDetalle())
		{
			int sd = calcularStockDisponible(dp.getProducto());
			//Si tengo Stock disponible, reservo y listo
			if (sd>dp.getCantidad())
			{
				Almacen.getInstance().crearReserva(p, dp, dp.getCantidad());
			}
			//Sino...
			else
			{
				//Si no puedo completar, pero el stock disponible es mayor a 0, primero reservo lo que queda y después creo OP nueva
				if (sd > 0)
				{
					Almacen.getInstance().crearReserva(p, dp, sd);
					OrdenPedido op = Almacen.getInstance().buscarOPConDisponibilidad(dp.getProducto());
					//Todo esto mientras haya una OP con disponibilidad. Sino voy a tener que hacer una nueva de 0 y reservarle el 100%
					if (op != null)
					{
						//Si tiene disponible el total de lo que falta, que lo reserve de ahí.
						if (op.calcularDisponible(dp.getCantidad()-sd))
						{
							op.agregarMovimientoReserva(dp.getCantidad()-sd, p);
						}
						//Sino, reservo de esa OP lo que le quede y le digo al Almacén que cree una nueva por el restante
						else
						{
							int reservadoOP = op.disponible();
							op.agregarMovimientoReserva(reservadoOP, p);
							op.setEstado(EstadoOP.Reservada);
							Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-sd-reservadoOP);
						}
					}
					//Tengo stock, aunque no suficiente, pero no tenog OPs con disponibilidad para reservar
					else 
					{
						//VOLVER A VER. Creo que el CrearOrdenPedido debería crearla e instantaneamente crearle un movimientoreserva. VER VER VER
						Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-sd);
					}
				}
				//Si no puedo completar, y aparte no hay NADA de stock, voy directamente a ver si tengo para reservarle a una OP
				else if (sd == 0)
				{
					OrdenPedido op = Almacen.getInstance().buscarOPConDisponibilidad(dp.getProducto());
					//Si hay una OP con disponibilidad...
					if (op != null)
					{
						//Si la OP tiene disponible para reservarle el total, de lujo
						if (op.calcularDisponible(dp.getCantidad()))
						{
							op.agregarMovimientoReserva(dp.getCantidad(), p);
						}
						//Sino, le reservo lo que le quede y aparte creo una nueva
						else
						{
							int reservadoOP = op.disponible();
							op.agregarMovimientoReserva(reservadoOP, p);
							op.setEstado(EstadoOP.Reservada);
							Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-reservadoOP);
						}
					}
					//No solo no tengo nada de stock sino que no tengo OP con disponibilidad. Caso más horrible.
					else
					{
						
					}
				}
				resultado = false;
			}
		return resultado;
		}
	}



	

	private int calcularStockDisponible(Producto producto) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
