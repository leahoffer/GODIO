package business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.AlmacenDAO;
import dao.ProductoDAO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import enumeration.EstadoProducto;
import enumeration.Presentacion;

public class Producto{


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
	
	public Producto() {
		this.lote = new Lote();
		this.ubicaciones = new ArrayList<Ubicacion>();
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


	//PRIMERA VEZ QUE USO ITERATOR TENGO MIEDO
	public void sacarUbicacion(Ubicacion u) {
		Iterator<Ubicacion> iter = this.ubicaciones.iterator();
		while(iter.hasNext())
		{
			Ubicacion ub = iter.next();
			if (u.getCalle().equals(ub.getCalle()) && u.getBloque()==ub.getBloque() && u.getEstante()==ub.getEstante() && u.getEstanteria()==ub.getEstanteria() && u.getPosicion()==ub.getPosicion())
				iter.remove();
		}
		//VALIDAR BIEN. El update que está abajo llama al createOrUpdate. Actualizará la List de ubicaciones? :(
		this.updateMe();
	}

	public void updateMe() {
		ProductoDAO.getInstance().createOrUpdate(this);
		
	}

	public ProductoDTO toDTO() {
		ProductoDTO prdto = new ProductoDTO();
		List<UbicacionDTO> udtos = new ArrayList<UbicacionDTO>();
		prdto.setCantAComprar(this.cantAComprar);
		prdto.setCantPosicion(this.cantPosicion);
		prdto.setCodBarras(this.codBarras);
		prdto.setDescripcion(this.descripcion);
		prdto.setEstado(this.estado.toString());
		prdto.setLote(this.lote.toDTO());
		prdto.setMarca(this.marca);
		prdto.setPrecio(this.precio);
		prdto.setPresentacion(this.presentacion.toString());
		prdto.setTamaño(this.tamaño);
		prdto.setUnidad(this.unidad);
		for(Ubicacion u : this.ubicaciones)
		{
			udtos.add(u.toDTO());
		}
		prdto.setUbicaciones(udtos);
		return prdto;
	}
	
	public int devolverStockProducto() {
		List<Reserva> reservas = AlmacenDAO.getInstance().reservasProducto(this);
		int cantidadStock = 0;
		for (Ubicacion u : this.ubicaciones)
		{
			cantidadStock=cantidadStock+u.getCantidadActual();
		}
		for (Reserva r : reservas)
		{
			if (!r.isCompleta())
			{
				cantidadStock = cantidadStock - r.getCantidad();
			}
		}
		return cantidadStock;
	}
	
}
