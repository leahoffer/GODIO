package entity;

import javax.persistence.*;

@Entity
@Table(name="Ubicaciones")
public class UbicacionEntity {

	@EmbeddedId
	private UbicacionId idUbicacion;

	public UbicacionEntity() {
		super();
	}

	public UbicacionId getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(UbicacionId idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	
	
	
}
