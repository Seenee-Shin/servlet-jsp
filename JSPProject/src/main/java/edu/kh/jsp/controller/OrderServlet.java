package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

@WebServlet("/order")
//(주의) 서로 다른 서블릿이라도 요청주소가 같을 수 없다.
// 하나의 프로젝트에서 같은 주소요청을 처리하는 Servlet은 중복 존재할 수 없다 -> 요청 주소당 Servlet릉 하나만 가능
// 단, 데이터 전달 방식을 이용하여 하나의 요청 주소로 여러 요청을 처리할 수 있다.
// ex) GET방식 /order
//	   POST 방식 / order
public class OrderServlet extends HttpServlet {
	// Servlet : 웹 서비스를 위한 자바 클래스 
	// 요청에 따른 응답 화면을 만드는 클래스 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get방식 요청 처리 메소드 
		
		// HttpServletrequest : 요청 (클라이언트 -> 서버)과 관련된 데이터, 정보등을 담고 있는 객체
		// ex)파라미터, 요청방식, contentRoot, 요청주소, 요청자 ip,RequestDispatcher
		
		
		// HttpServletresponse : 응답(서버 -> 클라이언트)과 관련된 정보, 스트림 등을 담고있는 객체 
		// ex) getWriter() (스트림), Cookie
		
		//파라미터 중 가격, 수량을 곱셈 연산 값을 total 변수에 저장 
		String price = req.getParameter("price");
		String amount = req.getParameter("amount");
		
		int total = Integer.parseInt(price)*Integer.parseInt(amount);
		//Integer.parseInt : String을 Integer 값으로 변환 
		// Integer - int 오토 언박싱 
	
		//total을 JSP로 전달 (HttpRequest 속성으로 추가)
		req.setAttribute("total",total);
		// 두번째 자료형의 값은 Object (업캐스팅 )\
		
		
		//응답화면을 JSP를 이용하여 만들기 
		// 1. Servlet -> JSP 로 요청 정보를 전달할 발송자 RequestDispatcher 생성 
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp");
		
		//2. RequestDispatcher를 이용햐여 요청, 응답 관련 객체 전달
		dispatcher.forward(req, resp);
	}
	
	
	//post방식 요청시 처리 메소드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//문자 인코딩 변환작업
		req.setCharacterEncoding("UTF-8");
		
		// Parameter를 얻어옴과 동시에 int로 반환
		int price = Integer.parseInt(req.getParameter("price"));
		int amount = Integer.parseInt(req.getParameter("amount"));
	
		//price,amount 곱연산 결과를 total이라는 키값으로 req 속성에 추가
		req.setAttribute("total", price*amount); 
		
		//req,reps JSP로 전달 
		req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp").forward(req, resp);
	}
}
