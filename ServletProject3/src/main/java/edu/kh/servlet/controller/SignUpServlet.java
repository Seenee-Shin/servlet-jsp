package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPW");
		String memberName = req.getParameter("memberName");
		String memberEmail = req.getParameter("memberEmail");
		
		System.out.println(memberId);
		System.out.println(memberPw);
		System.out.println(memberName);
		System.out.println(memberEmail);
		
	}
	
	//post방식을 처리하는 메소드 
	//post 방식으로 데이터값을 전달하게 외면 문자인코딩이 지정되어 있지 않아 2byte 범위의 글자는 깨진다. 
	// 해결 방법 : 문자 인코딩을 지정해 준다. 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		//-> 요청관련 객체에 담긴 값의 문자 인코딩을 변경 (대,소문자 상관 없음 )
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPW");
		String memberName = req.getParameter("memberName");
		String memberEmail = req.getParameter("memberEmail");
		
		System.out.println(memberId);
		System.out.println(memberPw);
		System.out.println(memberName);
		System.out.println(memberEmail);
		
		//응답에 대항 응답화면 만들어 내보내기 
//		//1. 응답 화면의 문서 형식 지정 
//		resp.setContentType("text/html; charset=UTF-8");
//		
//		//2.응답화면을 클라이언트에게 전달할 통로 얻어오기
//		PrintWriter out = resp.getWriter();
//		
//		//3. 클라이언트에게 응답화면 코드(html) 내보내기 (출력)
//		out.print("<!DOCTYPE html>");
//		out.print("<html>");
//		
//		out.print("<head>");
//		out.print("<title> 회원가입 결과 </title>");
//		out.print("</head>");
//		
//		out.print("<body>");
//		out.print("<h1>"+memberName+"님의 회원가입을 환영합니다.</h1>");
//		
//		out.print("</body>");
//		
//		out.print("</html>");
		
		//************JSP를 이용하여 응답하기 
		
		//Dispatcher : 발송자, 필요정보를 제공 
		//RequestDispatcher : 요철발송자, 요청발송 객체 (요청 위임 객체)
		//		-> Servlet에서는 응답화면(html)코드 작성이 어려움. 응답화면을 더 쉽게 만들 수 있는 JSP에 응답화면을 작성 요청 발송 
		//		 이떄 매개변수로 요청 관련 정보를 담고 있는 객체 HttpServletRequest를 전달하여 파라미터,클라이언트 정보를 이용해 응답화면을 구성 
		
		//		응답 관련정보를 담고 있는 객체 HttpServletResponse를 전달하여 클라이언트와 스트림으로 연결하여 자동으로 응답 화면을 출력해 준다.
		
		RequestDispatcher dispatcher= req.getRequestDispatcher("/WEB-INF/views/result.jsp");
		dispatcher.forward(req,resp);
 		
		
	}
}
