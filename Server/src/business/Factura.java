package business;

import java.util.ArrayList;
import java.util.List;

import dto.FacturaDTO;
import enumeration.TipoFactura;

public class Factura {

	private int nro;
	private TipoFactura tipo;
	private Cliente cliente;
	private float total;
	private List<ItemFactura> items;
	private float cancelado;
	
	
	public Factura(int nro, TipoFactura tipo, Cliente cliente, float total, List<ItemFactura> items, float cancelado) {
		super();
		this.nro = nro;
		this.tipo = tipo;
		this.cliente = cliente;
		this.total = total;
		this.items = new ArrayList<ItemFactura>();
		this.cancelado = cancelado;
	}
	public Factura() {
		this.cliente = new Cliente();
		this.items = new ArrayList<ItemFactura>();
	}
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
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
	public FacturaDTO toDTO() {
		FacturaDTO fdto = new FacturaDTO();
		fdto.setCancelado(this.cancelado);
		//Lo comento porque me va a entrar en loop infinito esto es horrible
		//fdto.setCliente(this.cliente.toDTO());
		fdto.setNro(this.nro);
		fdto.setTipo(this.tipo);
		fdto.setTotal(this.total);
		for (ItemFactura ifa : this.items)
		{
			fdto.getItems().add(ifa.toDTO());
		}
		return fdto;
	}
	public float calcularTotal() {
		float total = 0;
		for (ItemFactura ifa : this.items)
		{
			total = total + ifa.getSubtotal();
		}
		return total;
	}
	
	
	
	
}
