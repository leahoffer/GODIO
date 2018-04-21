package business;

public class Cliente {

	private String cuit;
	private String razon_social;
	private String telefono;
	private String Direccion;
	private boolean r_inscripto;
	private CuentaCorriente cuentaCorriente;
	private String condicionEsp;
	
	public Cliente() {
		this.cuentaCorriente = new CuentaCorriente();
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public boolean isR_inscripto() {
		return r_inscripto;
	}

	public void setR_inscripto(boolean r_inscripto) {
		this.r_inscripto = r_inscripto;
	}

	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public String getCondicionEsp() {
		return condicionEsp;
	}

	public void setCondicionEsp(String condicionEsp) {
		this.condicionEsp = condicionEsp;
	}
	
	
	
}
