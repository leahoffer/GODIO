package entity;



import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name="Reservas")
public class ReservaEntity {

	@Id
	@GeneratedValue
	private int numero;
	
	@ManyToOne
	private ProductoEntity producto;
	private int cantidad;
	
	@OneToOne
	private PedidoEntity pedido;
	private boolean completa;
	private Date fecha;
	public ReservaEntity() {
		super();
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public ProductoEntity getProducto() {
		return producto;
	}
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public PedidoEntity getPedido() {
		return pedido;
	}
	public void setPedido(PedidoEntity pedido) {
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
