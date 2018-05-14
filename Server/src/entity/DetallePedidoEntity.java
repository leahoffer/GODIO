package entity;

import javax.persistence.*;

@Entity
@Table(name="Detalles_Pedido")
public class DetallePedidoEntity {

	
	@Id
	@GeneratedValue
	private int id;
	private  float subtotal;
	private int  cantidad;
	@OneToOne
	private ProductoEntity producto;

	public DetallePedidoEntity() {
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
	public ProductoEntity getProducto() {
		return producto;
	}
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

}
