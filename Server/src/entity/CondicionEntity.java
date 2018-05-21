package entity;

import javax.persistence.*;

	
@Entity
@Table(name="Condiciones")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
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
