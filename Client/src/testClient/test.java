package testClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import businessdelegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.CuentaCorrienteDTO;
import dto.DetallePedidoDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import exception.ProductoException;

public class test {

	public static void main(String[] args) throws RemoteException, ProductoException {
		// TODO Auto-generated method stub

		
		System.out.println("Presione 0 para Test de RMI");
		System.out.println("Presione 1 para Ingresar Cliente");
		System.out.println("Presione 2 para Ingresar Pedido");
		System.out.println("Presione 3 para Traer Productos");
		String entradaTeclado = "";

        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner

        entradaTeclado = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
		
        if (entradaTeclado.equals("0"))
		{
			//Test para ver si funciona el BD y la conexión al servidor
			BusinessDelegate.getInstance().test();
			
		}
        if (entradaTeclado.equals("1"))
		{
			ClienteDTO cliente=new ClienteDTO();
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
			BusinessDelegate.getInstance().crearCliente(cliente);
			System.out.println("Cliente Creado Con Éxito!");
		}
		
        if (entradaTeclado.equals("3"))
        {
        	
        	List<ProductoDTO> productos= BusinessDelegate.getInstance().listarProductosDisponibles();
        	
        	for (ProductoDTO p: productos)
        	{
        		System.out.println(p.getCodBarras()+" "+p.getMarca()+" "+p.getDescripcion()+" "+p.getEstado());
        	}
        }
        
		if (entradaTeclado.equals("2"))
		{
			ClienteDTO cliente=new ClienteDTO();
			cliente.setCuit("Cuit1");
			
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
			
			
			BusinessDelegate.getInstance().crearPedido("20366543598", detalles);
			
			
			/*Empezar generacion de pedido
			List<ProductoDTO> prods = new ArrayList<ProductoDTO>();			
			prods = BusinessDelegate.getInstance().listarProductosDisponibles();
			
			for (ProductoDTO p : prods)
			{
				System.out.println("Producto:" + p.getDescripcion() + " Cantidad:" + p.getCantPosicion());
				System.out.println();
				
			}
			System.out.println("Se va a hacer un pedido con estos materiales y cantidad 5");
			*/
			
		}
		
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

}
