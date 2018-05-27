package entity;




import java.util.Date;
import java.util.List;

import javax.persistence.*;




@Entity
@Table(name="Pedidos")
public class PedidoEntity {
	
	@Id
	@GeneratedValue
	private int nroPedido;
	
	@ManyToOne
	private ClienteEntity cliente;
	
	@OneToOne(cascade=CascadeType.ALL)
	private FacturaEntity factura;
	
	private String dir_entrega;
	private Date fecha;
	private Date fecha_despacho;
	private float total_bruto;
	private boolean despachable;
	private String estado;
	private String motivoEstado;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<DetallePedidoEntity> detalles;
	
	private String aclaracionEspecial;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<CondicionEntity> condicionesAplicadas;

	public int getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public FacturaEntity getFactura() {
		return factura;
	}

	public void setFactura(FacturaEntity factura) {
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

	public List<DetallePedidoEntity> getDetalle() {
		return detalles;
	}

	public void setDetalle(List<DetallePedidoEntity> detalles) {
		this.detalles = detalles;
	}

	public String getAclaracionEspecial() {
		return aclaracionEspecial;
	}

	public void setAclaracionEspecial(String aclaracionEspecial) {
		this.aclaracionEspecial = aclaracionEspecial;
	}

	public List<CondicionEntity> getCondicionesAplicadas() {
		return condicionesAplicadas;
	}

	public void setCondicionesAplicadas(List<CondicionEntity> condicionesAplicadas) {
		this.condicionesAplicadas = condicionesAplicadas;
	}

	public PedidoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}