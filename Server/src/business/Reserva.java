package business;

import java.util.Date;

import dao.AlmacenDAO;

public class Reserva {

	private int numero;
	private Producto producto;
	private int cantidad;
	private Pedido pedido;
	private boolean completa;
	private Date fecha;
	public Reserva(Producto producto, int cantidad, Pedido pedido, boolean completa, Date fecha) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.completa = completa;
		this.fecha = fecha;
	}
	public Reserva() {
		this.producto = new Producto();
		this.pedido = new Pedido();
		this.fecha = new Date();
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
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
	public void createMe() {
		AlmacenDAO.getInstance().createReserva(this);
		
	}
	public void updateMe() {
		// TODO Auto-generated method stub
		AlmacenDAO.getInstance().updateReserva(this);
	}
	
	
	
	
	
}
