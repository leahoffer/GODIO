package business;

import java.util.ArrayList;
import java.util.List;

import enumeration.TipoFactura;

public class Factura {

	private String nro;
	private TipoFactura tipo;
	private Cliente cliente;
	private float total;
	private List<ItemFactura> items;
	private float cancelado;
	public Factura() {
		this.cliente = new Cliente();
		this.items = new ArrayList<ItemFactura>();
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public TipoFactura getTipo() {
		return tipo;
	}
	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<ItemFactura> getItems() {
		return items;
	}
	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	public float getCancelado() {
		return cancelado;
	}
	public void setCancelado(float cancelado) {
		this.cancelado = cancelado;
	}
	
	
	
	
}
