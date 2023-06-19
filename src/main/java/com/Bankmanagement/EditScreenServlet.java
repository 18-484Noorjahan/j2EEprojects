package com.Bankmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet{
	private static final String query="select  bookname,bookedition,bookprice from bookdata where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/html");
		 int id= Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdata?useSSL=false","root", "root");
		    PreparedStatement pre = con.prepareStatement(query);
		    pre.setInt(1, id);
		   ResultSet set = pre.executeQuery();
		   set.next();
		   out.println("<form action='editurl?id"+id+"' method ='post'>");
		   out.println("<table align='center'>");
		   out.println("<tr>");
		   out.println("<td>Book Name</td>");
		   out.println("<td><input type= 'text'  name= 'bookname' value='"+set.getString(1)+"'></td>");
		   out.println("</tr>");
		   out.println("<tr>");
		   out.println("<td>Book Edition</td>");
		   out.println("<td><input type= 'text'  name= 'bookedition' value='"+set.getString(2)+"'></td>");
		   out.println("</tr>");
		   out.println("<tr>");
		   out.println("<td>Book Price</td>");
		   out.println("<td><input type= 'text'  name= 'bookprice' value='"+set.getString(3)+"'></td>");
		   out.println("</tr>");
		   out.println("<tr>");
		   out.println("<td><input type='submit' value ='Edit' </td>");
		   out.println("<td><input type='reset' value ='cancel' </td>");
		   out.println("</tr>");
		   out.println("</table>");
		   out.println("</form>");
		   
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		out.println("<a href='Home.html'>Home</a>");
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
		}


}
