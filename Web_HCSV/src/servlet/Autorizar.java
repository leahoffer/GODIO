package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessdelegate.BusinessDelegate;
import dto.PedidoDTO;

public class Autorizar extends HttpServlet {

	public Autorizar() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			PedidoDTO p=(PedidoDTO) req.getSession().getAttribute("pedido");
			BusinessDelegate.getInstance().autorizarPedido(p.getNroPedido());
		
	}
}
