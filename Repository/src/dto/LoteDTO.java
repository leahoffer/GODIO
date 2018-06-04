package dto;

import java.io.Serializable;
import java.util.Date;



public class LoteDTO implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311167639007322785L;
	private String numero;
	private Date vencimiento;
	private String proveedor;
	private float precio;
	private float precioventa;
	
	
	public LoteDTO(String numero, Date vencimiento, String proveedor, float precio, float precioventa) {
		super();
		this.numero = numero;
		this.vencimiento = vencimiento;
		this.proveedor = proveedor;
		this.precio = precio;
		this.precioventa = precioventa;
	}
	public LoteDTO() {
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
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
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public float getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}
	
	
}
