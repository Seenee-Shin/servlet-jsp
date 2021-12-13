package edu.kh.semi.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/secession")
public class SecessionServlet extends HttpServlet{
	
	//get 방식 : 회원탈외 jsp로 요청
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/secession.jsp").forward(req, resp);
	}
	
	//post 방식 : 화원탈퇴 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPw = req.getParameter("currentPw");
		
		HttpSession session = req.getSession();
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		
		//Map을 이용하여 데이터 전달 
		//Map을 이용하는 경우 : 서로 관련없는 데이터들의 묶음이 필요할때, vo실행하지 않을때
		
		Map<String,	String> map = new HashMap<String, String>();
		
		//Map에 데이터 담기 
		map.put("currentPw", currentPw);
		map.put("memberNo", memberNo+""); //int형에 빈 문자열을 더하여 String으로 형변환
		
		try {
			//서비스 전달 후 결과 반환
			int result = new MemberService().secession(map);
			
			String message = null;
			String path = null;
			
			if(result>0) {
				message = "회원탈퇴가 정상적으로 이루어졌습니다."; 
				path = req.getContextPath();
				session.invalidate(); // 기존 세션객체 무효화 
				
			}else{
				message = "비밀번호가 일치하지 않습니다.";
				path = "secession";
				
			}
			
			req.getSession().setAttribute("message", message); //위에서 session 종료, 세션 재생성 후 메세지 출력
			resp.sendRedirect(path);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "탈퇴과정에서 문제가 발생했습니다.");
			req.setAttribute("e", e);
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
		
	}
}
