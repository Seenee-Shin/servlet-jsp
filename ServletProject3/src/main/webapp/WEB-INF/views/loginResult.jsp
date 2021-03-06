<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>

	<h1><%= request.getParameter("inputId") %>이 로그인 되었습니다.</h1>
	<!-- HTML주석 -->
	<%-- <%= "JSP주석" %> --%>
	<%-- jsP는 Servlet(java)코드로 변환되어 컴파일 될따 JSP는 해석되지 않는다. 
	JSP에 작성된 Java코드는 HTML 주석으로 처리할 수 없다 반드시 JSP주석을 사용하여야 한다--%>
	
	<%--
		<% %> : scriptlet (스크립틀릿) -> JSP에서 자바코드 작성에 사용 
		<%= =%> : Expression (표현식) -> 자바 코드 중 값이나 테이터를 HTML코드 자체로 출력
	 --%>
	 
	 <% if(request.getParameter("saveId") != null){ %>
		 <%-- 아이디 저장을 체크한 경우 --%>
		 <h3>아이디 저장이 체크되었습니다.</h3>
	 <% } else { %>
	 
	 <%-- 아이디 저장을 체크하지 않은 경우 --%>
	 <h3>아이디 저장이 체크 되지 않았습니다. </h3>
	<%} %>
	
	<% for(int i=1; i<=6; i++){ %>
		<h<%=i %>>반복문으로 출력중...</h<%=i %>>
	<%} %>

	<h1>전달된 name확인</h1>
	<% String name = (String)request.getAttribute("name"); %> 
	<h1 style="color:blue"><%= name %></h1>
	
</body>
</html>