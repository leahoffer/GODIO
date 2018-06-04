package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UbicacionId implements Serializable{
	private static final long serialVersionUID = 1L;
	private String calle;
	private int bloque;
	private int estanteria;
	private int estante;
	private int posicion;
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
	public UbicacionId(String calle, int bloque, int estanteria, int estante, int posicion) {
		super();
		this.calle = calle;
		this.bloque = bloque;
		this.estanteria = estanteria;
		this.estante = estante;
		this.posicion = posicion;
	}
	public UbicacionId() {
		// TODO Auto-generated constructor stub
	}

	
	
	
}