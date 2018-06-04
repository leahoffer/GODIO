package dto;

import java.io.Serializable;



public class MovimientoCCDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 178898883454508984L;
	private float monto;
	private boolean signo;
	private FacturaDTO facturaAplicada;
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
	public FacturaDTO getFacturaAplicada() {
		return facturaAplicada;
	}
	public void setFacturaAplicada(FacturaDTO facturaAplicada) {
		this.facturaAplicada = facturaAplicada;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MovimientoCCDTO() {
		super();
	}
	
	
}
