package business;

import java.util.List;

import enumeration.EstadoProducto;
import enumeration.Presentacion;

public class Producto {

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
	
}
