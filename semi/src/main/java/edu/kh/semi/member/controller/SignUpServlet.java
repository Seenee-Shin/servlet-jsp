package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/signup")
public class SignUpServlet extends HttpServlet{
	//데이터 전송 방식
	// POST : 삽입(CREATE) 
	// GET : 조회(READ)
	// PUT : 수정(UPDATE) 
 	// DELETE : 삭제 (DELETE)
	
	
	// get 방식 요청시 회원가입 화면으로 응답 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path ="/WEB-INF/views/member/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	//Post 방식 요청시 회원정보 입력 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Post 방식의 경우 문자 인코딩 처리 필요 -> encodingFilter를 생성, 별도처리 필요 x
		
		//회원 가입시 입력받은 파라미터를 모두 변수에 저장 
		String memberId = req.getParameter("id");
		String memberPw = req.getParameter("pwd1");
		String memberName = req.getParameter("name");
		String memberEmail = req.getParameter("email");
		
		String[] phone = req.getParameterValues("phone");
		String[] address = req.getParameterValues("address");
		
		//전화번호를 하나의 문자열로 병합
		String memberPhone = String.join("-",phone);
		// String.join(구분자 , 배열 ): 요소 사이에 구분자를 추가하여 문자열로 병합
		
		
		//주소를 하나의 문자열로 병합 (선택적 입력)
		String memberAddress = null;
		
		if(!address[0].equals("")) { //주소 작성 
			memberAddress = String.join(",,,", address);
		}
		
		//저장된 파라미터를 하나의 member객체에 저장 
		Member member = new Member(memberId, memberPw, memberName, memberPhone, memberEmail, memberAddress);
		
		try {
			//Member 서비스 객체 생성 
			MemberService service = new MemberService();
			
			//service 호출,실행 후 결과 반환
			int result = service.signup(member);
			
			//중간확인 
			System.out.println(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
				
	
	}
}
