package entity;

import java.util.List;
import javax.persistence.*;



@MappedSuperclass
public class CuentaCorrienteEntity {

	@Id
	protected String id;
	protected float saldo;
	protected float limite;
	@OneToMany 
	protected List<MovimientoCCEntity> movimientos;
	@OneToMany
	protected List<CondicionEntity> condiciones;
	
	
	
	public CuentaCorrienteEntity() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public float getLimite() {
		return limite;
	}
	public void setLimite(float limite) {
		this.limite = limite;
	}
	public List<MovimientoCCEntity> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoCCEntity> movimientos) {
		this.movimientos = movimientos;
	}
	public List<CondicionEntity> getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(List<CondicionEntity> condiciones) {
		this.condiciones = condiciones;
	}
	
	
}
