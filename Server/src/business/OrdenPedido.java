package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Almacen;
import controller.Compras;
import dao.AlmacenDAO;
import enumeration.EstadoOP;

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
	
	
	
	
}
