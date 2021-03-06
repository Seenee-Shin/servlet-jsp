package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

//MVC(Model View Controller) model 2 Pattern
// back&front end를 구분해서 협업이 원할하게 하는 디자인 패턴 

// Model : 비지니스 로직 (백엔드 코드)
// View : 눈에 보이는 화면 jsp, css, html, javascript (프론트 엔드 )
// controller : 클라이언트의 요청을 받아 요청에 따라 알맞은 서비스 결과에 따라 응답화면 제어

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
	
		//파라미터확인
//		System.out.println(memberId);
//		System.out.println(memberPw);
		
		
		//아이디, 비밀번호를 DB로 전달하여 일치하는 회원 정보 조회 Service 호출
		try {
			//1) MemberService 객체 생성
			MemberService service = new MemberService();
			
			//2)로그인 Service 메소드 호출, 결과반환 => 멤버객체(100: 정상 , 102 :정지) || null || 예외
			Member loginMember = service.login(memberId,memberPw);
			
			HttpSession session = req.getSession(); 
			//로그인 관련 처리 시 Session 객체를 이용
			// Session 의 자원관리는 server에서 이루어짐
			// 브라우저가 보유한 session ID를 얻어와 동일한 Session ID를 가진 객체 반환
			
			//로그인 정보 O 
			//로그인 후 결과 화면 => 메인페이지 || 로그인 이전 페이지 
			if(loginMember != null) {
				
				// 정상 , 정지 구분
				if(loginMember.getStatusCode() == 100) { //정상
					//로그인 성공 - 로그아웃 or 창을 닫기 전까지 유지(session에 login정보 추가)
					session.setAttribute("loginMember" , loginMember);
					session.setMaxInactiveInterval(1800);
					// setMaxInactiveInterval(sec) : 최대 세션 유효시간(초단위)
					// 요청시 마다 초기화 
					
					
					//============= 아이디 저장 ======================
					
					//아이디 저장 (Cookie)
					
					//Cookie ? :  클라이언트 측에서 관리하는 파일, 특정 주소 요청 시 마다 알맞은 Cookie파일을 첨부해서 요청을 보냄 
					
					// 쿠키 생성 및 사용 순서 
					// 1. 특정 요청 응답 시 서버 측에서 Cookie 파일을 생성, 응답과 함께 내보내기 
					// 2. 응답을 받은 클라이언트 컴퓨터에 cookie 파일이 저장
					// 3. 이후 해당 사이트 이용 시 저장된 Cookie 파일의 내용을 자동으로 요청.
					
					
					//Cookie 객체 생성 ( Key, Value )
					Cookie cookie = new Cookie("saveId",memberId);
					
					
					//아이디 저장 체크박스 체크
					if(req.getParameter("save") != null){
						// 쿠키가 유지 될 수 있는 유효기간 설정 
						cookie.setMaxAge(60*60*24*30); //한달 지정 
						
					}else { // 체크박스 체크 x 
						// Cookie 없애기 위해 유효기간 0초 설정
						cookie.setMaxAge(0); 
						// 이전 클라이언트 컴퓨터에 저장되어있는 동일 쿠키 파일이 있을 경우 새로운 쿠키파일 덮어쓰기
						// 유효기간 0초, 덮어쓴 후 바로 삭제 
					}
					
					//쿠키파일이 사용되어질 경로 지정
					cookie.setPath(req.getContextPath());
					// -> semmi를 포함한 모든 하위 주에서 사용가능 
					
					// 클라이언트에 전달 
					resp.addCookie(cookie);
					
					//================================================
					
				
					
				}else { //정지
					session.setAttribute("message" , "정지된 회원입니다");
					
				}
			}else { //로그인 실패
				
				session.setAttribute("message" , "입력 정보를 확인해 주세요");
			}
			
		
			// forward : Servlet 구현하기 힘든 응답화면을 만들기 위해 JSP로 req, resp(요청위임시 req,resp객체 유지)를 전달, 화면 구현
			// redirect : 재요청, 직접 응답화면 구현 x/ 재요청하여 응답화면 구현 (토스) -> 기존 요청을 담고 있는 req,resp를 삭제 후 다시 요청
			resp.sendRedirect(req.getContextPath()); //주소경로로 작성 
			//req.getContextPath() : 프로젝트 최상단 주소 : /semi
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
			//화면에 에러 메세지출력
			String errorMessage = "로그인 중 시스템 오류가 발생하였습니다.";
			
			req.setAttribute("errorMesaage", errorMessage);
			req.setAttribute("e", e);
			
			
			String path = "/WEB-INF/views/common/error.jsp"; 
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	}
	
}
