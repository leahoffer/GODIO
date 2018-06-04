package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.DetallePedidoDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import exception.ClienteException;
import exception.ProductoException;

@SuppressWarnings("unused")
public class test_hibernate {

	public static void main(String[] args) throws RemoteException, ClienteException, ProductoException {

/*		ClienteDTO cliente=new ClienteDTO();
		cliente.setCuentaCorriente(new CuentaCorrienteDTO());
		cliente.setCuit("Cuit1");
		cliente.setDireccion("Direccion");
		cliente.setR_inscripto(true);
		cliente.setRazon_social("Razon Social");
		cliente.setTelefono("Telefono");
		cliente.getCuentaCorriente().setId("001");
		cliente.getCuentaCorriente().setLimite(10000);
		cliente.getCuentaCorriente().setSaldo(0);
		cliente.setCondicionEsp("Condicion Especial");
		List<CondicionDTO> condiciones = new ArrayList<CondicionDTO>();
		CondicionDTO condi = new CondicionDTO();
		CondicionDTO condi2 = new CondicionDTO();
		condi.setCondicion("Condicion 1");
		condiciones.add(condi);
		condi2.setCondicion("Condicion 2");
		condiciones.add(condi2);
		
	
		cliente.getCuentaCorriente().setCondiciones(condiciones);;
		
		try {
			Controller.getInstance().crearCliente(cliente);
		} catch (ClienteException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Cliente Creado Con Éxito!");*/
		
		/*ClienteDTO cliente=new ClienteDTO();
		cliente.setCuit("Cuit2");
		
		PedidoDTO pedido=new PedidoDTO();
		pedido.setAclaracionEspecial("Aclaracion Especial");
		pedido.setCliente(cliente);
		pedido.setDespachable(false);
		pedido.setDir_entrega("Dir Entrega");
		pedido.setEstado("Incompleto");
		pedido.setFecha(new Date());
		pedido.setFecha_despacho(new Date());
		pedido.setMotivoEstado("Motivo Estado");
		pedido.setTotal_bruto(0);
		
		ProductoDTO producto=new ProductoDTO();
		producto.setCodBarras("CodBarras1");
		ProductoDTO producto2=new ProductoDTO();
		producto2.setCodBarras("CodBarras2");
		ProductoDTO producto3=new ProductoDTO();
		producto3.setCodBarras("CodBarras3");
		ProductoDTO producto4=new ProductoDTO();
		producto4.setCodBarras("CodBarras4");

		
		DetallePedidoDTO detalle= new DetallePedidoDTO();
		detalle.setCantidad(100);
		detalle.setSubtotal(0);
		detalle.setProducto(producto);
		
		DetallePedidoDTO detalle2= new DetallePedidoDTO();
		detalle2.setCantidad(100);
		detalle2.setSubtotal(0);
		detalle2.setProducto(producto2);
		
		DetallePedidoDTO detalle3= new DetallePedidoDTO();
		detalle3.setCantidad(100);
		detalle3.setSubtotal(0);
		detalle3.setProducto(producto3);
		
		DetallePedidoDTO detalle4= new DetallePedidoDTO();
		detalle4.setCantidad(100);
		detalle4.setSubtotal(0);
		detalle4.setProducto(producto4);
		
		List<DetallePedidoDTO> detalles = new ArrayList<DetallePedidoDTO>();
		detalles.add(detalle);
		detalles.add(detalle2);
		detalles.add(detalle3);
		detalles.add(detalle4);
		
		//BusinessDelegate.getInstance().listarProductosDisponibles();
		//aca se elijen los items y la cantidad y se crean los detalles
		Controller.getInstance().crearPedido("Cuit1", detalles, "Albarellos", "Mercaderia");
*/
	}

}
