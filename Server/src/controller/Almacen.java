package controller;

import java.util.ArrayList;
import java.util.List;

import business.DetallePedido;
import business.MovimientoStock;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
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

	public void crearReserva(Pedido p, DetallePedido dp, int cantidad) {
		// TODO Auto-generated method stub
		
	}

	public OrdenPedido buscarOPConDisponibilidad(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void crearOrdenPedidoSinReservas() {
		// TODO Auto-generated method stub
		
	}

	public void crearOrdenPedido(Pedido p, DetallePedido dp, int i) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
