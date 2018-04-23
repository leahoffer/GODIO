package business;

public class Proveedor {

	private String nombreProv;
	private float precio;
	
	public Proveedor(String nombreProv, float precio) {
		this.nombreProv = nombreProv;
		this.precio = precio;
	}

	public String getNombreProv() {
		return nombreProv;
	}

	public void setNombreProv(String nombreProv) {
		this.nombreProv = nombreProv;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
}
