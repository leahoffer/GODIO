package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Almacen;
import controller.Compras;
import dao.AlmacenDAO;
import dao.ComprasDAO;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;

@SuppressWarnings("unused")
public class OrdenPedido {

	private int nro;
	private EstadoOP estado;
	private Pedido pedidoOrigen;
	private Producto producto;
	private int cantidadPedida;
	private List<Proveedor> ultimosTresProv;
	private List <MovimientoReserva> movReserva;
	
	public OrdenPedido() {
		this.ultimosTresProv = new ArrayList<Proveedor>();
		this.movReserva = new ArrayList<MovimientoReserva>();
	}

	public OrdenPedido(Pedido p, Producto pr, int i) {
		this.estado = EstadoOP.Pendiente;
		this.pedidoOrigen = p;
		this.producto = pr;
		this.cantidadPedida = calcularCantidadAPedir(i, pr);
		this.ultimosTresProv = new ArrayList<Proveedor>();
		this.movReserva = new ArrayList<MovimientoReserva>();
	}
	
	
	private int calcularCantidadAPedir(int i, Producto producto) {
		int cantidad = 0;
		while (cantidad < i)
		{
			cantidad = cantidad + producto.getCantAComprar();
		}
		return cantidad;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public EstadoOP getEstado() {
		return estado;
	}

	public void setEstado(EstadoOP estado) {
		this.estado = estado;
	}

	public Pedido getPedidoOrigen() {
		return pedidoOrigen;
	}

	public void setPedidoOrigen(Pedido pedidoOrigen) {
		this.pedidoOrigen = pedidoOrigen;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public List<Proveedor> getUltimosTresProv() {
		return ultimosTresProv;
	}

	public void setUltimosTresProv(List<Proveedor> ultimosTresProv) {
		this.ultimosTresProv = ultimosTresProv;
	}

	public List<MovimientoReserva> getMovReserva() {
		return movReserva;
	}

	public void setMovReserva(List<MovimientoReserva> movReserva) {
		this.movReserva = movReserva;
	}

	public boolean calcularDisponible(int i) {
		int reservas = 0;
		for (MovimientoReserva mr : this.getMovReserva())
		{
			reservas = reservas + mr.getCantidad();
		}
		if (i >= (this.getCantidadPedida()-reservas))
			return false;
		return true;
	}

	public void agregarMovimientoReserva(int i, Pedido p) {
		MovimientoReserva mr = new MovimientoReserva();
		mr.setCantidad(i);
		mr.setCompleta(false);
		mr.setFecha(new Date());
		mr.setPedido(p);
		this.movReserva.add(mr);
		updateMe();
		
	}

	public void updateMe() {
		Compras.getInstance().updateOP(this);
		
	}

	public int disponible() {
		int reservas = 0;
		for (MovimientoReserva mr : this.getMovReserva())
		{
			reservas = reservas + mr.getCantidad();
		}
		return this.getCantidadPedida()-reservas;
	}

	public void createMe() {
		Compras.getInstance().createOP(this);		
	}

	public void cerrar() {
		this.estado = EstadoOP.Completa;
		this.updateMe();
		Producto p=this.getProducto();
		int cantidad = this.getCantidadPedida();
		
		if (p.getUbicaciones().isEmpty())
		{
			Ubicacion u= Almacen.getInstance().traerPrimeraUbicacionVacia();
			if (cantidad<p.getCantPosicion())
			{ 
				u.setCantidadActual(cantidad);
				p.getUbicaciones().add(u);
				p.updateMe();
			}
			else
			{ 
				u.setCantidadActual(p.getCantPosicion());
				int cantaux = cantidad - p.getCantPosicion();
				p.getUbicaciones().add(u);
				p.updateMe();
				
				while (cantaux>=p.getCantPosicion())
				{ 
					Ubicacion uaux =Almacen.getInstance().traerPrimeraUbicacionVacia();
					uaux.setCantidadActual(p.getCantPosicion());
					cantaux = cantaux - p.getCantPosicion();
					p.getUbicaciones().add(uaux);
					p.updateMe();
				}
				if (cantaux>0)
				{ 	Ubicacion uaux =Almacen.getInstance().traerPrimeraUbicacionVacia();
					uaux.setCantidadActual(p.getCantPosicion());
					u.setCantidadActual(cantaux);
					p.getUbicaciones().add(uaux);
					p.updateMe();
				}

				
			}
			
		}
		else
		{ 
			List<Ubicacion> ubicacionesProd = p.getUbicaciones();
			int cantaux2 = cantidad;
			for (Ubicacion ub: ubicacionesProd)
			{ 
				if (ub.getCantidadActual()<p.getCantPosicion())
				{
					if ((cantaux2+ub.getCantidadActual())>p.getCantPosicion())
					{
						cantaux2=cantaux2-ub.getCantidadActual();
						ub.setCantidadActual(p.getCantPosicion());
						p.getUbicaciones().add(ub);
						p.updateMe();
						
					}
				}
			}
			Ubicacion u= Almacen.getInstance().traerPrimeraUbicacionVacia();
			if (cantaux2<p.getCantPosicion())
			{ 
				u.setCantidadActual(cantidad);
				p.getUbicaciones().add(u);
				p.updateMe();
			}
			else
			{ 
				u.setCantidadActual(p.getCantPosicion());
				cantaux2 = cantaux2 - p.getCantPosicion();
				p.getUbicaciones().add(u);
				p.updateMe();
				
				while (cantaux2>=p.getCantPosicion())
				{ 
					Ubicacion uaux =Almacen.getInstance().traerPrimeraUbicacionVacia();
					uaux.setCantidadActual(p.getCantPosicion());
					cantaux2 = cantaux2 - p.getCantPosicion();
					p.getUbicaciones().add(uaux);
					p.updateMe();
				}
				if (cantaux2>0)
				{ 	Ubicacion uaux =Almacen.getInstance().traerPrimeraUbicacionVacia();
					uaux.setCantidadActual(cantaux2);
					p.getUbicaciones().add(uaux);
					p.updateMe();
				}

				
			}
		}
		
		
		
		if (!this.movReserva.isEmpty())
		{
			for (MovimientoReserva mr : this.movReserva)
			{
				Reserva r = Almacen.getInstance().convertirMovimientoReserva(mr, this.producto);
				r.createMe();
				mr.setCompleta(true);
				revalidarPedido(mr.getPedido());
			}
		}
		this.updateMe();
		
		
	}
	
	
	private void revalidarPedido(Pedido pedido) {
		boolean completoONo = true;
		List<OrdenPedido> ordenesPendientes = ComprasDAO.getInstance().buscarOPSPendientesOReservadas();
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
	
	
	
	
}
