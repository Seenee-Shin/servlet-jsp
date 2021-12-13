package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memberEmail= req.getParameter("email"); 
		
		String[] phone = req.getParameterValues("phone");
		String memberPhone = String.join("-", phone);
		
		String[] address = req.getParameterValues("address");
		
		String memberAddress = null;
		if(!address[0].equals("")) {
			memberAddress = String.join(",,", address);
		}
		
		Member member = new Member();
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddress(memberAddress);
		
		// 회원번호(PK) session에 있는 loginmember에서 얻어오기 
		
		//1) session 객체 얻어오기 
		HttpSession session =req.getSession();
		
		//2) 로그인 멤버 
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		//3) 회원번호 얻어오기 
		member.setMemberNo(loginMember.getMemberNo());
		
		
		try {
			
		int result = new MemberService().update(member);
		
		if(result > 0) {
			session.setAttribute("message", "회원정보가 수정되었습니다.");
			
			//update 성공 == db수정 성공
			// session에 있는 로그인정보 & 마이페이지 수정 X
			loginMember.setMemberEmail(memberEmail);
			loginMember.setMemberPhone(memberPhone);
			loginMember.setMemberAddress(memberAddress);
			
			//loginMember 변수가 주소 참조 -> 복사복은 수정해도 원본이 수정됨 
			// 세션정보 최신화 
			
		}else {
			session.setAttribute("message", "회원정보가 수정 실패 ");
			
		}
		
		//redirect 
		resp.sendRedirect("myPage");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			String errorMessage = "회원 정보 수정 중 문제가 발생 했습니다.";
			
			//req를 이용 값 위임
			req.setAttribute("errorMessage", errorMessage); //에러메세지
			req.setAttribute("e", e); // 예외 관련 정보 객체 
			
			//error.jsp 로 요청 위임
			String path = "/WEB-INF/views/common/error.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		

		}
		
		
	}
}
