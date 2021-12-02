package edu.kh.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.servlet.model.service.MemberService;
import edu.kh.servlet.vo.Member;

@WebServlet("/jdbc/signUp")
public class SignUp2Servlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		String memberName = req.getParameter("memberName");
		String membereEmail = req.getParameter("memberEmail");
		
		//Member.vo로 파라미터 전부 담기
		Member member = new Member(memberId, memberPw, memberName, membereEmail);
		
		//MemberService 객체 생성 
		MemberService service = new MemberService();
		
		//입력 받은 값들을 DB에 삽입 후 결과 반환 받기 
		try {
			int result = service.signUp(member);
			
			String message = null;
			
			if(result > 0) {
				message = "회원가입 성공";
			}else {
				message = "회원가입 실패";
			}
			//기존에 request에 없는 값을 세팅 
			req.setAttribute("msg", message);
			
			//JSP 요청 위임 
			//1) 요청 위임 객체 생성 
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/signUpResult.jsp");
			
			//2.요청 위임 객체를 이용해서 req,resp 전달 
			dispatcher.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
