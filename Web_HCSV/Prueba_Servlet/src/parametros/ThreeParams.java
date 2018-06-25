package parametros;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreeParams
 */
public class ThreeParams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreeParams() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
		    response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String title = "Leer 3 parámetros";
		    out.println("DOCTYPE \n" + "<HTML>\n" +
		                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
		                "<BODY>\n" +
		                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
		                "<UL>\n" +
		                "  <LI>param1: "
		                + request.getParameter("param1") + "\n" +
		                "  <LI>param2: "
		                + request.getParameter("param2") + "\n" +
		                "  <LI>param3: "
		                + request.getParameter("param3") + "\n" +
		                "</UL>\n" +
		                "</BODY></HTML>");}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
