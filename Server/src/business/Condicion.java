package business;

public abstract class Condicion {

	protected String condicion;
	//Como se escribian los metodos de cosos abstractos para que los hijos lo implementen??
	
	
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
