package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AlmacenDAO;

public class Remito {

	private String nro;
	private List<ItemRemito> items;
	private Cliente cliente;
	private Date fecha;
	private boolean despachado;
	
	public Remito() {
		this.items = new ArrayList<ItemRemito>();
		this.cliente = new Cliente();
		this.fecha = new Date();
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public List<ItemRemito> getItems() {
		return items;
	}

	public void setItems(List<ItemRemito> items) {
		this.items = items;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isDespachado() {
		return despachado;
	}

	public void setDespachado(boolean despachado) {
		this.despachado = despachado;
	}

	public void save() {
		AlmacenDAO.getInstance().saveRemito(this);
		
	}
	
	
	
}
