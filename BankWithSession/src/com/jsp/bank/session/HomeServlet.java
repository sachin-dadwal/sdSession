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

/**
 * Servlet implementation class Servlet1
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		HttpSession session=null;
		pw.println("<h1>Welcome to the homepage</h1>");
		
		pw.println("<br><h1><a href=  \"LogoutServlet\">Log-out</a></h1>");
		pw.println("<br><a href=  \"Servlet3\">Account manipulation</a>");
		pw.println("<html");
		pw.println("<body bgcolor='red'>");
		pw.println("<h1> welcome user </h1>");
		pw.println(" <a href=\"ViewServlet\">View Details </a><br>");
	pw.println(" <a href=\"	BalanceServlet\">Balance-Enquiry </a><br>");
	pw.println(" <a href=\"	Depositservlet\">Deposit-Amount </a><br>");
	 pw.println(" <a href=\"withdrawservlet\">Withdraw-Amount </a><br>");
	   pw.println(" <a href=\"transferservlet\">Transfer-Amount </a><br>");
		RequestDispatcher rd1=request.getRequestDispatcher("Home.html");
		rd1.include(request, response);
		pw.flush();
	}

}
