package business;

import java.util.ArrayList;
import java.util.List;

import enumeration.EstadoOP;

public class OrdenPedido {

	private String nro;
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

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
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
		// TODO Auto-generated method stub
		return false;
	}

	public void agregarMovimientoReserva(int i, Pedido p) {
		// TODO Auto-generated method stub
		
	}

	public int disponible() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void saveMe() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
