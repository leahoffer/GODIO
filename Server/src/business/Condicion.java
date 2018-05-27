package business;

public abstract class Condicion {

	protected String condicion;
	
	
	public abstract float calcularMonto(int montoInicial);
	
	public Condicion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	
}
