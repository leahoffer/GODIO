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
		
		pedido.setAclaracionEspecial(req.getParameter("aclaraciones"));
		pedido.setCliente(cliente);
		pedido.setDespachable(false);
		pedido.setDir_entrega(req.getParameter("direccion"));
		pedido.setFecha(new Date());
		pedido.setMotivoEstado("Un motivo cualca");
		pedido.setTotal_bruto(0);
		
		ProductoDTO producto=new ProductoDTO();
		producto.setCodBarras(req.getParameter("producto"));

		
		DetallePedidoDTO detalle= new DetallePedidoDTO();
		detalle.setCantidad(Integer.parseInt(req.getParameter("cantidad")));
		detalle.setSubtotal(0);
		detalle.setProducto(producto);
	

		
		List<DetallePedidoDTO> detalles = new ArrayList<DetallePedidoDTO>();
		detalles.add(detalle);
		
		pedido.setDetalle(detalles);
		
		try {
			BusinessDelegate.getInstance().crearPedido(pedido);
		} catch (ClienteException | ProductoException e) {
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
