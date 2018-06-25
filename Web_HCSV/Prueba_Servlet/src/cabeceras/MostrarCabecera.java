package cabeceras;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MostrarCabecera
 */
public class MostrarCabecera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarCabecera() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Leer Cabeceras de Solicitud desde Servlets";
	    out.println("DOCTYPE \n" + "<HTML>\n" +
                	"<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
	                "<B>Metodo     : </B>" +
	                request.getMethod() + "<BR>\n" +
	                "<B>URI        : </B>" +
	                request.getRequestURI() + "<BR>\n" +
	                "<B>Protocolo  : </B>" +
	                request.getProtocol() + "<BR><BR>\n" +
	                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                "<TR BGCOLOR=\"#FFAD00\">\n" +
	                "<TH>Nombre Encabezado<TH>Valor de Encabezado");
	    Enumeration<String> headerNames = request.getHeaderNames();
	    while(headerNames.hasMoreElements()) {
	      String headerName = headerNames.nextElement();
	      out.println("<TR><TD>" + headerName);
	      out.println("    <TD>" + request.getHeader(headerName));
	    }
	    out.println("</TABLE>\n</BODY></HTML>");
	  }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
