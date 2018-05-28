package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.DetallePedido;
import business.MovimientoStock;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
import business.Reserva;
import business.Ubicacion;
import dao.AlmacenDAO;
import enumeration.EstadoOP;
import enumeration.TipoMovimientoStock;
@SuppressWarnings("unused")
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

	public void createReserva(Pedido p, DetallePedido dp, int cantidad) {
		Reserva r = new Reserva();
		r.setCantidad(cantidad);
		r.setCompleta(false);
		r.setFecha(new Date());
		r.setPedido(p);
		r.setProducto(dp.getProducto());
		r.createMe();
	}

	public OrdenPedido buscarOPConDisponibilidad(Producto p) {
		OrdenPedido op = AlmacenDAO.getInstance().buscarOPConDisponibilidad(p);
		return op;
	}


	public void crearOrdenPedido(Pedido p, DetallePedido dp, int i) {
		OrdenPedido op = new OrdenPedido();
		op.setEstado(EstadoOP.Pendiente);
		op.setPedidoOrigen(p);
		op.setProducto(dp.getProducto());
		op.setCantidadPedida(calcularCantidadAPedir(i, dp.getProducto()));
		op.createMe();
	}

	private int calcularCantidadAPedir(int i, Producto producto) {
		int cantidad = 0;
		while (cantidad < i)
		{
			cantidad = cantidad + producto.getCantAComprar();
		}
		return cantidad;
	}

	public int devolverStockProducto(Producto producto) {
		List<MovimientoStock> movimientos = AlmacenDAO.getInstance().movimientosStockProducto(producto);
		List<Reserva> reservas = AlmacenDAO.getInstance().reservasProducto(producto);
		int cantidadStock = 0;
		for (MovimientoStock ms : movimientos)
		{
			if (ms.getMotivo().toString().equals(TipoMovimientoStock.Compra.toString()) || ms.getMotivo().toString().equals(TipoMovimientoStock.AjustePos.toString()))
			{
				cantidadStock = cantidadStock + ms.getCantidad();
			}
			else
			{
				cantidadStock = cantidadStock - ms.getCantidad();
			}
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

	public void updateOP(OrdenPedido op) {
		AlmacenDAO.getInstance().updateOP(op);
		
	}

	public void createOP(OrdenPedido op) {
		AlmacenDAO.getInstance().createOP(op);
		
	}

	/*public Ubicacion traerUbicacion(String calle, int bloque, int estanteria, int estante, int posicion) {
		for (Ubicacion u : this.ubicaciones)
		{
			if (u.getCalle().equals(calle) && u.getBloque()==bloque && u.getEstanteria()==estanteria && u.getEstante()==estante && u.getPosicion()==posicion)
				return u;
			
		}
		Ubicacion u = new Ubicacion();
		u.setCalle(calle);
		u.setBloque(bloque);
		u.setEstanteria(estanteria);
		u.setEstante(estante);
		u.setPosicion(posicion);
		Ubicacion resultado = AlmacenDAO.getInstance().traerUbicacion(u);
		return resultado;
	}*/

	public List<Ubicacion> buscarUbicacionesParaDespachar(Pedido p) {
		List<Ubicacion> us = new ArrayList<Ubicacion>();
		for (DetallePedido dp : p.getDetalle())
		{
			//Obtengo todas las ubicaciones que tiene el producto
			List<Ubicacion> ubicaciones = dp.getProducto().getUbicaciones();
			int aux = 0;
			//Por cada ubicación encontrada arriba
			for (Ubicacion u : ubicaciones)
			{
				//Mientras no haya encontrado el total que me pide el DetallePedido...
				while (aux < dp.getCantidad())
				{
					//Si la Ubicacion que estoy analizando tiene el total de lo que me falta sacar, saco todo (si me queda en 0 desasocio) y aumento el aux en la cantidad sacada
					if(u.getCantidadActual() >= dp.getCantidad()-aux)
					{
						int cantidadPosicion = u.getCantidadActual();
						//Primer ronda, aux=0. Rondas subsiguientes, aux va a ser lo que saqué de otras ubicaciones...
						int cantidadASacar = (dp.getCantidad()-aux);
						//Actualizo la cantidadActual, restando lo que necesito sacar
						u.setCantidadActual(cantidadPosicion-cantidadASacar);
						if(u.getCantidadActual()==0)
						{
							dp.getProducto().sacarUbicacion(u);
						}
						u.update();
						us.add(u);
						aux = aux + cantidadASacar;
					}
					//Si la Ubicacion que estoy analizando no tiene el total de lo que me falta sacar, saco todo lo que hay en la ubicación, desasocio y aumento el aux en el total de lo que tenía la posición
					else
					{
						us.add(u);
						aux = aux + u.getCantidadActual();
						u.setCantidadActual(0);
						u.update();
						dp.getProducto().sacarUbicacion(u);
					}
				}
			}
			MovimientoStock ms = new MovimientoStock();
			ms.setCantidad(dp.getCantidad());
			ms.setMotivo("Venta");
			ms.setTipo(TipoMovimientoStock.Venta);
			ms.setProducto(dp.getProducto());
			ms.setResponsable("N/A");
			ms.save();
		}
		return us;
	}

	public void saveMocimientoStock(MovimientoStock movimientoStock) {
		AlmacenDAO.getInstance().saveMovimientoStock(movimientoStock);
	}

	
	
	
}
