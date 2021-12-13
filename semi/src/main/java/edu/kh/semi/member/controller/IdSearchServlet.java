package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/idSearch")
public class IdSearchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputId = req.getParameter("inputId");
		
		try {
			Member member =  new MemberService().idSearch(inputId);
			
//			resp.getWriter().print(member);
			//member객체가 아닌 member.toString 문자열이 출력됨 
			//자바스크립트에서 객체로 취급 불가 -> JSON형태로 수정
			
			// json-simple.jar 라이브러리 이용하기  
			//데이터 -> JSON, JSON->데이터 
			//-> Map 형식으로 데이터를 다룸 (K:V)
//			JSONObject jsonObj = null;
//			
//			if(member != null) {
//				jsonObj = new JSONObject();
//				//DB에서 조회된 결과가 있을 때만 JSONOBject 생성 
//				
//				jsonObj.put("memberId",member.getMemberId());
//				jsonObj.put("memberName",member.getMemberName());
//				jsonObj.put("memberPhone",member.getMemberPhone());
//				jsonObj.put("memberEmail",member.getMemberEmail());
//				jsonObj.put("memberAddress",member.getMemberAddress());
//				
//				System.out.println(jsonObj);
//			}
//			
//			resp.getWriter().print(jsonObj);
			
			//gson사용하기 
			new Gson().toJson(member,resp.getWriter());
			//member 객체를 JSON 형태로 변환 후 지정된 스트림으로 출력
//			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			resp.setStatus(500);
			resp.getWriter().print(e.getMessage());
		}
	}
}
