package com.Bankmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteuel")
public class DeleteServlet  extends HttpServlet{
	private static final String query="delete from bookdata where  id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out= resp.getWriter();
		resp.setContentType("text/html");
		String idpram= req.getParameter("id");
		if(idpram!=null)
		{
			 int id= Integer.parseInt(req.getParameter("id"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdata?useSSL=false", "root", "root");
		    PreparedStatement pre = con.prepareStatement(query);
		    pre.setInt(1, id);
		    int count= pre.executeUpdate();
		    if(count==1)
		    {
		    	out.println("<h1>Record is successfully deleted</h1>");
		    }
		    else
		    {
		    	out.println("<h1>Record is not successfully deleted</h1>");
		    }
		   
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		}
		out.println("<a href='Home.html'>Home</a>");
		out.println("<br>");
		out.println("<a href='booklist'>BookList</a>");

	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
		}


}
