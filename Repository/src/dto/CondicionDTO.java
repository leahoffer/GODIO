package dto;

import java.io.Serializable;

public class CondicionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1853235636612426629L;
	protected String condicion;
	
	
	
	
	public CondicionDTO() {
		super();
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
}
