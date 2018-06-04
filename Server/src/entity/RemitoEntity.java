package entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="Remitos")
public class RemitoEntity {

	@Id
	@GeneratedValue
	private String nro;
	@OneToMany
	private List<ItemRemitoEntity> items;
	@OneToOne
	private ClienteEntity cliente;
	private Date fecha;
	private boolean despachado;
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public List<ItemRemitoEntity> getItems() {
		return items;
	}
	public void setItems(List<ItemRemitoEntity> items) {
		this.items = items;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isDespachado() {
		return despachado;
	}
	public void setDespachado(boolean despachado) {
		this.despachado = despachado;
	}
	public RemitoEntity() {
		super();
	}
	
	
	
	
}
