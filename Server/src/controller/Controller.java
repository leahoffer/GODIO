package controller;

import java.util.ArrayList;
import java.util.List;

import business.Bonificacion;
import business.Cliente;
import business.Condicion;
import business.CuentaCorriente;
import business.Descuento;
import business.DetallePedido;
import business.Factura;
import business.ItemFactura;
import business.MovimientoCC;
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
import dto.CondicionDTO;
import dto.CuentaCorrienteDTO;
import dto.DetallePedidoDTO;
import dto.MovimientoCCDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import entity.BonificacionEntity;
import entity.CondicionEntity;
import entity.DescuentoEntity;
import entity.MovimientoCCEntity;
import entity.ProductoEntity;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;
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
	
	
	
	public PedidoDTO validarCreditoCliente(int nropedido) 
	{
		Pedido p = PedidoDAO.getInstance().findByNro(nropedido);
		Cliente c= p.getCliente();
		PedidoDTO pdto = p.toDTO();
		if(!c.excedeLimite(p.getTotal_bruto()))
			pdto.setEstado("Autorizado");
		else
			pdto.setEstado("Rechazado");
		
		return pdto;
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


	
	
	
	
	
	public List<UbicacionDTO> despacharPedido (int nropedido)
	{
		List<UbicacionDTO> udtos = new ArrayList<UbicacionDTO>();
		List<Ubicacion> us;
		Pedido p = PedidoDAO.getInstance().findByNro(nropedido);
		//buscarUbicacionesParaDespachar se va a encargar de crear los movimientos y de actualizar las ubicaciones que encuentre y devuelva
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

	