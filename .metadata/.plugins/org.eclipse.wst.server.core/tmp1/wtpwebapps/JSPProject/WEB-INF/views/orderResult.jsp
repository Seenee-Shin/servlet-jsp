<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int total = (int)request.getAttribute("total"); 
	String method = request.getMethod(); // get, post 등의 요청방식
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 결과</title>
</head>
<body>
	<h1><%= method %> 요청 결과</h1>
	상품명 : <%=request.getParameter("pName")%> <br>
	가격 : <%=request.getParameter("price") %>원 <br>
	수량 : <%=request.getParameter("amount")%>개 <br>
	총 금액 : <%=total %>원<br>
	
 	<button onclick="history.back();">뒤로가기</button>
</body>
</html>