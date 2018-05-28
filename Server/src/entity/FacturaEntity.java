package entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="Facturas")
public class FacturaEntity {

	@Id
	@GeneratedValue
	private int nro;
	private String tipo;
		
	@OneToOne (cascade=CascadeType.ALL)
	private ClienteEntity cliente;
	
	private float total;
	@OneToMany (cascade=CascadeType.ALL)
	protected List<ItemFacturaEntity> items;
	private float cancelado;
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<ItemFacturaEntity> getItems() {
		return items;
	}
	public void setItems(List<ItemFacturaEntity> items) {
		this.items = items;
	}
	public float getCancelado() {
		return cancelado;
	}
	public void setCancelado(float cancelado) {
		this.cancelado = cancelado;
	}
	
	
}
