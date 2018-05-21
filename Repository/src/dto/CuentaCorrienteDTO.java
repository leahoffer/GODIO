package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CuentaCorrienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1626370027886861904L;
	private int id;
	private float saldo;
	private float limite;
	private List<MovimientoCCDTO> movimientos;
	private List<CondicionDTO> condiciones;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<MovimientoCCDTO> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoCCDTO> movimientos) {
		this.movimientos = movimientos;
	}
	public List<CondicionDTO> getCondiciones() {
		return condiciones;
	}
	public void setCondiciones(List<CondicionDTO> condiciones) {
		this.condiciones = condiciones;
	}
	public CuentaCorrienteDTO() {
		super();
		movimientos = new ArrayList<MovimientoCCDTO>();
		condiciones = new ArrayList<CondicionDTO>();
		// TODO Auto-generated constructor stub
	}
	
	
}
