package business;

public class MovimientoCC {

	private float monto;
	private boolean signo;
	private Factura facturaAplicada;
	
	public MovimientoCC(float monto, boolean signo) {
		this.monto = monto;
		this.signo = signo;
		this.facturaAplicada = new Factura();
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
	
	
	
	
	
}
