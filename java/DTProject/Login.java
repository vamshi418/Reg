package DTProject;

import java.io.*;
	import java.sql.*;
	import javax.servlet.*;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.*;

	//@WebServlet("/LoginDemo")
	public class Login extends HttpServlet {
		private static final long serialVersionUID = 1L;
	    
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 	
		{
		try
		{
		PrintWriter out=response.getWriter();
		response.setContentType("text/htm");
		String un=	request.getParameter("uname");
		String pw=	request.getParameter("pword");
		

		Class.forName("org.h2.Driver");
		//Establish the connection object
		Connection con=DriverManager.getConnection("jdbc:h2:~/test","sa","");
		//Execute the query
		String qry="select * from REGISTRATION";
		PreparedStatement pst=con.prepareStatement(qry);
		
		RequestDispatcher disp=null;
		ResultSet rs=pst.executeQuery();
		//Session creation 
		HttpSession session=request.getSession();
		System.out.println("session id:"+session.getId());
		System.out.println("Session Creation time:"+new Date(session.getCreationTime()));
		
		System.out.println("session object create");
		while(rs.next())
		{
			System.out.println("while loop start");
		/*	String u=rs.getString("uname");
			String p=rs.getString("pword");*/
			
			session.setAttribute("u", rs.getString("UNAME"));
			session.setAttribute("p", rs.getString("PWORD"));
			
			   String u1=(String)session.getAttribute("u");
			   System.out.println(u1);
			  
	            String p1=(String)session.getAttribute("p");
	            System.out.println(p1);
			
			if(un.equals(u1)&&pw.equals(p1))
			{

				
				 disp=request.getRequestDispatcher("Home.jsp");
				 disp.forward(request, response);
				
			}	
			else
			{
			out.print("credentials are invalid:");
			disp=request.getRequestDispatcher("login.html");
			disp.include(request, response);
			}
		}
	}
		
	catch(Exception e)
		
	{
		System.out.println(e);
	}

	}
	}



