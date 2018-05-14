package dto;

import java.io.Serializable;
import java.util.Date;

import business.Proveedor;

public class LoteDTO implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311167639007322785L;
	private String numero;
	private Date vencimiento;
	private ProveedorDTO proveedor;
	private float precioventa;
	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	public ProveedorDTO getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}
	public float getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}
	
	
}
