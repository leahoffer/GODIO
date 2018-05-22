package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue (value = "Bonificacion")
public class BonificacionEntity extends CondicionEntity {

	private float monto;

	public BonificacionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	
}
