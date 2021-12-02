package edu.kh.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//기존 Web.xml에 작성한 url패턴에 따른 servlet연결 구문을 간소화하는 어노테이션
@WebServlet("/login")
public class loginServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//post방식 요청 -> 문자인코딩 변경
		
		req.setCharacterEncoding("UTF-8");
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		
		//체크박스 1개 => 배열로 받을 필요 없음 
		//체크박스는 체크가 된 경우 value값을 전달 
		String saveId = req.getParameter("saveId");
		
		System.out.println(inputId);
		System.out.println(inputPw);
		System.out.println(saveId);
		
		//파라미터가 아닌 새로운 값을 JSP에 위임하는 방법 
		String name = "딘딘";
		
		if(inputId.equals("user01")) {
			name="임철";
		}
		
		//name은 요청을 처리해서 만들어진 결과물 
		// ->name을 HttpServleyRequest 객체에 담아 JSp로 위임
		
		req.setAttribute("name", name); //key : value형태로 req 객체에 세팅
		//Value(두번째 매개변수 )의 자료형을 Object로 설정되어 잇다.
		// 모든 객체를 매개변수으로 전달 할 수 있다 
		// == 매개변수로 들어온 모든 객체의 Object부분 말을 참조할 수 있다.
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
		dispatcher.forward(req, resp);
	}
}
