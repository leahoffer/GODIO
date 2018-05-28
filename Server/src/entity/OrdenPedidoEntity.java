package entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Ordenes_Pedido")
public class OrdenPedidoEntity {

	@Id
	@GeneratedValue
	private int nro;
	private String estado;
	@ManyToOne
	private PedidoEntity pedidoOrigen;
	@ManyToOne
	private ProductoEntity producto;
	private int cantidadPedida;
//	private List<Pair<String, Float>> ultimosTresProv;
	@OneToMany (cascade=CascadeType.ALL)
	private List <MovimientoReservaEntity> movReserva;
	
	public OrdenPedidoEntity() {}
	
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public PedidoEntity getPedidoOrigen() {
		return pedidoOrigen;
	}
	public void setPedidoOrigen(PedidoEntity pedidoOrigen) {
		this.pedidoOrigen = pedidoOrigen;
	}
	public ProductoEntity getProducto() {
		return producto;
	}
	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}
	public int getCantidadPedida() {
		return cantidadPedida;
	}
	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}
	public List<MovimientoReservaEntity> getMovReserva() {
		return movReserva;
	}
	public void setMovReserva(List<MovimientoReservaEntity> movReserva) {
		this.movReserva = movReserva;
	}
	
	
	
	
}
