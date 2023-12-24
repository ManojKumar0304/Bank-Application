import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Close
 */
@WebServlet("/Close")
public class Close extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Close() {
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
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("<h1>DRIVER LOADED SUCESSFULLY </h1><br>");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghdb","ghdb");
			PreparedStatement ps=con.prepareStatement("update bank set STATUS =0 ,INFORMATION_2='DE_ACTIVATION' where  ACCOUNTNUMBER=? and PASSWORD=?");
			//PreparedStatement ps = con.prepareStatement("UPDATE bank SET STATUS = 0, INFORMATION_2 = 'DE_ACTIVATION' WHERE ACCOUNTNUMBER = ? AND PASSWORD = ?");

			ps.setInt(1, ACCOUNTNUMBER);
			ps.setString(2, PASSWORD);
			 
		int n=ps.executeUpdate();
		if(n==1)
		{
			out.print("<H1>YOUR ACCOUNT HAS been CLOSED .... </H1>"+NAME);
		}
		else {
			out.print("<h1> IN ACTIVE YOUR ACCOUNT </h1>");
		}
		con.close();
		}
		catch (Exception e) 
		{
			out.print(e);
		}
			
	}

}
