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
			memberAddress = String.join(",,", address);
			
			memberAddress = replaceParameter(memberAddress);
		}
		
		//저장된 파라미터를 하나의 member객체에 저장 
		Member member = new Member(memberId, memberPw, memberName, memberPhone, memberEmail, memberAddress);
		
		try {
			//Member 서비스 객체 생성 
			MemberService service = new MemberService();
			
			//service 호출,실행 후 결과 반환
			int result = service.signup(member);
			
			//중간확인 
//			System.out.println(result);
			
			String message = null;
			if(result > 0) {
				message = "회원가입 성공";
			}
		
			//redairect 시 req,resp 재생성 -> 위 message 사라짐 (req로의 값 전달 불가 )-> 범위가 한단계 높은 session으로 구현
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			//실패시 메인 페이지 => redirect(이미 Service구현 완료)
			resp.sendRedirect(req.getContextPath());
			
		}catch(Exception e){
			e.printStackTrace();
			// Http 상태코드 500 => Internal server error 
			// - 백엔드에서 발생한 에러 
			// - 로직수행세 사용되는 값/ SQL,DB연결/ 코드 오타 등이 발생 하였을때 나타나는 상태 코드
			
			String errorMessage = "회원 가입 중 문제가 발생 했습니다.";
			
			//req를 이용 값 위임
			req.setAttribute("errorMessage", errorMessage); //에러메세지
			req.setAttribute("e", e); // 예외 관련 정보 객체 
			
			//error.jsp 로 요청 위임
			String path = "/WEB-INF/views/common/error.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
		
		
				
	
	}
	
	//크로스 사이트 스크립트 방지
	private String replaceParameter(String param) {
		//크로스사이트 스크림트 공격이 가능한 이유
		// -> <,>,&,\ 같은 태그가 html코드로 해석됨 
		// html코드로 인식 될 만한 특수문자로 문자 변환
		
		String result = param;
		
		if(result != null) {
			result = result.replaceAll("&","&amp;");
			result = result.replaceAll("<","%lt");
			result = result.replaceAll(">","%gt");
			result = result.replaceAll("\"","&quot;");
			
		}
		return result;
	}
	
}
