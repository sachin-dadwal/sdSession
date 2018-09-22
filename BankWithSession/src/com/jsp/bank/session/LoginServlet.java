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
public class LoginServlet extends HttpServlet {
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

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		System.out.println("i am in service phase......");
		response.setContentType("text/html");
		
		String user=request.getParameter("username");
		String pass=request.getParameter("password");
		HttpSession session=null;
		try {
			pstmt.setString(1, user);
			pstmt.setString(2, pass);
			ResultSet rs=pstmt.executeQuery();
			
			System.out.println("i am here...");
			if(rs.next())
			{
				session=request.getSession(true);
				
				session.setAttribute("username", user);
				session.setAttribute("password",pass);
				pw.println("<br><h1><a href=  \"LogoutServlet\">Log-out</a></h1>");
				pw.println("This is india");
				//pw.println("<h1>Hello "+user+",Welcome to your homepage</h1>");
				
				RequestDispatcher rd=request.getRequestDispatcher("HomeServlet");
				pw.println("get the request and  forwording to servlet 3");
				rd.forward(request, response);
								
			
			}
			else
			{
				
				pw.println(""
						+ "<h2> <font color='red'>Invalid crdentials </font></h2>");
				//pw.println("<h1><font color='red'>invlid credentials</font></h1>");
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


	
