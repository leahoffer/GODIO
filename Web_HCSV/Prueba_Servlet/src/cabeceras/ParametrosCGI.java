package cabeceras;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParametrosCGI
 */
public class ParametrosCGI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParametrosCGI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String[][] variables =
	      { { "AUTH_TYPE", request.getAuthType() },
	        { "CONTENT_LENGTH", String.valueOf(request.getContentLength()) },
	        { "CONTENT_TYPE", request.getContentType() },
	        { "DOCUMENT_ROOT", getServletContext().getRealPath("/") },
	        { "PATH_INFO", request.getPathInfo() },
	        { "PATH_TRANSLATED", request.getPathTranslated() },
	        { "QUERY_STRING", request.getQueryString() },
	        { "REMOTE_ADDR", request.getRemoteAddr() },
	        { "REMOTE_HOST", request.getRemoteHost() },
	        { "REMOTE_USER", request.getRemoteUser() },
	        { "REQUEST_METHOD", request.getMethod() },
	        { "SCRIPT_NAME", request.getServletPath() },
	        { "SERVER_NAME", request.getServerName() },
	        { "SERVER_PORT", String.valueOf(request.getServerPort()) },
	        { "SERVER_PROTOCOL", request.getProtocol() },
	        { "SERVER_SOFTWARE", getServletContext().getServerInfo() }
	      };
	    String title = "Servlet que Muestra las Variables CGI";
	    out.println("DOCTYPE \n" + "<HTML>\n" +
            		"<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
	                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                "<TR BGCOLOR=\"#FFAD00\">\n" +
	                "<TH>CGI Variable Name<TH>Value");
	    for(int i=0; i<variables.length; i++) {
	      String varName = variables[i][0];
	      String varValue = variables[i][1];
	      if (varValue == null)
	        varValue = "<I>No Especificada</I>";
	      out.println("<TR><TD>" + varName + "<TD>" + varValue);
	    }
	    out.println("</TABLE></BODY></HTML>");
	  }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
