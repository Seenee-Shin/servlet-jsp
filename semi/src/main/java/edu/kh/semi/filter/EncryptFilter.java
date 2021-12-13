package edu.kh.semi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.wrapper.EncryptWrapper;


// Filter : 클라이언트 요청시 생성되는 HttpServletRequest, HttpServletResponse 객체가 
//			요청 응답을 처리하는 Servlet/JSP 에 도달하기 전에 전/후 처리하는 클래스 

// FilterChain : 필터를 연쇄적으로 연결 가능

//필터 생명주기
// init() -> doFilter() -> destroy -> init ...
// init :  서버 실행시 필터 실행
// doFilter :  필터 역할 수행
// destroy : 필터코드가 변경되어 이전 필터가 필요 없어 졌을때 수행되는 메


//@WebFilter : 어떤요청을 필터링 할지 주소를 작성하고 필터에 이름을 부여하여 필터링 순서를 지정 할 수 있게하는 어노테이션
@WebFilter(filterName="encryptFilter", 
			urlPatterns={"/member/login", "/member/signup" , "/member/updatePw" , "/member/secession"})

public class EncryptFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("암호화 필터 생성");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//ServletRequest,  ServletResponse
		// HttpServletResponse, Request의 부모 타입
		// 부모부분만 사용가능 -> 다운캐스팅하여 자식부분 사용 
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//Post 방식의 요청일 경우
		if(req.getMethod().equals("POST")) {
			
			//req 요청 객체를 encWrapper로 감싸기 ->오버라이딩된 getParameter 사용가능
			EncryptWrapper encWrapper = new EncryptWrapper(req);
			
			//기존 req대신 ecnWrapper 를 Servlet으로 전달 
			chain.doFilter(encWrapper, response);
		}else {
			// 다음 필터로 요천/ 응답 전달
			// 다음 필터가 없으면 Servlet/jsp 전달
			chain.doFilter(request, response);
		}
				
	}

	public void destroy() {
		
	}

}
