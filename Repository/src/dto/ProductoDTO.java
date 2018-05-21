package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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
	private String estado;
	private float tamaño;
	private int unidad;
	private float precio;
	private int cantPosicion;
	private String presentacion;
	private LoteDTO lote;
	private int cantAComprar;
	private List<UbicacionDTO> ubicaciones;
	
	
	public ProductoDTO()
	{
		ubicaciones= new ArrayList<UbicacionDTO>();
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


	public LoteDTO getLote() {
		return lote;
	}


	public void setLote(LoteDTO lote) {
		this.lote = lote;
	}


	public int getCantAComprar() {
		return cantAComprar;
	}


	public void setCantAComprar(int cantAComprar) {
		this.cantAComprar = cantAComprar;
	}


	public List<UbicacionDTO> getUbicaciones() {
		return ubicaciones;
	}


	public void setUbicaciones(List<UbicacionDTO> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	
	
}
