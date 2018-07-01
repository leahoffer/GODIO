package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessdelegate.BusinessDelegate;
import dto.ClienteDTO;
import dto.DetallePedidoDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import exception.ClienteException;
import exception.ProductoException;

public class CrearPedido extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4956363269171493305L;

	public CrearPedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ClienteDTO cliente=new ClienteDTO();
		cliente.setCuit(req.getParameter("cuit"));
		
		PedidoDTO pedido=new PedidoDTO();
		
		pedido.setAclaracionEspecial(req.getParameter("aclaracion"));
		pedido.setCliente(cliente);
		pedido.setDespachable(false);
		pedido.setDir_entrega(req.getParameter("direccion"));
		pedido.setFecha(new Date());
		pedido.setMotivoEstado("");
		pedido.setTotal_bruto(0);
		
		try {
			List<ProductoDTO> totalproductos = BusinessDelegate.getInstance().listarProductosDisponibles();
			List<DetallePedidoDTO> detalles = new ArrayList<DetallePedidoDTO>();
			for (ProductoDTO pdto: totalproductos)
			{
				String id = "cantidad"+pdto.getCodBarras();
				String cantidad = ""+req.getParameter(id);
				if (!cantidad.equals(""))
				{
					ProductoDTO producto=new ProductoDTO();
					producto.setCodBarras(pdto.getCodBarras());

					DetallePedidoDTO detalle= new DetallePedidoDTO();
					detalle.setCantidad(Integer.parseInt(cantidad));
					detalle.setSubtotal(0);
					detalle.setProducto(producto);
					
					detalles.add(detalle);
				}
				
				
			}
			pedido.setDetalle(detalles);
			BusinessDelegate.getInstance().crearPedido(pedido);
		} catch (ProductoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher view = req.getRequestDispatcher("pedidocreado.jsp");
		view.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
