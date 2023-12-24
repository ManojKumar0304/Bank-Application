import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Balance
 */
@WebServlet("/Balance")
public class Balance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Balance() {
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
			PreparedStatement ps=con.prepareStatement("select * from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
//			PreparedStatement ps=con.prepareStatement("select STATUS from bank where ACCOUNTNUMBER=? and PASSWORD=? and STATUS=? ");
//			ps.setInt(1, ACCOUNTNUMBER);
//			ps.setString(2, PASSWORD);
//			ResultSet rs1=ps.executeQuery();
////			if(rs1.next())
//			{
//				PreparedStatement ps1 = con.prepareStatement("select ACCOUNTNUMBER, NAME, AMOUNT, ADDRESS from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
//
//				//PreparedStatement ps1=con.prepareStatement("select ACCOUNTNUMBER,NAME,AMOUNT,ADDRESS from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
				ps.setInt(1, ACCOUNTNUMBER);
				ps.setString(2, NAME);
				ps.setString(3, PASSWORD);
				ResultSet rs=ps.executeQuery();
				ResultSetMetaData rsmd=rs.getMetaData();
//				double AMOUNT=0.00;
//				if(rs.next())
//				{
//					AMOUNT=rs.getDouble(4);
//				}
//				out.print("current balance is ="+AMOUNT);
//				ResultSetMetaData rsmd=rs.getMetaData();
				out.print("<h2>your account balance is :</h2> "+"<p></p>");
				out.print("<html><body bgcolor='yellow'><table border ='5'>");
				int n=rsmd.getColumnCount();
				for(int i=1;i<=n;i++)
				
					out.print("<td><font color=red size=3>"+rsmd.getColumnName(i));
					out.print("<tr>");
					while (rs.next()) 
					{
						for(int i=1;i<=n;i++)
						{
							out.print("<td><br>"+rs.getString(i));
						}
						out.print("</tr>");
						
					}
					out.print("</table></body></html>");
				
				con.close();
			
			
//			else {
//				out.print("your account inactive...."+NAME);
//			}
//			con.close();
		}
		catch (Exception e) {
			out.print(e);
		}
		
		
	}

}
//PreparedStatement ps1 = con.prepareStatement("select ACCOUNTNUMBER,NAME,AMOUNT,ADDRESS from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
//PreparedStatement ps1 = con.prepareStatement("select ACCOUNTNUMBER,NAME,AMOUNT,ADDRESS from bank where ACCOUNTNUMBER=? and NAME=?,PASSWORD=?");
//PreparedStatement ps1 = con.prepareStatement("select ACCOUNTNUMBER, NAME, AMOUNT, ADDRESS from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
//PreparedStatement ps1 = con.prepareStatement("select ACCOUNTNUMBER,NAME,AMOUNT,ADDRESS from bank where ACCOUNTNUMBER=? and NAME=? and PASSWORD=?");
