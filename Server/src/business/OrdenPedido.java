package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Almacen;
import controller.Compras;
import dao.AlmacenDAO;
import dao.ComprasDAO;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;

@SuppressWarnings("unused")
public class OrdenPedido {

	private int nro;
	private EstadoOP estado;
	private Pedido pedidoOrigen;
	private Producto producto;
	private int cantidadPedida;
	private List<Proveedor> ultimosTresProv;
	private List <MovimientoReserva> movReserva;
	
	public OrdenPedido() {
		this.ultimosTresProv = new ArrayList<Proveedor>();
		this.movReserva = new ArrayList<MovimientoReserva>();
	}

	public OrdenPedido(Pedido p, Producto pr, int i) {
		this.estado = EstadoOP.Pendiente;
		this.pedidoOrigen = p;
		this.producto = pr;
		this.cantidadPedida = calcularCantidadAPedir(i, pr);
	}
	
	
	private int calcularCantidadAPedir(int i, Producto producto) {
		int cantidad = 0;
		while (cantidad < i)
		{
			cantidad = cantidad + producto.getCantAComprar();
		}
		return cantidad;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public EstadoOP getEstado() {
		return estado;
	}

	public void setEstado(EstadoOP estado) {
		this.estado = estado;
	}

	public Pedido getPedidoOrigen() {
		return pedidoOrigen;
	}

	public void setPedidoOrigen(Pedido pedidoOrigen) {
		this.pedidoOrigen = pedidoOrigen;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public List<Proveedor> getUltimosTresProv() {
		return ultimosTresProv;
	}

	public void setUltimosTresProv(List<Proveedor> ultimosTresProv) {
		this.ultimosTresProv = ultimosTresProv;
	}

	public List<MovimientoReserva> getMovReserva() {
		return movReserva;
	}

	public void setMovReserva(List<MovimientoReserva> movReserva) {
		this.movReserva = movReserva;
	}

	public boolean calcularDisponible(int i) {
		int reservas = 0;
		for (MovimientoReserva mr : this.getMovReserva())
		{
			reservas = reservas + mr.getCantidad();
		}
		if (i >= (this.getCantidadPedida()-reservas))
			return false;
		return true;
	}

	public void agregarMovimientoReserva(int i, Pedido p) {
		MovimientoReserva mr = new MovimientoReserva();
		mr.setCantidad(i);
		mr.setCompleta(false);
		mr.setFecha(new Date());
		mr.setPedido(p);
		this.movReserva.add(mr);
		updateMe();
		
	}

	public void updateMe() {
		Compras.getInstance().updateOP(this);
		
	}

	public int disponible() {
		int reservas = 0;
		for (MovimientoReserva mr : this.getMovReserva())
		{
			reservas = reservas + mr.getCantidad();
		}
		return this.getCantidadPedida()-reservas;
	}

	public void createMe() {
		Compras.getInstance().createOP(this);		
	}

	public void cerrar() {
		this.estado = EstadoOP.Completa;
		this.updateMe();
		if (!this.movReserva.isEmpty())
		{
			for (MovimientoReserva mr : this.movReserva)
			{
				Reserva r = Almacen.getInstance().convertirMovimientoReserva(mr, this.producto);
				r.createMe();
				mr.setCompleta(true);
				revalidarPedido(mr.getPedido());
			}
		}
		
	}
	
	
	private void revalidarPedido(Pedido pedido) {
		boolean completoONo = true;
		List<OrdenPedido> ordenesPendientes = ComprasDAO.getInstance().buscarOPSPendientesOReservadas();
		if(!ordenesPendientes.isEmpty())
		{
			for (OrdenPedido op : ordenesPendientes)
			{
				for (MovimientoReserva mr : op.getMovReserva())
				{
					if (mr.getPedido().getNroPedido() == pedido.getNroPedido())
						completoONo = false;
				}
			}
		}
		if (completoONo)
		{
			pedido.setEstado(EstadoPedido.PendienteDespacho);
			pedido.update();
		}
	}
	
	
	
	
}
