package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class PedidoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2071632209672397718L;
	private int nroPedido;
	private ClienteDTO cliente;
	private FacturaDTO factura;
	private String dir_entrega;
	private Date fecha;
	private Date fecha_despacho;
	private float total_bruto;
	private boolean despachable;
	private String estado;
	private String motivoEstado;
	private List<DetallePedidoDTO> detalle;
	private String aclaracionEspecial;
	private List<CondicionDTO> condicionesAplicadas;
	public PedidoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public int getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public FacturaDTO getFactura() {
		return factura;
	}
	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}
	public String getDir_entrega() {
		return dir_entrega;
	}
	public void setDir_entrega(String dir_entrega) {
		this.dir_entrega = dir_entrega;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFecha_despacho() {
		return fecha_despacho;
	}
	public void setFecha_despacho(Date fecha_despacho) {
		this.fecha_despacho = fecha_despacho;
	}
	public float getTotal_bruto() {
		return total_bruto;
	}
	public void setTotal_bruto(float total_bruto) {
		this.total_bruto = total_bruto;
	}
	public boolean isDespachable() {
		return despachable;
	}
	public void setDespachable(boolean despachable) {
		this.despachable = despachable;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivoEstado() {
		return motivoEstado;
	}
	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}
	public List<DetallePedidoDTO> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<DetallePedidoDTO> detalle) {
		this.detalle = detalle;
	}
	public String getAclaracionEspecial() {
		return aclaracionEspecial;
	}
	public void setAclaracionEspecial(String aclaracionEspecial) {
		this.aclaracionEspecial = aclaracionEspecial;
	}
	public List<CondicionDTO> getCondicionesAplicadas() {
		return condicionesAplicadas;
	}
	public void setCondicionesAplicadas(List<CondicionDTO> condicionesAplicadas) {
		this.condicionesAplicadas = condicionesAplicadas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
