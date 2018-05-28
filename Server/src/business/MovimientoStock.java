package business;

import controller.Almacen;
import dao.AlmacenDAO;
import enumeration.TipoMovimientoStock;

public class MovimientoStock {

	private TipoMovimientoStock tipo;
	private int cantidad;
	private String responsable;
	private Producto producto;
	private String motivo;
	
	public MovimientoStock() {
		this.producto = new Producto();
	}

	public TipoMovimientoStock getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimientoStock tipo) {
		this.tipo = tipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void save() {
		AlmacenDAO.getInstance().saveMovimientoStock(this);
	}
	
	
	
	
}
