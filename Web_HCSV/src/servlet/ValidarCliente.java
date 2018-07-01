package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessdelegate.BusinessDelegate;
import dto.PedidoDTO;

public class ValidarCliente extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2199042081906182759L;

	public ValidarCliente() {
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

			PedidoDTO pedido = BusinessDelegate.getInstance().validarCreditoCliente(Integer.parseInt(req.getParameter("nro")));
			req.getSession().setAttribute("pedido", pedido);
			RequestDispatcher view = req.getRequestDispatcher("detallepedido.jsp");
			view.forward(req, resp);
		
	}
}
