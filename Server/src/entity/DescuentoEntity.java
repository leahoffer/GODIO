package entity;

import javax.persistence.Entity;

@Entity
public class DescuentoEntity extends CondicionEntity {

	private float porcentaje;

	public DescuentoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	
	
	
}
