package entity;

import javax.persistence.Id;

import org.hibernate.annotations.Entity;
	


@Entity
public class CondicionEntity extends CuentaCorrienteEntity{

@Id
private  String descripcion;


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
