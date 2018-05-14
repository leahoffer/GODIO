package entity;

import javax.persistence.*;

@Entity
@Table(name="Movimientos_CC")
public class MovimientoCCEntity {

	@Id
	@GeneratedValue
	private int nro;
	private float monto;
	private boolean signo;
	@OneToOne (cascade=CascadeType.ALL)
	private FacturaEntity facturaAplicada;
	
	public MovimientoCCEntity() {
		super();
	}

	public MovimientoCCEntity(float monto, boolean signo, FacturaEntity facturaAplicada) {
		super();
		this.monto = monto;
		this.signo = signo;
		this.facturaAplicada = facturaAplicada;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public boolean isSigno() {
		return signo;
	}

	public void setSigno(boolean signo) {
		this.signo = signo;
	}

	public FacturaEntity getFacturaAplicada() {
		return facturaAplicada;
	}

	public void setFacturaAplicada(FacturaEntity facturaAplicada) {
		this.facturaAplicada = facturaAplicada;
	}
	
	
}
