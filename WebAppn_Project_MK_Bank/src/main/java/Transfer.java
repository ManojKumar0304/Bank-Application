import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		long TARGETACCOUNTNUMBER=Long.parseLong(request.getParameter("TAN"));
		double AMOUNT=Double.parseDouble(request.getParameter("AM"));
		//double AMOUNT = Double.parseDouble(request.getParameter("Am"));

		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("<h1>DRIVER LOADED SUCESSFULLY </h1><br>");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghdb","ghdb");
			PreparedStatement ps=con.prepareStatement("select status from bank where ACCOUNTNUMBER=? and NAME=? and STATUS=1 ");
			ps.setInt(1, ACCOUNTNUMBER);
			ps.setString(2, NAME);
//			ps.setInt(1, ACCOUNTNUMBER);
//			ps.setString(2, NAME);

			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				PreparedStatement ps1=con.prepareStatement("update bank set AMOUNT=AMOUNT-? where ACCOUNTNUMBER=? and NAME=? and password=?");
				ps1.setDouble(1, AMOUNT);
				ps1.setInt(2, ACCOUNTNUMBER);
				ps1.setString(3, NAME);
				ps1.setString(4, PASSWORD);
				int n=ps1.executeUpdate();
				//PreparedStatement ps2 = con.prepareStatement("select AMOUNT from bank ACCOUNTNUMBER=? and NAME=? and password=?");
//PreparedStatement ps2 = con.prepareStatement("select AMOUNT from bank where ACCOUNTNUMBER=? and NAME=? and password=?");

				
				PreparedStatement ps2=con.prepareStatement("select AMOUNT from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
			//PreparedStatement ps2 = con.prepareStatement("SELECT AMOUNT FROM bank WHERE ACCOUNTNUMBER=? AND NAME=? AND PASSWORD=?");
	
				ps2.setInt(1, ACCOUNTNUMBER);
				ps2.setString(2,NAME);
				ps2.setString(3, PASSWORD);
				ResultSet rs1=ps2.executeQuery();
				ResultSetMetaData rsmd=rs1.getMetaData();
				int o=rsmd.getColumnCount();
				for(int i=1;i<=o;i++)
					out.print("<tr>");
				while(rs1.next())
				{
					for(int i=1;i<=o;i++)
						out.println("<td><br><h3>MY ACCOUNT BALANCE IS :<h3> "+rs1.getString(i)+"<br>");
					out.println("<tr>");
				}
				out.print("target amount incress with : "+AMOUNT+"<br>");
				PreparedStatement ps3=con.prepareStatement("select AMOUNT from BANK where ACCOUNTNUMBER=?");
				ps3.setInt(1, ACCOUNTNUMBER);
				
				ResultSet rs2=ps3.executeQuery();
				ResultSetMetaData rsmd1=rs2.getMetaData();
				
				int m=rsmd1.getColumnCount();
				for(int i=1;i<=m;i++)
					
					while(rs2.next()) 
				{
				for( i=1;i<=m;i++)
					out.print("<tr><br>FINALLY TARGET AMOUNT IS : "+rs.getString(i)+"<br>");
					out.print("<tr>");
				}	
			}

			else 
			{
				out.print("your account is inactive..."+NAME);
			}
			
			con.close();
		}
		catch (Exception e) {
			out.print(e);
		}
	}

}

