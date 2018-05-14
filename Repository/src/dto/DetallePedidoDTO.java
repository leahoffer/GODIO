package dto;

import java.io.Serializable;



public class DetallePedidoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -62009040202835015L;

	private  float subtotal;
	private int  cantidad;
	private ProductoDTO producto;
	
	public DetallePedidoDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
	
}
