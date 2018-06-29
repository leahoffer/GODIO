package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessdelegate.BusinessDelegate;
import dto.PedidoDTO;

public class BuscarPedidosPendientes extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BuscarPedidosPendientes() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cuit = req.getParameter("cliente");
		List<PedidoDTO> pedidos = BusinessDelegate.getInstance().listarPedidosPendientes();
		 PrintWriter out = resp.getWriter();
		 String title = "Listo Clubes";
		 
		 out.println("DOCTYPE \n" + "<HTML>\n" +
	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY>\n" +
	                "<H1>" + title + "</H1>\n" + 
	                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                "<TR BGCOLOR=\"#FFAD00\">\n" +
	                "<TD>Nombre Club</TD></TR>");
		for (PedidoDTO p: pedidos)
		{
		
			out.println("<TR><TD>" + p.getNroPedido() + " - " + p.getDir_entrega() + "</TD></TR>");
			 
		}
		 out.println("</TABLE></BODY></HTML>");
	}
}
