package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection con;
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String usuario = request.getParameter("usuario");
		 String password =  request.getParameter("passwd");
		 ArrayList<String> clubes = new  ArrayList<String>();
			try {
				
				Class.forName("net.sourceforge.jtds.jdbc.Driver");
				String connectionUrl = "jdbc:jtds:sqlserver://192.168.6.202:1433;database=jugadores;";
				con = DriverManager.getConnection(connectionUrl,usuario,password);
				System.out.println(con.getCatalog());
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery("Select * from clubes");
					while(rs.next())
					{
						clubes.add(rs.getString("Nombre"));
					}
			} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}

		 PrintWriter out = response.getWriter();
		 String title = "Listo Clubes";
		 
		 out.println("DOCTYPE \n" + "<HTML>\n" +
	                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                "<BODY>\n" +
	                "<H1>" + title + "</H1>\n" + 
	                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
	                "<TR BGCOLOR=\"#FFAD00\">\n" +
	                "<TD>Nombre Club</TD></TR>");
		 Iterator<String> i = clubes.iterator();
		 while(i.hasNext()) {
		 	 out.println("<TR><TD>" + i.next() + "</TD></TR>");
		 }
		 out.println("</TABLE></BODY></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
