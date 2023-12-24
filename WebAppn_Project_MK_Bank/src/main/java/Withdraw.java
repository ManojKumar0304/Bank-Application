import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
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
		double AMOUNT=Double.parseDouble(request.getParameter("AM"));
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("<h1>DRIVER LOADED SUCESSFULLY </h1><br>");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghdb","ghdb");
			PreparedStatement ps=con.prepareStatement("select STATUS from bank where ACCOUNTNUMBER=? and PASSWORD=? and STATUS=1");
			
		ps.setInt(1, ACCOUNTNUMBER);
			ps.setString(2, PASSWORD);
			
			ResultSet rs=ps.executeQuery();
			if (rs.next()) 
			{
				PreparedStatement ps1=con.prepareStatement("select AMOUNT from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
				ps1.setInt(1, ACCOUNTNUMBER);
				ps1.setString(2, NAME);
				ps1.setString(3, PASSWORD);
				ResultSet rs2=ps1.executeQuery();
				ResultSetMetaData rsmd=rs2.getMetaData();
	
				int n=rsmd.getColumnCount();
				for(int i=1;i<=n;i++)
					out.println("<tr>");
				while (rs2.next()) 
				{
					for(int i=1;i<=n;i++) {
						out.println("<td><br> YOUR ORGINAL AMMOUNT : "+rs2.getString(i)+"<br>");}
					out.print("<tr>");
					
				}
				PreparedStatement ps2=con.prepareStatement("update bank set AMOUNT=AMOUNT-? where ACCOUNTNUMBER=? and PASSWORD=?");
				ps2.setDouble(1,AMOUNT);
				ps2.setInt(2, ACCOUNTNUMBER);
				ps2.setString(3, PASSWORD);
				
				int o=ps2.executeUpdate();
				out.print(" decrease withdraw amount = "+AMOUNT+"<br>");
				
				PreparedStatement ps3=con.prepareStatement("select AMOUNT from bank where ACCOUNTNUMBER=? and PASSWORD=?");
				ps3.setInt(1, ACCOUNTNUMBER);
				ps3.setString(2, PASSWORD);
				ResultSet rs3=ps3.executeQuery();
				ResultSetMetaData rsmd2=rs3.getMetaData();
				int p=rsmd2.getColumnCount();
				for(int i=1;i<=p;i++)
					out.print("<tr>");
				while(rs3.next())
				{
					for(int i=1;i<=n;i++)
						out.print("<td><br> MY TOTAL AMOUNT IS "+rs3.getString(i));
					out.print("<tr>");
				}
				}
			else
			{
				out.print(" your account Inactive"+NAME);
			}
			con.close();
			}
		catch (Exception e) 
		{
			out.print(e);
		}
		
	}

}
