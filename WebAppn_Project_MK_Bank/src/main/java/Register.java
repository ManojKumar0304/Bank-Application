import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		String EMAIL=request.getParameter("EMAIL");
		String GENDER=request.getParameter("GENDER");
		long MOBILENUMBER=Long.parseLong(request.getParameter("MB"));
		String STATE=request.getParameter("STATE");
		String COUNTRY=request.getParameter("COUNTRY");
		String ADDRESS=request.getParameter("ADD");
		String INFORMATION=request.getParameter("INFORMATION");
		String INFORMATION_2=request.getParameter("INFORMATION_2");
		double AMOUNT=Double.parseDouble(request.getParameter("AM"));
		int STATUS=1;
		
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("<h1 style=\"color: orange;\">class load sucessfully....<h1><br> ");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghdb","ghdb");
			out.print("<h1 style=\"color: pink;\">connect created sucessfully.....<h1><br>");
			PreparedStatement ps = con.prepareStatement("INSERT INTO bank (accountnumber, name, password, email, gender, mobilenumber, state, country, address, information, information_2, amount, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

			//PreparedStatement ps=con.prepareStatement("insert into bank values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, ACCOUNTNUMBER);
			ps.setString(2, NAME);
			ps.setString(3, PASSWORD);
			ps.setString(4, EMAIL);
			ps.setString(5,GENDER);
			ps.setLong(6, MOBILENUMBER);
			ps.setString(7, STATE);
			ps.setString(8, COUNTRY);
			ps.setString(9, ADDRESS);
			ps.setString(10, INFORMATION);
			ps.setString(11, INFORMATION_2);
			ps.setDouble(12, AMOUNT);
			ps.setInt(13, STATUS);
			
			int i=ps.executeUpdate();
			out.print(i+"<h3 style=\"color: blue;\">data insert sucessfully....<h3>");
			con.close();
			
		} 
		catch (Exception e) 
		{								
			out.print(e);
		}
	}

}//PreparedStatement ps = con.prepareStatement("INSERT INTO bank (account_number, name, password, email, gender, mobile_number, state, country, address, information, information_2, amount, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

