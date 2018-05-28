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
import dto.MovimientoCCDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.UbicacionDTO;
import exception.ClienteException;
import exception.ProductoException;

@SuppressWarnings("unused")
public class test {

	public static void main(String[] args) throws RemoteException, ProductoException {
		// TODO Auto-generated method stub

	    
	    
		System.out.println("Presione 0 para Test de RMI");
		System.out.println("Presione 1 para Ingresar Cliente");
		System.out.println("Presione 2 para Traer un Producto");
		System.out.println("Presione 3 para Traer Productos");
		System.out.println("Presione 4 para Modificar Cliente de Cuit1");
		System.out.println("Presione 5 para Crear un pedido");
		System.out.println("Presione 6 para Agregar un movimiento de stock del producto CodBarra1");
		System.out.println("Presione 7 para Autorizar Pedido");
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
			cliente.getCuentaCorriente().setLimite(10000);
			cliente.getCuentaCorriente().setSaldo(0);
			cliente.setCondicionEsp("Condicion Especial");
			
			
		
			
			
			try {
				BusinessDelegate.getInstance().crearCliente(cliente);
				System.out.println("Cliente Creado Con Éxito!");
			} catch (ClienteException e) {
				// TODO Auto-generated catch block
				System.out.println(	e.getMessage());
			}
			
		}
        
        if (entradaTeclado.equals("2"))
        { 
        	ProductoDTO p= BusinessDelegate.getInstance().mostrarProducto("CodBarra1");
        	System.out.println(p.getCodBarras()+" "+ p.getDescripcion());
        }
        
	
		  if (entradaTeclado.equals("3"))
	        {
	        	
	        	List<ProductoDTO> productos= BusinessDelegate.getInstance().listarProductosDisponibles();
	        	
	        	for (ProductoDTO p: productos)
	        	{
	        		System.out.println(p.getCodBarras()+" "+p.getMarca()+" "+p.getDescripcion()+" "+p.getEstado());
	        	}
	        }
		  if (entradaTeclado.equals("4"))
		  { 
			  try {
				ClienteDTO c= BusinessDelegate.getInstance().traerCliente("Cuit1");
				System.out.println(c.getCuit()+" "+c.getDireccion()+" "+c.getRazon_social()+" "+c.getTelefono());
				System.out.print("Modificando...");
				c.setDireccion("Direccion Modificada");
				c.setTelefono("Telefono Modificado");
				BusinessDelegate.getInstance().modificarCliente(c);
			} catch (ClienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  
		  }
		
			if (entradaTeclado.equals("5"))
			{
				ClienteDTO cliente=new ClienteDTO();
				cliente.setCuit("Cuit1");
				
				PedidoDTO pedido=new PedidoDTO();
				pedido.setNroPedido(1);
				pedido.setAclaracionEspecial("Aclaracion Especial");
				pedido.setCliente(cliente);
				pedido.setDespachable(false);
				pedido.setDir_entrega("Dir Entrega");
				pedido.setFecha(new Date());
				pedido.setMotivoEstado("Motivo Estado");
				pedido.setAclaracionEspecial("Aclaración Especial");
				pedido.setTotal_bruto(0);
				
				ProductoDTO producto=new ProductoDTO();
				producto.setCodBarras("CodBarra1");
				/*ProductoDTO producto2=new ProductoDTO();
				producto2.setCodBarras("CodBarra2");
				ProductoDTO producto3=new ProductoDTO();
				producto3.setCodBarras("CodBarra3");
				ProductoDTO producto4=new ProductoDTO();
				producto4.setCodBarras("CodBarra4");*/

				
				DetallePedidoDTO detalle= new DetallePedidoDTO();
				detalle.setCantidad(15);
				detalle.setSubtotal(0);
				detalle.setProducto(producto);
				
				/*DetallePedidoDTO detalle2= new DetallePedidoDTO();
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
				detalle4.setProducto(producto4);*/
				
				List<DetallePedidoDTO> detalles = new ArrayList<DetallePedidoDTO>();
				detalles.add(detalle);
				/*detalles.add(detalle2);
				detalles.add(detalle3);
				detalles.add(detalle4);*/
				
				pedido.setDetalle(detalles);
				
				try {
					BusinessDelegate.getInstance().crearPedido(pedido);
					
				} catch (ClienteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			if (entradaTeclado.equals("6"))
			{
				UbicacionDTO u = new UbicacionDTO();
				u.setBloque(1);
				u.setCalle("A");
				u.setEstante(1);
				u.setEstanteria(1);
				u.setPosicion(1);
				BusinessDelegate.getInstance().agregarAjusteStock("CodBarra1", "AjustePos", u, "Motivo Movimiento", 8, "Responsable");
			
			}
			if (entradaTeclado.equals("7"))
			{
				System.out.println(BusinessDelegate.getInstance().validarCreditoCliente(7));
				String entrada = "";
		        Scanner Escaner = new Scanner (System.in); 
		        entrada = entradaEscaner.nextLine ();
		        if (entrada.equals("SI"))
		        	{
		        		BusinessDelegate.getInstance().autorizarPedido(7);
		        		System.out.print("Pedido Autorizado!");
		        	}
		        else
		        	{
		        		System.out.print("Pedido no Autorizado");
		        	}
			}
			
	}

	@Override
	public boolean equals(Object arg0) {
		
		return super.equals(arg0);
	}

}
