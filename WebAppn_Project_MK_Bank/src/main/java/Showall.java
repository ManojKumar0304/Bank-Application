
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Showall
 */
@WebServlet("/Showall")
public class Showall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Showall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 String NAME=request.getParameter("N");
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghdb","ghdb");
			Statement ps=con.createStatement();
			 ResultSet rs=ps.executeQuery("select * from bank");
			 ResultSetMetaData rsmd=rs.getMetaData();
			 out.print("<html><body bgcolor=skyblue><table border='1'>");
			 int n=rsmd.getColumnCount();
			 for(int i=1;i<=n;i++) {
				 out.println("<td><font color=blue size=3>"+"<br>"+rsmd.getColumnName(i));
			 }
			 out.println("<tr>");

			 while(rs.next()) {
				 for(int i=1;i<=n;i++) {
					 out.print("<td><br>"+rs.getString(i));
					  
				 }
				 out.println("</tr>");
			 }
			 out.print("</table></body></html>");
			 con.close();
			 
		 }
		 catch (Exception e) {
			 out.print(e);
		}

	}

}
