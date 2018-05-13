package dto;

import java.io.Serializable;
import java.util.List;

import business.Lote;
import business.Ubicacion;
import enumeration.EstadoProducto;
import enumeration.Presentacion;

public class ProductoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 839603259572067959L;
	private String codBarras;
	private String marca;
	private String descripcion;
	private EstadoProducto estado;
	private float tamaño;
	private int unidad;
	private float precio;
	private int cantPosicion;
	private Presentacion presentacion;
	private Lote lote;
	private int cantAComprar;
	private List<Ubicacion> ubicaciones;
	
	
	public ProductoDTO()
	{
		
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


	public EstadoProducto getEstado() {
		return estado;
	}


	public void setEstado(EstadoProducto estado) {
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


	public Presentacion getPresentacion() {
		return presentacion;
	}


	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}


	public Lote getLote() {
		return lote;
	}


	public void setLote(Lote lote) {
		this.lote = lote;
	}


	public int getCantAComprar() {
		return cantAComprar;
	}


	public void setCantAComprar(int cantAComprar) {
		this.cantAComprar = cantAComprar;
	}


	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}


	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
	
}
