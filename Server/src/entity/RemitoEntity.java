package entity;


import java.sql.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="Remitos")
public class RemitoEntity {

	@Id
	@GeneratedValue
	private int nro;
	@OneToMany (cascade=CascadeType.ALL)
	private List<ItemRemitoEntity> items;
	@OneToOne
	private ClienteEntity cliente;
	private Date fecha;
	private boolean despachado;
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
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
