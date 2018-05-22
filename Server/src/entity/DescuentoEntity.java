package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue (value = "Descuento")
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
