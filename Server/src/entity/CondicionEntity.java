package entity;

import javax.persistence.*;

	
@Entity
@Table(name="Condiciones")
public class CondicionEntity{

	@Id
	@GeneratedValue
	private int nro;
	private String descripcion;


	public CondicionEntity ()
	{	
		super();	
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
