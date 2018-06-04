package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.DetallePedido;
import business.MovimientoReserva;
import business.MovimientoStock;
import business.Pedido;
import business.Producto;
import business.Reserva;
import business.Ubicacion;
import dao.AlmacenDAO;
import dao.ProductoDAO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import enumeration.TipoMovimientoStock;
import exception.ProductoException;

public class Almacen {

	private static Almacen instance;
	private List<MovimientoStock> movimientos;
	private List<Ubicacion> ubicaciones;
	public static Almacen getInstance() {
		if (instance == null)
			instance = new Almacen();
		return instance;
	}
	
	
	
	private Almacen() {
		this.ubicaciones = new ArrayList<Ubicacion>();
		new ArrayList<Reserva>();
		this.movimientos = new ArrayList<MovimientoStock>();
	}

	
	
	public void createReserva(Pedido p, DetallePedido dp, int cantidad) {
		Reserva r = new Reserva(dp.getProducto(), cantidad, p, false, new Date());
		r.createMe();
	}


	

	public Ubicacion traerUbicacion(String calle, int bloque, int estanteria, int estante, int posicion) {
		for (Ubicacion u : this.ubicaciones)
		{
			if (u.getCalle().equals(calle) && u.getBloque()==bloque && u.getEstanteria()==estanteria && u.getEstante()==estante && u.getPosicion()==posicion)
				return u;	
		}
		Ubicacion u = new Ubicacion(calle, bloque, estanteria, estante, posicion);
		Ubicacion resultado = AlmacenDAO.getInstance().traerUbicacion(u);
		return resultado;
	}
	
	
	
	public List<UbicacionDTO> mostrarUbicaciones() {
		List<Ubicacion> ubicaciones = traerUbicaciones();
		List<UbicacionDTO> ubicacionesdto = new ArrayList<UbicacionDTO>();
		for (Ubicacion u:ubicaciones)
			ubicacionesdto.add(u.toDTO());
		return ubicacionesdto;
	}

	
	
	private List<Ubicacion> traerUbicaciones() {
		return AlmacenDAO.getInstance().traerTodasLasUbicaciones();
	}

	
	
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


	/*public void agregarMovimientoStock(String codbarra, String tipoajuste, String motivo, int cantidad,
			String responsable) {
		MovimientoStock ms= new MovimientoStock();
		Producto p = Controller.getInstance().buscarProducto(codbarra);
		ms.setCantidad(cantidad);
		ms.setMotivo(motivo);
		ms.setProducto(p);
		ms.setResponsable(responsable);
		ms.setTipo(TipoMovimientoStock.valueOf(tipoajuste));
		this.movimientos.add(ms);
		ms.save();
		if (ms.getTipo().toString().equalsIgnoreCase("AjustePos"))
		{
			if (p.getUbicaciones().isEmpty())
			{
				Ubicacion u= this.traerPrimeraUbicacionVacia();
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
						Ubicacion uaux =this.traerPrimeraUbicacionVacia();
						uaux.setCantidadActual(p.getCantPosicion());
						cantaux = cantaux - p.getCantPosicion();
						p.getUbicaciones().add(uaux);
						p.updateMe();
					}
					if (cantaux>0)
					{ 	Ubicacion uaux =this.traerPrimeraUbicacionVacia();
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
				Ubicacion u= this.traerPrimeraUbicacionVacia();
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
						Ubicacion uaux =this.traerPrimeraUbicacionVacia();
						uaux.setCantidadActual(p.getCantPosicion());
						cantaux2 = cantaux2 - p.getCantPosicion();
						p.getUbicaciones().add(uaux);
						p.updateMe();
					}
					if (cantaux2>0)
					{ 	Ubicacion uaux =this.traerPrimeraUbicacionVacia();
						uaux.setCantidadActual(cantaux2);
						p.getUbicaciones().add(uaux);
						p.updateMe();
					}

					
				}
			}
		}
		else
		{ 
			
		}
	}

*/
	
	
	
	
	public Ubicacion traerPrimeraUbicacionVacia() {
		return AlmacenDAO.getInstance().traerPrimeraUbicacionVacia();
	}

	
	
	public void agregarAjusteStock(String producto, String tipo, UbicacionDTO udto, String motivo, int cantidad, String responsable) {
		Ubicacion u=new Ubicacion(udto.getCalle(), udto.getBloque(), udto.getEstanteria(), udto.getEstante(), udto.getPosicion());		
		MovimientoStock ms= new MovimientoStock();
		Producto p = ProductoDAO.getInstance().findById(producto);
		
			for (Ubicacion ub:p.getUbicaciones())
			{
				if (u.soyLaUbicacion(ub))
				{ 
					if (tipo.equalsIgnoreCase("AjustePos"))
					{
						ms.setCantidad(cantidad-ub.getCantidadActual());
					}
					else
					{
						ms.setCantidad(ub.getCantidadActual()-cantidad);
					}
					
					ms.setMotivo(motivo);
					ms.setProducto(p);
					ms.setResponsable(responsable);
					ms.setTipo(TipoMovimientoStock.valueOf(tipo));
					this.movimientos.add(ms);
					ms.save();
					
					ub.setCantidadActual(cantidad);
					p.updateMe();
				}
			}
			if (p.getUbicaciones().isEmpty())
			{
				if (tipo.equalsIgnoreCase("AjustePos"))
				{
					ms.setCantidad(cantidad);
					ms.setMotivo(motivo);
					ms.setProducto(p);
					ms.setResponsable(responsable);
					ms.setTipo(TipoMovimientoStock.valueOf(tipo));
					this.movimientos.add(ms);
					ms.save();
					u.setCantidadActual(cantidad);
					p.getUbicaciones().add(u);
					p.updateMe();
				}
			}
		
	
		
	}

	
	
	public Reserva convertirMovimientoReserva(MovimientoReserva mr, Producto p) {
		Reserva r = new Reserva(p, mr.getCantidad(), mr.getPedido(), false, mr.getFecha());
		return r;
	}

	
	
	public Producto giveMeAProduct(String codbarras) {
		return ProductoDAO.getInstance().findById(codbarras);
		
	}

	

	public ProductoDTO mostrarProducto(String codbarras) {
		Producto p = Almacen.getInstance().giveMeAProduct(codbarras);
		return p.toDTO();
	}

	
	
	public List<ProductoDTO> listarProductos() throws ProductoException
	{
		List<Producto> ps = ProductoDAO.getInstance().findAll();
		List <ProductoDTO> prodsvo = new ArrayList<ProductoDTO>();
		for (Producto p : ps) 
			prodsvo.add(p.toDTO());		
		return prodsvo;
	}

	
	
	
}
