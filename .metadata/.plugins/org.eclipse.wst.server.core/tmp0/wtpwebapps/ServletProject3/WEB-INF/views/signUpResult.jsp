<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 변수 선언 -->    
<% // 스크릿틀릿 : JSP에서 자바코드 작성하는 영역
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과 화면</title>
</head>
<body>
	<h1><%= msg %></h1>
</body>
</html>