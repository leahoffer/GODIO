package dto;

import java.io.Serializable;

public class BonificacionDTO extends CondicionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020939910575553758L;
	private float monto;
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public BonificacionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
