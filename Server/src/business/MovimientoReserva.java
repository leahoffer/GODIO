package business;

import java.util.Date;

public class MovimientoReserva {

	private int nro;
	private int cantidad;
	private Pedido pedido;
	private boolean completa;
	private Date fecha;
	
	public MovimientoReserva() {
		this.pedido = new Pedido();
		this.fecha = new Date();
	}

	public MovimientoReserva(int nro, int cantidad, Pedido pedido, boolean completa, Date fecha) {
		super();
		this.nro = nro;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.completa = completa;
		this.fecha = fecha;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public boolean isCompleta() {
		return completa;
	}

	public void setCompleta(boolean completa) {
		this.completa = completa;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
