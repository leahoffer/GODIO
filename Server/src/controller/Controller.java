package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import business.Bonificacion;
import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.DetallePedido;
import business.Factura;
import business.ItemFactura;
import business.MovimientoReserva;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
import business.Reserva;
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
import enumeration.TipoFactura;
import exception.ClienteException;
import exception.ProductoException;

@SuppressWarnings("unused")
public class Controller {

	private static Controller instance;
	private List<Cliente> clientes;
	private List<Pedido> pedidos;
	
	
	public static Controller getInstance() {
		if (instance==null)
			instance = new Controller();
		return instance;
	}
	
	
	
	private Controller() {
		clientes = new ArrayList<Cliente>();
		pedidos = new ArrayList<Pedido>();
	}

	
	
	public void crearCliente(ClienteDTO c) throws ClienteException  {
		if(ClienteDAO.getInstance().findByCuit(c.getCuit()) == null)
		{
			Cliente cliente=new Cliente(c.getCuit(), c.getRazon_social(), c.getTelefono(), c.getDireccion(), c.isR_inscripto(), c.getCondicionEsp());
			CuentaCorriente cc= new CuentaCorriente(c.getCuentaCorriente().getSaldo(), c.getCuentaCorriente().getLimite());
			cliente.setCuentaCorriente(cc);
			cliente.saveOrUpdate();
		}
		else
			throw new ClienteException("Cliente ya creado");
	}
	
	
	
	public void crearPedido (PedidoDTO p) throws ClienteException, ProductoException
	{
		Cliente cliente = buscarCliente(p.getCliente().getCuit());
		List<DetallePedido> detalles = new ArrayList<DetallePedido>();
		for (DetallePedidoDTO ddto : p.getDetalle())
		{ 
			DetallePedido dp = new DetallePedido(ddto.getCantidad(), Almacen.getInstance().giveMeAProduct(ddto.getProducto().getCodBarras()));
			dp.calcularSubTotal();
			detalles.add(dp);
		}
		Pedido pedido = new Pedido();
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

	
	
	
	private Cliente buscarCliente (String cuit) throws ClienteException
	{
		for (Cliente clie : clientes)
		{
			if (clie.getCuit().equals(cuit));
				return clie; //Busqueda en memoria.
		}
		Cliente cliente = ClienteDAO.getInstance().findByCuit(cuit);		
		clientes.add(cliente);
		return cliente;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	
	
	public List<PedidoDTO> listarPedidosPendientes ()
	{
		List<PedidoDTO> pdtos = new ArrayList<PedidoDTO>();
		List<Pedido> ps = PedidoDAO.getInstance().traerPedidosPendientes();
		for (Pedido p : ps)
			pdtos.add(p.toDTO());
		return pdtos;
	}
	
	
	
	public String validarCreditoCliente(int nropedido) 
	{
		Pedido p = PedidoDAO.getInstance().findByNro(nropedido);
		Cliente c= p.getCliente();
		if(!c.excedeLimite(p.getTotal_bruto()))
			return "El Cliente "+c.getRazon_social()+" CUIT "+c.getCuit()+" tiene límite suficiente para hacer este pedido. Autorizar (SI/NO)?";
		else
			return "El Cliente "+c.getRazon_social()+" CUIT "+c.getCuit()+" no tiene límite suficiente para hacer este pedido. Autorizar (SI/NO)?";
	}
	
	
	
	public void autorizarPedido (int nro)
	{
		Pedido p = PedidoDAO.getInstance().findByNro(nro);
		if (p.validarCompletarPedido())
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

	
	/*private boolean validarCompletarPedido(Pedido p) 
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
							op.updateMe();
							Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-sd-reservadoOP);
							op = Almacen.getInstance().buscarOPConDisponibilidad(dp.getProducto());
							op.agregarMovimientoReserva(dp.getCantidad()-sd-reservadoOP, p);
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
							op.updateMe();
							Almacen.getInstance().crearOrdenPedido(p, dp, dp.getCantidad()-reservadoOP);
							op = Almacen.getInstance().buscarOPConDisponibilidad(dp.getProducto());
							op.agregarMovimientoReserva(dp.getCantidad()-sd-reservadoOP, p);
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
	}*/

	
	
	public ClienteDTO mostrarCliente(String cuit) throws ClienteException {
			Cliente c= this.buscarCliente(cuit);
			return c.toDTO();
	}

	
	
	public void modificarCliente(ClienteDTO cdto) throws ClienteException {
		Cliente c= this.buscarCliente(cdto.getCuit());
		c.modify(cdto.getCuit(), cdto.getRazon_social(), cdto.getTelefono(), cdto.getDireccion(), cdto.isR_inscripto(), cdto.getCondicionEsp());
		//No tiene en cuenta ni cuenta corriente ni Movimientos. Es solo para updatear datos personales//	
		// modify hace un update a si mismo.
	}


	
	
	
	
	
	public List<UbicacionDTO> despacharPedido (PedidoDTO pdto)
	{
		List<UbicacionDTO> udtos = new ArrayList<UbicacionDTO>();
		List<Ubicacion> us;
		Pedido p = PedidoDAO.getInstance().findByNro(pdto.getNroPedido());
		us = p.despachar();
		for (Ubicacion u : us)
		{
			UbicacionDTO udto = new UbicacionDTO(u.getCalle(), u.getBloque(), u.getEstanteria(), u.getEstante(), u.getPosicion(), u.getCantidadActual());
			udtos.add(udto);
		}
		return udtos;
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

	
	
	
}

	