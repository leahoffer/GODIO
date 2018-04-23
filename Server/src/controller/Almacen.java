package controller;

import java.util.ArrayList;
import java.util.List;

import business.MovimientoStock;
import business.Reserva;
import business.Ubicacion;

public class Almacen {

	private static Almacen instance;
	private List<MovimientoStock> movimientos;
	private List<Ubicacion> ubicaciones;
	private List<Reserva> reservas;
	
	public static Almacen getInstance() {
		if (instance == null)
			instance = new Almacen();
		return instance;
	}
	
	private Almacen() {
		this.ubicaciones = new ArrayList<Ubicacion>();
		this.reservas = new ArrayList<Reserva>();
		this.movimientos = new ArrayList<MovimientoStock>();
	}
	
	
	
}
