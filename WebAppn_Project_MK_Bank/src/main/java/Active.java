

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Active
 */
@WebServlet("/Active")
public class Active extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Active() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html");
			
			PrintWriter out=response.getWriter();
			int ACCOUNTNUMBER=Integer.parseInt(request.getParameter("AN"));
			String NAME=request.getParameter("N");
			String PASSWORD=request.getParameter("PS");
			int STATUS=1;
			String INFORMATION_2="ACTIVATION";
			

try 
{
    Class.forName("oracle.jdbc.driver.OracleDriver");
    out.print("<h1>DRIVER LOADED SUCCESSFULLY </h1><br>");
    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghdb","ghdb");
    PreparedStatement ps = con.prepareStatement("UPDATE bank SET STATUS = ?, INFORMATION_2 = ? WHERE ACCOUNTNUMBER = ? AND NAME = ? AND PASSWORD = ?");
    
    ps.setInt(1, STATUS);
    ps.setString(2, INFORMATION_2);
    ps.setInt(3, ACCOUNTNUMBER);
    ps.setString(4, NAME);
    ps.setString(5, PASSWORD);
    
    int i = ps.executeUpdate();
    out.print(i + "<h1>WELCOME</h1>" + NAME + " ==> " + "<h1>YOUR ACCOUNT HAS BEEN ACTIVATED</h1>");
    out.close();
}
catch (Exception e) {
    out.print(e);
}
}
}
