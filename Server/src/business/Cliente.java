package business;

import dao.ClienteDAO;
import dto.ClienteDTO;

public class Cliente {

	private String cuit;
	private String razon_social;
	private String telefono;
	private String Direccion;
	private boolean r_inscripto;
	private CuentaCorriente cuentaCorriente;
	private String condicionEsp;
	
	public Cliente() {
		
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
	
	
	public float obtenerSaldo() {
		float resultado = 0;
		for (MovimientoCC mcc : this.cuentaCorriente.getMovimientos())
		{
			if (mcc.isSigno())
			{
				resultado = resultado + mcc.getMonto();
			}
			else
			{
				resultado = resultado - mcc.getMonto();
			}
		}
		return resultado;			
	}
	
	public boolean excedeLimite(float monto) {
		if (this.obtenerSaldo() > monto)
			return false;
		return true;
	}
	
	public void agregarMovimiento(float monto, boolean signo) {
		this.cuentaCorriente.agregarMovimiento(monto, signo);
		this.updateMe();
	}

	public void agregarBonificacion(float monto, String descripcion)
	{
		
	}
	
	public void updateMe() {

		ClienteDAO.getInstance().update(this);

	}

	public void saveOrUpdate() {
		// TODO Auto-generated method stub
		ClienteDAO.getInstance().createOrUpdate(this);
	}

	public ClienteDTO toDTO() {
		ClienteDTO cdto = new ClienteDTO();
		cdto.setCondicionEsp(this.condicionEsp);
		cdto.setCuentaCorriente(this.cuentaCorriente.toDTO());
		cdto.setCuit(this.cuit);
		cdto.setDireccion(this.Direccion);
		cdto.setR_inscripto(this.r_inscripto);
		cdto.setRazon_social(this.razon_social);
		cdto.setTelefono(this.telefono);
		return cdto;
	}
	
}
