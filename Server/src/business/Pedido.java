package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.Almacen;
import controller.Compras;
import dao.PedidoDAO;
import dto.BonificacionDTO;
import dto.CondicionDTO;
import dto.DescuentoDTO;
import dto.DetallePedidoDTO;
import dto.FacturaDTO;
import dto.PedidoDTO;
import enumeration.EstadoOP;
import enumeration.EstadoPedido;
import enumeration.TipoFactura;

public class Pedido {

	private int nroPedido;
	private Cliente cliente;
	private Factura factura;
	private String dir_entrega;
	private Date fecha;
	private Date fecha_despacho;
	private float total_bruto;
	private boolean despachable;
	private EstadoPedido estado;
	private String motivoEstado;
	private List<DetallePedido> detalle;
	private String aclaracionEspecial;
	private List<Condicion> condicionesAplicadas;
	
	public Pedido() {
		//Polémico. Después vemos si simplemente creamos vacíos y rellenamos o si asignamos con objetos creados
		this.cliente = new Cliente();
		this.factura = new Factura();
		this.detalle = new ArrayList<DetallePedido>();
		this.condicionesAplicadas = new ArrayList<Condicion>();
	}
	
	

	public int getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public String getDir_entrega() {
		return dir_entrega;
	}

	public void setDir_entrega(String dir_entrega) {
		this.dir_entrega = dir_entrega;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha_despacho() {
		return fecha_despacho;
	}

	public void setFecha_despacho(Date fecha_despacho) {
		this.fecha_despacho = fecha_despacho;
	}

	public float getTotal_bruto() {
		return total_bruto;
	}

	public void setTotal_bruto(float total_bruto) {
		this.total_bruto = total_bruto;
	}

	public boolean isDespachable() {
		return despachable;
	}

	public void setDespachable(boolean despachable) {
		this.despachable = despachable;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public String getMotivoEstado() {
		return motivoEstado;
	}

	public void setMotivoEstado(String motivoEstado) {
		this.motivoEstado = motivoEstado;
	}

	public List<DetallePedido> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetallePedido> detalle) {
		this.detalle = detalle;
	}

	public String getAclaracionEspecial() {
		return aclaracionEspecial;
	}

	public void setAclaracionEspecial(String aclaracionEspecial) {
		this.aclaracionEspecial = aclaracionEspecial;
	}

	public List<Condicion> getCondicionesAplicadas() {
		return condicionesAplicadas;
	}

	public void setCondicionesAplicadas(List<Condicion> condicionesAplicadas) {
		this.condicionesAplicadas = condicionesAplicadas;
	}

	public void update() {
		PedidoDAO.getInstance().update(this);
	}
	
	public void calcularTotal() {
		float resultado = 0;
		for (DetallePedido dp : this.getDetalle())
		{
			resultado = resultado+dp.getSubtotal();
		}
		this.total_bruto= resultado;
	}
	


	public void saveOrUpdate() {
		PedidoDAO.getInstance().createOrUpdate(this);
	}

	public PedidoDTO toDTO() {
		PedidoDTO pdto = new PedidoDTO();
		List<DetallePedidoDTO> dpdtos = new ArrayList<DetallePedidoDTO>();
		List<CondicionDTO> cdtos = new ArrayList<CondicionDTO>();
		pdto.setAclaracionEspecial(this.aclaracionEspecial);
		pdto.setCliente(this.cliente.toDTO());
		pdto.setDespachable(this.despachable);
		pdto.setDir_entrega(this.dir_entrega);
		pdto.setEstado(this.estado.toString());
		pdto.setFecha(this.fecha);
		pdto.setFecha_despacho(this.fecha_despacho);
		pdto.setMotivoEstado(this.motivoEstado);
		pdto.setNroPedido(this.nroPedido);
		pdto.setTotal_bruto(this.total_bruto);
		for (DetallePedido dp : this.detalle)
		{
			dpdtos.add(dp.toDTO());
		}
		for (Condicion c : this.condicionesAplicadas)
		{
			if (c instanceof Bonificacion)
			{
				BonificacionDTO bdto = new BonificacionDTO();
				bdto.setCondicion(c.getCondicion());
				bdto.setMonto(((Bonificacion) c).getMonto());
				cdtos.add(bdto);
			}
			else if (c instanceof Descuento)
			{
				DescuentoDTO ddto = new DescuentoDTO();
				ddto.setCondicion(c.getCondicion());
				ddto.setPorcentaje(((Descuento) c).getPorcentaje());
				cdtos.add(ddto);
			}
		}
		pdto.setDetalle(dpdtos);
		pdto.setCondicionesAplicadas(cdtos);
		if (this.factura != null)
		{
			FacturaDTO fdto = this.factura.toDTO();
			pdto.setFactura(fdto);
		}
		return pdto;
	}

	public boolean validarCompletarPedido() 
	{
		boolean resultado = true;
		for (DetallePedido dp : this.getDetalle())
		{
			int sd = dp.getProducto().devolverStockProducto();
			//Si tengo Stock disponible, reservo y listo. Almacén se encarga de updatear el stock y eso.
			if (sd>dp.getCantidad())
			{
				Almacen.getInstance().createReserva(this, dp, dp.getCantidad());
			}
			//Sino...
			else
			{
				//Si no puedo completar, pero el stock disponible es mayor a 0, primero reservo lo que queda y después creo OP nueva
				if (sd > 0)
				{
					Almacen.getInstance().createReserva(this, dp, sd);
					OrdenPedido op = Compras.getInstance().buscarOPConDisponibilidad(dp.getProducto());
					//Todo esto mientras haya una OP con disponibilidad. Sino voy a tener que hacer una nueva de 0 y reservarle el 100%
					if (op != null)
					{
						//Si tiene disponible el total de lo que falta, que lo reserve de ahí.
						if (op.calcularDisponible(dp.getCantidad()-sd))
						{
							op.agregarMovimientoReserva(dp.getCantidad()-sd, this);
						}
						//Sino, reservo de esa OP lo que le quede y le digo al Almacén que cree una nueva por el restante
						else
						{
							int reservadoOP = op.disponible();
							op.agregarMovimientoReserva(reservadoOP, this);
							op.setEstado(EstadoOP.Reservada);
							op.updateMe();
							Compras.getInstance().crearOrdenPedido(this, dp, dp.getCantidad()-sd-reservadoOP);
							op = Compras.getInstance().buscarOPConDisponibilidad(dp.getProducto());
							op.agregarMovimientoReserva(dp.getCantidad()-sd-reservadoOP, this);
						}
					}
					//Tengo stock, aunque no suficiente, pero no tenog OPs con disponibilidad para reservar
					else 
					{
						//Creo que esto está bien... que el crearOrdenPedido no genere el movimientoReserva.
						Compras.getInstance().crearOrdenPedido(this, dp, dp.getCantidad()-sd);
						op = Compras.getInstance().buscarOPConDisponibilidad(dp.getProducto());
						op.agregarMovimientoReserva(dp.getCantidad()-sd, this);
					}
				}
				//Si no puedo completar, y aparte no hay NADA de stock, voy directamente a ver si tengo para reservarle a una OP
				else if (sd == 0)
				{
					OrdenPedido op = Compras.getInstance().buscarOPConDisponibilidad(dp.getProducto());
					//Si hay una OP con disponibilidad...
					if (op != null)
					{
						//Si la OP tiene disponible para reservarle el total, de lujo
						if (op.calcularDisponible(dp.getCantidad()))
						{
							op.agregarMovimientoReserva(dp.getCantidad(), this);
						}
						//Sino, le reservo lo que le quede y aparte creo una nueva
						else
						{
							int reservadoOP = op.disponible();
							op.agregarMovimientoReserva(reservadoOP,this);
							op.setEstado(EstadoOP.Reservada);
							op.updateMe();
							Compras.getInstance().crearOrdenPedido(this, dp, dp.getCantidad()-reservadoOP);
							op = Compras.getInstance().buscarOPConDisponibilidad(dp.getProducto());
							op.agregarMovimientoReserva(dp.getCantidad()-sd-reservadoOP, this);
						}
					}
					//No solo no tengo nada de stock sino que no tengo OP con disponibilidad. Caso más horrible.
					else
					{
						Compras.getInstance().crearOrdenPedido(this, dp, dp.getCantidad()-sd);
						op = Compras.getInstance().buscarOPConDisponibilidad(dp.getProducto());
						op.agregarMovimientoReserva(dp.getCantidad(), this);
					}
				}
				resultado = false;
			}
		}
		return resultado;
	}



	



	public List<Ubicacion> despachar() {
		//buscarUbicacionesParaDespachar se va a encargar de crear los movimientos y de actualizar las ubicaciones que encuentre y devuelva
		List<Ubicacion> us = Almacen.getInstance().buscarUbicacionesParaDespachar(this);
		this.estado = EstadoPedido.Despachado;
		this.factura = new Factura(this);
		this.generarRemito();
		this.cliente.facturarPedido(this);
		this.update();
		return us;
	}



	private void generarRemito() {
		Remito r = new Remito();
		List<ItemRemito> irs = new ArrayList<ItemRemito>();
		r.setCliente(this.getCliente());
		r.setDespachado(true);
		for(DetallePedido dp : this.detalle)
		{
			ItemRemito ir = new ItemRemito();
			ir.setCantidad(dp.getCantidad());
			ir.setProducto(dp.getProducto());
			irs.add(ir);
		}
		r.setItems(irs);
		r.save();		
	}
			
	
	
	
	
}