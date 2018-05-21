package business;

public class Descuento extends Condicion {

	
	private float porcentaje;
	
	public float calcularMonto(int montoInicial) {
		return montoInicial*porcentaje;
	}
	
	public float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	

}
