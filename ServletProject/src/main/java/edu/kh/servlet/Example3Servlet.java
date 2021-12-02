package edu.kh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example3Servlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String address = req.getParameter("address");
		String product = req.getParameter("product");
		String amount = req.getParameter("amount");
	
		System.out.println(address);
		System.out.println(product);
		System.out.println(amount);
	}
	
	
}
