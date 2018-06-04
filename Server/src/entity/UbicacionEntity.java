package entity;

import javax.persistence.*;

@Entity
@Table(name="Ubicaciones")
public class UbicacionEntity {

	@EmbeddedId
	private UbicacionId idUbicacion;
	private int cantidadActual;

	public UbicacionEntity() {
		super();
	}

	public UbicacionId getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(UbicacionId idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public UbicacionEntity(UbicacionId idUbicacion, int cantidadActual) {
		super();
		this.idUbicacion = idUbicacion;
		this.cantidadActual = cantidadActual;
	}
	
	
	
}
