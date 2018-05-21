package business;

public class Bonificacion extends Condicion {

	
	private float monto;
	
	public float calcularMonto(int montoInicial) {
		return montoInicial-monto;
	}
	
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}

}
