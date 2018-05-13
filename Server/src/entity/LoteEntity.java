package entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Lotes")
public class LoteEntity {
	
	@Id
	private String numero;
	
	private Date vencimiento;
	private String proveedor;
	private float precioventa;
	public LoteEntity() {
		super();
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
