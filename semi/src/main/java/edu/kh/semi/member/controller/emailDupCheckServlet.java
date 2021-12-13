package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.member.model.service.MemberService;

@WebServlet("/member/emailDupCheck")
public class emailDupCheckServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputEmail = req.getParameter("inputEmail");
		
		try {
			int result = 0 ;
			MemberService service = new MemberService();
			
			result = service.emailDupCheck(inputEmail);
			
//			PrintWriter out = resp.getWriter();
//			out.print(result);
			
			resp.getWriter().print(result);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			// 에러상태 강제 인식,  ajax에 에러메세지 전달
			//HTTP응답 상태 코드
			//200 : 요청응답 성공 
			// 400: 잘못된 요청
			// 403: 서버가 접근을 거부 
			// 404 : 주소 불명
			// 405 : 지정된 method 방식 사용 불가 
			// 500 : 서버 내부 에러 
			
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	}
}
