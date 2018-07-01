package dto;

import java.io.Serializable;
import java.util.List;


import enumeration.EstadoOP;

public class OrdenPedidoDTO implements Serializable{

	private int nro;
	private String estado;
	private PedidoDTO pedidoOrigen;
	private ProductoDTO producto;
	private int cantidadPedida;
	private List<ProveedorDTO> ultimosTresProv;
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public PedidoDTO getPedidoOrigen() {
		return pedidoOrigen;
	}
	public void setPedidoOrigen(PedidoDTO pedidoOrigen) {
		this.pedidoOrigen = pedidoOrigen;
	}
	public ProductoDTO getProducto() {
		return producto;
	}
	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	public int getCantidadPedida() {
		return cantidadPedida;
	}
	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}
	public List<ProveedorDTO> getUltimosTresProv() {
		return ultimosTresProv;
	}
	public void setUltimosTresProv(List<ProveedorDTO> ultimosTresProv) {
		this.ultimosTresProv = ultimosTresProv;
	}
	public OrdenPedidoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
