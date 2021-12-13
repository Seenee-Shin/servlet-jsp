package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/updatePw")
public class UpdatePwServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get방식 요청시 비밀번호 변경  jsp요청 위임 (forward)
		req.getRequestDispatcher("/WEB-INF/views/member/updatePw.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//post 방식 요청시 db 비밀번호 변경 
		String currentPw = req.getParameter("currentPw");
		String newPw1 = req.getParameter("newPw1");
		
		
		HttpSession session = req.getSession();
		

		
		int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
		
		try {
		int result = new MemberService().updatePw(currentPw,newPw1,memberNo);
		
		String message = null;
		String path = null;
		if(result >0) {
			message = "비밀번호가 정상적으로 변경되었습니다.";
			path = "myPage";
			
		}else {
			message = "비밀번호를 확인해주세요.";
			path ="updatePw";
		}
		
		session.setAttribute("message", message);
		resp.sendRedirect(path);
			
		}catch(Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMessage", "비밀번호 수정중 문제가 발생했습니다.");
			req.setAttribute("e", e);
			
			String path = "/WEB-INF/views/common/error.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	}
	
}
