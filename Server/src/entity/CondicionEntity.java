package entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DIS", discriminatorType=DiscriminatorType.STRING)
@Table(name="Condiciones")
public class CondicionEntity{

	@Id
	@GeneratedValue
	@Column (name = "nroCon")
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
