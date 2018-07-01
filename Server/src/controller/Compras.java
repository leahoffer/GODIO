package controller;

import java.util.List;

import business.DetallePedido;
import business.OrdenPedido;
import business.Pedido;
import business.Producto;
import dao.ComprasDAO;
import dto.OrdenPedidoDTO;

public class Compras {

	private static Compras instance;
	
	public static Compras getInstance() {
		if (instance == null)
			instance = new Compras();
		return instance;
	}
	
	private Compras() {
	}
	
	
	//Publicado
	public void cerrarOP(int nroOP){
		OrdenPedido op = ComprasDAO.getInstance().findOPByNro(nroOP);
		op.cerrar();		
	}


	public OrdenPedido buscarOPConDisponibilidad(Producto p) {
		OrdenPedido op = ComprasDAO.getInstance().buscarOPConDisponibilidad(p);
		return op;
	}
	
	

	public void crearOrdenPedido(Pedido p, DetallePedido dp, int i) {
		OrdenPedido op = new OrdenPedido(p, dp.getProducto(), i);
		op.createMe();
	}
	
	
	
	public void updateOP(OrdenPedido op) {
		ComprasDAO.getInstance().updateOP(op);
		
	}

	public void createOP(OrdenPedido op) {
		ComprasDAO.getInstance().createOP(op);
		
	}

	public List<OrdenPedidoDTO> traerOrdenesPendientes() {
		// TODO Auto-generated method stub
		return ComprasDAO.getInstance().mostrarOrdenesPendientes();
	}
}
