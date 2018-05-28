package business;

import java.util.Date;

import dto.LoteDTO;

public class Lote {

	private String numero;
	private Date vencimiento;
	private Proveedor proveedor;
	private float precioventa;
	
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
	public float getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}
	public LoteDTO toDTO() {
		LoteDTO ldto = new LoteDTO();
		ldto.setNumero(this.numero);
		ldto.setPrecio(this.proveedor.getPrecio());
		ldto.setPrecioventa(this.precioventa);
		ldto.setProveedor(this.proveedor.getNombreProv());
		ldto.setVencimiento(this.vencimiento);
		return ldto;
	}
	
	
	
}
