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

@WebServlet("/booklist")
public class BookListServlet  extends HttpServlet{
	private static final String query="select id, bookname,bookedition,bookprice from bookdata";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/html");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdata?useSSL=false", "root", "root");
		    PreparedStatement pre = con.prepareStatement(query);
		   ResultSet set = pre.executeQuery();
		   out.println("<table border='1'  aligne='center'>");
		   out.println("<tr>");
		   out.println("<th>book id</th>");
		   out.println("<th>bookname</th>");
		   out.println("<th>bookedition</th>");
		   out.println("<th>bookprice</th>");
		   out.println("<th>Edit</th>");
		   out.println("<th>Delete</th>");
		   out.println("</tr>");
		   while(set.next())
		   {
			   out.println("<tr>");
			   out.println("<td>"+set.getInt(1)+"</td>");
			   out.println("<td>"+set.getString(2)+"</td>");
			   out.println("<td>"+set.getString(3)+"</td>");
			   out.println("<td>"+set.getString(4)+"</td>");
			   out.println("<td><a href='editScreen?id="+set.getInt(1)+"'>Edit</a></td>");
			   out.println("<td><a href='deleteuel?id="+set.getInt(1)+"'>Delete</a></td>");
			  out.println("</tr>");
		   }
		   out.println("</table>");
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
