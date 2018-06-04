package business;

import dto.ItemFacturaDTO;

public class ItemFactura {

	private float subtotal;
	private int cantidad;
	private Producto producto;
	
	public ItemFactura(float subtotal, int cantidad, Producto producto) {
		super();
		this.subtotal = subtotal;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public ItemFactura() {
		// TODO Auto-generated constructor stub
	}

	public void calcularSubtotal()
	{
		subtotal=producto.getPrecio()*cantidad;
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

	public ItemFacturaDTO toDTO() {
		ItemFacturaDTO ifdto = new ItemFacturaDTO();
		ifdto.setCantidad(this.cantidad);
		ifdto.setProducto(this.producto.toDTO());
		ifdto.setSubtotal(this.subtotal);
		return ifdto;
	}
	
	
}
