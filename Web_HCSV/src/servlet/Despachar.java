package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessdelegate.BusinessDelegate;
import dto.PedidoDTO;
import dto.UbicacionDTO;

public class Despachar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6309898797894814391L;

	public Despachar() {
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
			
			List<UbicacionDTO> ubicaciones = BusinessDelegate.getInstance().despacharPedido(Integer.parseInt(req.getParameter("nroPedido")));
			req.getSession().setAttribute("ubicaciones", ubicaciones);
			RequestDispatcher view = req.getRequestDispatcher("ubicacionesdespacho.jsp");
			view.forward(req, resp);
	}
}
