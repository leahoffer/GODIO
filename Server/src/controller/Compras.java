package controller;

import java.util.ArrayList;
import java.util.List;

import business.DetallePedido;
import business.MovimientoReserva;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
import business.Proveedor;
import business.Reserva;
import dao.AlmacenDAO;
import dao.ComprasDAO;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;
@SuppressWarnings("unused")
public class Compras {

	private List<OrdenPedido> ordenesPedido;
	private List<Proveedor> proveedores;
	private static Compras instance;
	
	public static Compras getInstance() {
		if (instance == null)
			instance = new Compras();
		return instance;
	}
	
	private Compras() {
		this.ordenesPedido = new ArrayList<OrdenPedido>();
		this.proveedores = new ArrayList<Proveedor>();
	}
	
	public void cerrarOP(int nroOP){
		OrdenPedido op = AlmacenDAO.getInstance().findOPByNro(nroOP);
		op.setEstado(EstadoOP.Completa);
		op.updateMe();
		if (!op.getMovReserva().isEmpty())
		{
			for (MovimientoReserva mr : op.getMovReserva())
			{
				Reserva r = Almacen.getInstance().convertirMovimientoReserva(mr, op.getProducto());
				r.createMe();
				mr.setCompleta(true);
				revalidarPedido(mr.getPedido());
			}
		}
		
	}
	
	private void revalidarPedido(Pedido pedido) {
		boolean completoONo = true;
		List<OrdenPedido> ordenesPendientes = AlmacenDAO.getInstance().buscarOPSPendientesOReservadas();
		if(!ordenesPendientes.isEmpty())
		{
			for (OrdenPedido op : ordenesPendientes)
			{
				for (MovimientoReserva mr : op.getMovReserva())
				{
					if (mr.getPedido().getNroPedido() == pedido.getNroPedido())
						completoONo = false;
				}
			}
		}
		if (completoONo)
		{
			pedido.setEstado(EstadoPedido.PendienteDespacho);
			pedido.update();
		}
	}

	public OrdenPedido buscarOPConDisponibilidad(Producto p) {
		OrdenPedido op = ComprasDAO.getInstance().buscarOPConDisponibilidad(p);
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
	
	
	public void updateOP(OrdenPedido op) {
		ComprasDAO.getInstance().updateOP(op);
		
	}

	public void createOP(OrdenPedido op) {
		ComprasDAO.getInstance().createOP(op);
		
	}
}
