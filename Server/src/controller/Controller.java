package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import business.Bonificacion;
import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.DetallePedido;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
import business.Ubicacion;
import dao.AlmacenDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dto.ClienteDTO;
import dto.CuentaCorrienteDTO;
import dto.DetallePedidoDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import entity.ProductoEntity;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;
import enumeration.EstadoProducto;
import enumeration.Presentacion;
import exception.ClienteException;
import exception.ProductoException;

@SuppressWarnings("unused")
public class Controller {

	private static Controller instance;
	private List<Cliente> clientes;
	
	
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
		clientes = new ArrayList<Cliente>();
	}

	public void crearCliente(ClienteDTO c) throws ClienteException  {
		/**
		 * Creo un método para que me deje crear el RO y esas otras cosas
		 */
		
			if(ClienteDAO.getInstance().findByCuit(c.getCuit()) == null)
			{

				Cliente cliente=new Cliente();
				CuentaCorriente cc= new CuentaCorriente();
				Bonificacion boni  = new Bonificacion();
				Bonificacion boni2 = new Bonificacion();
				

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
			
				boni.setCondicion("Condicion 1");
				boni.setMonto(150);
				
				boni2.setCondicion("Condicion 2");
				boni2.setMonto(200);
				
				List<Condicion> condi = new ArrayList<Condicion>();
				condi.add(boni);
				condi.add(boni2);
				
				cliente.getCuentaCorriente().setCondiciones(condi);
				
				
				cliente.saveOrUpdate();
			}
			else
				throw new ClienteException("Cliente ya creado");
		
		
	}
	
	public void crearPedido (PedidoDTO p) throws ClienteException, ProductoException
	{
		//Calendar  hoy = Calendar.getInstance();
		
		Cliente cliente = buscarCliente(p.getCliente().getCuit());
		List<DetallePedido> detalles = new ArrayList<DetallePedido>();
		for (DetallePedidoDTO ddto : p.getDetalle())
		{ 
			DetallePedido pedc = new DetallePedido();
			Producto prod = this.buscarProducto(ddto.getProducto().getCodBarras());
			pedc.setCantidad(ddto.getCantidad());
			pedc.setProducto(prod);
			pedc.calcularSubTotal();
			detalles.add(pedc);
		}
		
		Pedido pedido=new Pedido();
		pedido.setDetalle(detalles);
		pedido.calcularTotal();
		pedido.setAclaracionEspecial(p.getAclaracionEspecial());
		pedido.setCliente(cliente);
		pedido.setCondicionesAplicadas(new ArrayList<Condicion>());
		pedido.setDespachable(p.isDespachable());
		pedido.setDir_entrega(p.getDir_entrega());
		pedido.setEstado(EstadoPedido.valueOf("PendienteAutorizacion"));
		pedido.setFecha(p.getFecha());
		pedido.setMotivoEstado(p.getMotivoEstado());
		
		pedido.saveOrUpdate();
		
	}
	
	private Producto buscarProducto(String codBarras) {
		// TODO Auto-generated method stub
		Producto p = ProductoDAO.getInstance().findById(codBarras);
		return p;
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
	
	private Cliente buscarCliente (String cuit) throws ClienteException
	{
		for (Cliente clie : clientes)
		{
			if (clie.getCuit().equals(cuit));
				return clie; //Busqueda en memoria.
		}
		
		//Los DAO reciben y devuelven objetos de negocio
		Cliente cliente = ClienteDAO.getInstance().findByCuit(cuit);
		
	/*	ClienteDTO cli = new ClienteDTO();
		CuentaCorrienteDTO cta = new CuentaCorrienteDTO();
		List<CondicionDTO> conds = new ArrayList<CondicionDTO>(); 
		*//** Comento esto para que no putee por ahora, porque solo falta ver la herencia. Después se puede descomentar**//*
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
	*/	
		
		clientes.add(cliente);
		return cliente;
	}
	
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
	private boolean validarCompletarPedido(Pedido p) 
	{
		boolean resultado = true;
		for (DetallePedido dp : p.getDetalle())
		{
			int sd = Almacen.getInstance().devolverStockProducto(dp.getProducto());
			//Si tengo Stock disponible, reservo y listo. Almacén se encarga de updatear el stock y eso.
			if (sd>dp.getCantidad())
			{
				Almacen.getInstance().createReserva(p, dp, dp.getCantidad());
			}
			//Sino...
			else
			{
				//Si no puedo completar, pero el stock disponible es mayor a 0, primero reservo lo que queda y después creo OP nueva
				if (sd > 0)
				{
					Almacen.getInstance().createReserva(p, dp, sd);
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
						//Creo que esto está bien... que el crearOrdenPedido no genere el movimientoReserva.
						Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-sd);
						op = Almacen.getInstance().buscarOPConDisponibilidad(dp.getProducto());
						op.agregarMovimientoReserva(dp.getCantidad()-sd, p);
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
						Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-sd);
						op = Almacen.getInstance().buscarOPConDisponibilidad(dp.getProducto());
						op.agregarMovimientoReserva(dp.getCantidad(), p);
					}
				}
				resultado = false;
			}
		}
		return resultado;
	}

	public ClienteDTO mostrarCliente(String cuit) throws ClienteException {
		// TODO Auto-generated method stub
		
			Cliente c= this.buscarCliente(cuit);
			ClienteDTO cdto= new ClienteDTO();
			cdto.setCuit(c.getCuit());
			cdto.setDireccion(c.getDireccion());
			cdto.setR_inscripto(c.isR_inscripto());
			cdto.setRazon_social(c.getRazon_social());
			cdto.setTelefono(c.getTelefono());
			cdto.setCondicionEsp(c.getCondicionEsp());
		return cdto;
	}

	public void modificarCliente(ClienteDTO cdto) throws ClienteException {
		// TODO Auto-generated method stub
		Cliente c= this.buscarCliente(cdto.getCuit());
		c.setCondicionEsp(cdto.getCondicionEsp());
		c.setDireccion(cdto.getDireccion());
		c.setR_inscripto(cdto.isR_inscripto());
		c.setRazon_social(cdto.getRazon_social());
		c.setTelefono(cdto.getTelefono());
		//No tiene en cuenta ni cuenta corriente ni Movimientos. Es solo para updatear datos personales//	
		ClienteDAO.getInstance().update(c);
	}


	/*public void agregarMovimientoStock(String codBarra, UbicacionDTO udto, String responsable, int cantidad)
	{
		Ubicacion u = Almacen.getInstance().traerUbicacion(udto.getCalle(), udto.getBloque(), udto.getEstanteria(), udto.getEstante(), udto.getPosicion());
		
	}*/

	public ProductoDTO mostrarProducto(String codbarras) {
		// TODO Auto-generated method stub
		Producto p = this.buscarProducto(codbarras);
		ProductoDTO pdto = new ProductoDTO();
		pdto.setCodBarras(p.getCodBarras());
		pdto.setDescripcion(p.getDescripcion());
		return pdto;
	}
	
	public List<UbicacionDTO> despacharPedido (PedidoDTO pdto)
	{
		List<UbicacionDTO> udtos = new ArrayList<UbicacionDTO>();
		List<Ubicacion> us;
		Pedido p = PedidoDAO.getInstance().findByNro(pdto.getNroPedido());
		//buscarUbicacionesParaDespachar se va a encargar de crear los movimientos y de actualizar las ubicaciones que encuentre y devuelva
		us = Almacen.getInstance().buscarUbicacionesParaDespachar(p);
		for (Ubicacion u : us)
		{
			UbicacionDTO udto = new UbicacionDTO();
			udto.setBloque(u.getBloque());
			udto.setCalle(u.getCalle());
			udto.setCantidadActual(u.getCantidadActual());
			udto.setEstante(u.getEstante());
			udto.setEstanteria(u.getEstanteria());
			udto.setPosicion(u.getPosicion());
			udtos.add(udto);
		}
		p.setEstado(EstadoPedido.Despachado);
		facturarPedido(p);
		p.update();
		return udtos;
	}

	private void facturarPedido(Pedido p) {
		// TODO Auto-generated method stub
		
	}
	
	public List<PedidoDTO> listarPedidosPendientesDespacho(){
		List<PedidoDTO> pdtos = new ArrayList<PedidoDTO>();
		List<Pedido> pps = PedidoDAO.getInstance().traerPedidosPendientesDespacho();
		for (Pedido p : pps)
		{
			PedidoDTO pdto = p.toDTO();
			pdtos.add(pdto);
		}
		return pdtos;
	}

	private List<Pedido> traerPedidosPendientesDespacho() {
		return PedidoDAO.getInstance().traerPedidosPendientesDespacho();

	}
}

	