package controller;

import java.util.ArrayList;
import java.util.List;

import business.OrdenPedido;
import business.Proveedor;
@SuppressWarnings("unused")
public class Compras {

	private List<OrdenPedido> ordenesPedido;
	private List<Proveedor> proveedores;
	private static Compras instance;
	
	public Compras getInstance() {
		if (instance == null)
			instance = new Compras();
		return instance;
	}
	
	private Compras() {
		this.ordenesPedido = new ArrayList<OrdenPedido>();
		this.proveedores = new ArrayList<Proveedor>();
	}
	
	
	
}
