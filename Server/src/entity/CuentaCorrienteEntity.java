package entity;

import java.util.List;
import javax.persistence.*;


@Entity
public class CuentaCorrienteEntity {

	@Id
	private String id;
	private float saldo;
	private float limite;
	//@OneToMany era cuando UNA de la Entity que estoy tocando tiene MUCHAS de las que están abajo de esto?
	private List<MovimientoCCEntity> movimientos;
	//Mismo comment que arriba :)
	private List<CondicionEntity> condiciones;
	
	
	
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
