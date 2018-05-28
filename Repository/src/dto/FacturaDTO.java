package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import enumeration.TipoFactura;

public class FacturaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8180412007257528106L;
	private int nro;
	private TipoFactura tipo;
	private ClienteDTO cliente;
	private float total;
	private List<ItemFacturaDTO> items;
	private float cancelado;
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
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public List<ItemFacturaDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemFacturaDTO> items) {
		this.items = items;
	}
	public float getCancelado() {
		return cancelado;
	}
	public void setCancelado(float cancelado) {
		this.cancelado = cancelado;
	}
	public FacturaDTO() {
		super();
		items= new ArrayList<ItemFacturaDTO>();
		// TODO Auto-generated constructor stub
	}
	
	
}
