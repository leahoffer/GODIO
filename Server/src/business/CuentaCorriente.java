package business;

import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente {

	private String id;
	private float saldo;
	private float limite;
	private List<MovimientoCC> movimientos;
	private List<Condicion> condiciones;
	
	public CuentaCorriente()
	{
		this.movimientos = new ArrayList<MovimientoCC>();
		this.condiciones = new ArrayList<Condicion>();
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
	public List<MovimientoCC> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoCC> movimientos) {
		this.movimientos = movimientos;
	}
	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
	
	
	
	
	
}