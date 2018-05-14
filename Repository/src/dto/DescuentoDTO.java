package dto;

import java.io.Serializable;

public class DescuentoDTO extends CondicionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 932885426969602611L;
	private float porcentaje;
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	public DescuentoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
