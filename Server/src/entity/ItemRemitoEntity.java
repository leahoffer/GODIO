package entity;

import javax.persistence.*;

@Entity
@Table(name="Items_Remito")
public class ItemRemitoEntity {

	@Id
	@GeneratedValue
	private int nro;
	private int cantidad;
	
	@ManyToOne
	private ProductoEntity producto;

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

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public ItemRemitoEntity() {
		super();
	}
	
	
}
