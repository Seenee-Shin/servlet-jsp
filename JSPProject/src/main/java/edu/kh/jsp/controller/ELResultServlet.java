package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.kh.jsp.model.vo.Person;

@WebServlet("/EL/result")
public class ELResultServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String inputName = req.getParameter("inputName") + "님";
		int inputAge = Integer.parseInt(req.getParameter("inputAge")) + 10;
		String inputAddr = req.getParameter("inputAddr");
		
		
		//파라미터를 이용해 Person 객체 생성 
		Person person = new Person(inputName, inputAge, inputAddr);
		
		//req 객체에 person속성 추가 전달 
		req.setAttribute("person", person);
		
		
		//jsp로 요청 위임
		String path = "/WEB-INF/views/EL/result.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		
	}
	
	

}
