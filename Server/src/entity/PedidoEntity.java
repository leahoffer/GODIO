package entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Entity;

import enumeration.EstadoPedido;

@Entity
public class PedidoEntity {
	@Id
	private int nroPedido;
	private ClienteEntity cliente;
	private FacturaEntity factura;
	private String dir_entrega;
	private Date fecha;
	private Date fecha_despacho;
	private float total_bruto;
	private boolean despachable;
	private EstadoPedido estado;
	private String motivoEstado;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<DetallePedidoEntity> detalle;
	private String aclaracionEspecial;
	@OneToOne(cascade=CascadeType.ALL)
	private List<CondicionEntity> condicionesAplicadas;
	
	public PedidoEntity() {
		
	}

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

	public List<DetallePedidoEntity> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallePedidoEntity> detalle) {
		this.detalle = detalle;
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
	
	
	
	
	
}