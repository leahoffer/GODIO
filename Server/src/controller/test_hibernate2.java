package controller;

import java.util.Date;

import dao.AlmacenDAO;
import dao.ProductoDAO;
import entity.UbicacionEntity;
import entity.UbicacionId;
import enumeration.EstadoPedido;

@SuppressWarnings("unused")
public class test_hibernate2 {

	public static void main(String[] args) {
		/*Cliente c = new Cliente();
		CuentaCorriente cc = new CuentaCorriente();
		cc.setLimite(1000);
		c.setCuentaCorriente(cc);
		c.setCondicionEsp("Hijo del dueño");
		c.setCuit("Cuit1");
		c.setDireccion("Calle Falsa 123");
		c.setR_inscripto(true);
		c.setRazon_social("Godio SRL");
		c.setTelefono("123456789");
		c.saveOrUpdate();
		
		Producto p1 = ProductoDAO.getInstance().findById("CodBarra1");
		Producto p2 = ProductoDAO.getInstance().findById("CodBarra2");
		
		DetallePedido dp1 = new DetallePedido();
		dp1.setCantidad(500);
		dp1.setProducto(p1);
		dp1.setSubtotal(dp1.calcularSubTotal());
		
		DetallePedido dp2 = new DetallePedido();
		dp2.setCantidad(2000);
		dp2.setProducto(p2);
		dp2.setSubtotal(dp2.calcularSubTotal());
		
		Pedido p = new Pedido();
		p.setAclaracionEspecial("Nada");
		p.setCliente(c);
		p.setDespachable(false);
		p.setDir_entrega(c.getDireccion());
		p.setEstado(EstadoPedido.PendienteAutorizacion);
		p.setFecha(new Date());
		p.setFecha_despacho(p.getFecha());
		p.setMotivoEstado("Pendiente autorización");
		p.setTotal_bruto(p.calcularTotal());
		p.getDetalle().add(dp1);
		p.getDetalle().add(dp2);
		p.createMe();*/
		
		/*String calle;
		int bloque = 1;
		int estanteria = 1;
		int estante = 1;
		int posicion = 1;
		int cantidadActual =1;
		
		/*
		 * 1 hasta 2 -> A
		 * 2 hasta 3 -> B
		 * 3 hasta 4 -> C
		 * 4 hasta 5 -> D
		 * 5 hasta 6 -> E
		 * 6 hasta 7 -> F
		 * 7 hasta 8 -> G
		 * */
		/*for (bloque = 7 ; bloque <= 8; bloque++)
		{
			for (estanteria = 1; estanteria <6; estanteria++)
			{
				for (estante = 1; estante < 7; estante++)
				{
					for (posicion = 1; posicion < 22; posicion++)
					{
						UbicacionEntity ue = new UbicacionEntity();
						UbicacionId uid = new UbicacionId();
						uid.setBloque(bloque);
						uid.setCalle("G");
						uid.setCantidadActual(0);
						uid.setEstante(estante);
						uid.setEstanteria(estanteria);
						uid.setPosicion(posicion);
						ue.setIdUbicacion(uid);
						AlmacenDAO.getInstance().crearUbicacion(ue);
					}
				}
			}
		}*/
		
		
	}
}

