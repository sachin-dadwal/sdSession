package com.jsp.bank.session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet1
 */
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	PreparedStatement pstmt=null;
	public void init()
	{
		System.out.println("I am in initialization phase.....");
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database1","root","tiger");
			pstmt=con.prepareStatement("select * from pnb_accounts WHERE userid=? AND password=?");
		}
		catch (ClassNotFoundException e) 
		{
		
			e.printStackTrace();
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
	}

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession(false);
		PrintWriter pw=response.getWriter();
		System.out.println("i am in service phase......");
		response.setContentType("text/html");
		if(session!=null)
  		{
			
			
			String UserName=(String) session.getAttribute("username");
  			System.out.println(UserName);
  			String Password=(String) session.getAttribute("password");
  			System.out.println(Password);
		
		
		try {
			pstmt.setString(1, UserName);
			pstmt.setString(2, Password);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("i am here...");
			if(rs.next())
			{
				
				
				
				pw.println("This is login");
				int accNo=rs.getInt(1);
  				double accBal=rs.getInt(2);
  				
  				
  				
  				
				pw.println("<br><h1><a href=  \"LogoutServlet\">Log-out</a></h1>");
				pw.println("This is india");
				pw.println("<html>");
	    			pw.println("<body bgcolor='Yellow'>");
	    			pw.println("<h1>");
	    			pw.println("<h1> welcome user</h1><br>");
	    			pw.println("<h2>your account balance:"+accBal);
	    			pw.println(" <a href=\"	ViewServlet\">User-Details </a><br>");
	    			pw.println(" <a href=\"	Depositservlet\">Deposit-Amount </a><br>");
	    			 pw.println(" <a href=\"withdrawservlet\">Withdraw-Amount </a><br>");
	    			   pw.println(" <a href=\"transferservlet\">Transfer-Amount </a><br>");
	    			   pw.println(" <a href=\"LogoutServlet\">log-out </a>");
	    			   pw.println("</h1>");
								
			
			}
			else
			{
				
				
				RequestDispatcher rd1=request.getRequestDispatcher("login.html");
				rd1.include(request, response);
				pw.flush();
				//pw.close();
			}
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	}
		public void destroy()
		{
		 System.out.println("Destroying phase destroying the resourses...");	
		
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(pstmt!=null)
		{
			try
			{
				pstmt.close();
			}
			catch (SQLException e) 
			{
							e.printStackTrace();
			}
		}
		
		}

}


	
