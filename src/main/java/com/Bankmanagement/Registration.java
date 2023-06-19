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

@WebServlet("/registration")
public class Registration  extends HttpServlet{
	private static final String query="insert into bookdata(bookname,bookedition,bookprice) values(?,?,?)";
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter out= resp.getWriter();
	resp.setContentType("text/html");
	String bookName= req.getParameter("bookname");
	String bookedition=req.getParameter("bookedition");
	String bookprice = req.getParameter("bookprice");
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdata?useSSL=false", "root", "root");
	    PreparedStatement pre = con.prepareStatement(query);
	    pre.setString(1, bookName);
	    pre.setString(2, bookedition);
	    pre.setString(3, bookprice);
	    int count= pre.executeUpdate();
	    if(count==1)
	    {
	    	out.println("<h2>Registration is successfull</h2>");
	    }
	    else
	    {
	    	out.println("<h2>Registration is not successfull</h2>");
	    }
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
	out.println("<a href='Home.html'>Home</a>");
	out.println("br");
	out.println("<a href='booklist'>BookList</a>");
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
