package edu.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.member.model.service.MemberService;

@WebServlet("/member/idDupCheck")
public class IdDupCheckServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputId = req.getParameter("inputId");
		
		
		try {
			MemberService service = new MemberService();
			
			//DB에 중복되는 아이디가 있는지 조회하는 서비스 요청 후 결과 반환
			int result = service.idDupCheck(inputId);
			
			//ajax는 부분갱신으로 forward, redirect구문 필요없음
		 	PrintWriter out =  resp.getWriter();
			out.print(result);
			//아이디 중복 조회 결과를 클라이언트에게 출력
			//현재 비동기 통신 중 -> succes의 매개변수로 이동 
		 	
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
