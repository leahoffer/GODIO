package business;

import dto.DetallePedidoDTO;

public class DetallePedido {
	
	public DetallePedido(int cantidad, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
	}
	private int id;
	private  float subtotal;
	private int  cantidad;
	private Producto producto;

	public DetallePedido() {
		super();
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void calcularSubTotal(){
		this.subtotal= this.cantidad*this.getProducto().getPrecio();
	}
	public DetallePedidoDTO toDTO() {
		DetallePedidoDTO dpdto = new DetallePedidoDTO();
		dpdto.setCantidad(this.cantidad);
		dpdto.setProducto(this.producto.toDTO());
		dpdto.setSubtotal(this.subtotal);
		return dpdto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
