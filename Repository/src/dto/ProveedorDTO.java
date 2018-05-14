package dto;

import java.io.Serializable;

public class ProveedorDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 304326980810592198L;
	private String nombreProv;
	private float precio;
	
	
	public ProveedorDTO() {
		super();
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
