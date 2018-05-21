package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PedidoDAO;
import enumeration.EstadoPedido;

public class Pedido {

	private int nroPedido;
	private Cliente cliente;
	private Factura factura;
	private String dir_entrega;
	private Date fecha;
	private Date fecha_despacho;
	private float total_bruto;
	private boolean despachable;
	private EstadoPedido estado;
	private String motivoEstado;
	private List<DetallePedido> detalle;
	private String aclaracionEspecial;
	private List<Condicion> condicionesAplicadas;
	
	public Pedido() {
		//Polémico. Después vemos si simplemente creamos vacíos y rellenamos o si asignamos con objetos creados
		this.cliente = new Cliente();
		this.factura = new Factura();
		this.detalle = new ArrayList<DetallePedido>();
		this.condicionesAplicadas = new ArrayList<Condicion>();
	}

	public int getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
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

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public String getMotivoEstado() {
		return motivoEstado;
	}

	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}

	public List<DetallePedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallePedido> detalle) {
		this.detalle = detalle;
	}

	public String getAclaracionEspecial() {
		return aclaracionEspecial;
	}

	public void setAclaracionEspecial(String aclaracionEspecial) {
		this.aclaracionEspecial = aclaracionEspecial;
	}

	public List<Condicion> getCondicionesAplicadas() {
		return condicionesAplicadas;
	}

	public void setCondicionesAplicadas(List<Condicion> condicionesAplicadas) {
		this.condicionesAplicadas = condicionesAplicadas;
	}

	public void update() {
		PedidoDAO.getInstance().update(this);
	}

	
	
	
	
	
}