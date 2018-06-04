package business;

import dao.AlmacenDAO;
import dto.UbicacionDTO;

public class Ubicacion {

	private String calle;
	private int bloque;
	private int estanteria;
	private int estante;
	private int posicion;
	private int cantidadActual;
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
	public void update() {
		AlmacenDAO.getInstance().updateUbicacion(this);
		
	}
	public UbicacionDTO toDTO() {
		UbicacionDTO udto = new UbicacionDTO();
		udto.setCalle(this.calle);
		udto.setBloque(this.bloque);
		udto.setCantidadActual(this.cantidadActual);
		udto.setEstante(this.estante);
		udto.setEstanteria(this.estanteria);
		udto.setPosicion(this.posicion);
		return udto;
	}
	public boolean soyLaUbicacion(Ubicacion ub) {
		if (ub.getBloque()==this.bloque)
		{
			if (ub.getCalle().equalsIgnoreCase(this.getCalle()))
			{
				if(ub.getEstante()==this.getEstante())
				{ 
					if (ub.getEstanteria()==this.getEstanteria())
					{
						if(ub.getPosicion()==this.getPosicion())
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public Ubicacion(String calle, int bloque, int estanteria, int estante, int posicion) {
		super();
		this.calle = calle;
		this.bloque = bloque;
		this.estanteria = estanteria;
		this.estante = estante;
		this.posicion = posicion;
	}
	public Ubicacion() {
	}
	
	
	
	
}
