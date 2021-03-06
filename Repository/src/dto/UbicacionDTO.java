package dto;

import java.io.Serializable;

public class UbicacionDTO implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5376308883770247095L;
	private String calle;
	private int bloque;
	private int estanteria;
	private int estante;
	private int posicion;
	private int cantidadActual;
	
	public UbicacionDTO() {
		super();
	}
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getBloque() {
		return bloque;
	}
	public void setBloque(int bloque) {
		this.bloque = bloque;
	}
	public int getEstanteria() {
		return estanteria;
	}
	public void setEstanteria(int estanteria) {
		this.estanteria = estanteria;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public int getCantidadActual() {
		return cantidadActual;
	}
	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public UbicacionDTO(String calle, int bloque, int estanteria, int estante, int posicion, int cantidadActual) {
		super();
		this.calle = calle;
		this.bloque = bloque;
		this.estanteria = estanteria;
		this.estante = estante;
		this.posicion = posicion;
		this.cantidadActual = cantidadActual;
	}
	
	
	
}
