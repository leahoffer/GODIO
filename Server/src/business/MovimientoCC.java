package business;

public class MovimientoCC {

	private float monto;
	private boolean signo;
	
	public MovimientoCC(float monto, boolean signo) {
		this.monto = monto;
		this.signo = signo;
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
