package DTProject;

	import java.io.*;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.*;
	import java.sql.*;

	public class Registration extends HttpServlet {

		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			try {
				System.out.println("hi");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				String fn = request.getParameter("fname");
				String ln = request.getParameter("lname");
				String un = request.getParameter("uname");
				String pw = request.getParameter("pword");
				// Load the driver
				System.out.println("database");
				Class.forName("org.h2.Driver");
				System.out.println("hi");
				// Establish the connection object
				Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

				if (con != null) {
					System.out.println("Connection: " + con);
					// Execute the query
					
					  String qry = "insert into REGISTRATION values(?,?,?,?)";
					  PreparedStatement pst = con.prepareStatement(qry);
					  
					  
					  pst.setString(1, fn); 
					  pst.setString(2, ln); 
					  pst.setString(3, un); 
					  pst.setString(4, pw);
					  
					  int i = pst.executeUpdate(); 
					  if (i > 0) 
					  {
					  
						  response.sendRedirect("index.jsp"); 
					  } 
					  else 
					  {
					  
						  out.println("Transaction failed"); 
					  }
					 

					/*Statement s = con.createStatement();

					s.execute("select * from resgistration");*/

				} else {
					System.out.println("no Connection" + con);
				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
