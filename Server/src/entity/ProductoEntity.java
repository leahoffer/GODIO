package entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="Productos")
public class ProductoEntity {

	@Id
	private String codBarras;
	private String marca;
	private String descripcion;
	private String estado;
	private float tamaño;
	private int unidad;
	private float precio;
	private int cantPosicion;
	@OneToMany
	protected List<UbicacionEntity> ubicaciones;
	private String presentacion;
	@OneToOne
	private LoteEntity lote;
	private int cantAComprar;

	
	public ProductoEntity() {
		super();
	}
	
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public float getTamaño() {
		return tamaño;
	}
	public void setTamaño(float tamaño) {
		this.tamaño = tamaño;
	}
	public int getUnidad() {
		return unidad;
	}
	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCantPosicion() {
		return cantPosicion;
	}
	public void setCantPosicion(int cantPosicion) {
		this.cantPosicion = cantPosicion;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public LoteEntity getLote() {
		return lote;
	}
	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}
	public int getCantAComprar() {
		return cantAComprar;
	}
	public void setCantAComprar(int cantAComprar) {
		this.cantAComprar = cantAComprar;
	}
	public List<UbicacionEntity> getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(List<UbicacionEntity> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
	
	
}
