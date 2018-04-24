package business;

import java.util.Date;

public class Lote {

	private String numero;
	private Date vencimiento;
	private Proveedor proveedor;
	public Lote() {
		this.vencimiento = new Date();
		this.proveedor = new Proveedor();
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
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
	
}
