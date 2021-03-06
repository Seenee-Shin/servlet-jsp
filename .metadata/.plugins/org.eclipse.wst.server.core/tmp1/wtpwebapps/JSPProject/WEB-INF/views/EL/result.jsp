<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 페이지 지시자 -->
<%-- <%@page import="edu.kh.jsp.model.vo.Person"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Result</title>
</head>
<body>
	<h3>파라미터 얻어오기 </h3>
	
	<pre>
	param.name속성값 : 요청객체에 담겨있는 파라미터를 *출력*
	</pre>
	
	1) JSP 표현식 : 
	<%= request.getParameter("inputName") %> /
	<%= request.getParameter("inputAge") %> /
	<%= request.getParameter("inputAddr") %> <br>
	2) EL 작성법 : 
	${param.inputName}/ ${param.inputAge} / ${param.inputAddr}
	
	<hr>
	
	<h3>속성 (Attribute) 얻어오기</h3>
	<pre>
		Sevlet 에서 추가됨 속성을 표현할 경우
		기본 jsp방식은 import, 다운캐스팅, getter를 이용한 출력등을 작성해야만한다. 
		
		EL은 이러한 요소들을 개선하여 
		import, getter, 다운캐스팅의 작성 없이 
		오로지 {속성키}, {속성 key, 필드명}으로 출력 가능 
		
		단, toString, getter가 모두 작성되어 있아야만 출력이 가능 
	</pre>
<%-- 	1) JSP 표현식 <br>
	<!-- Obeject 자료형을 Person으로 다운캐스팅 -->
	<% Person person = (Person)request.getAttribute("person");%>
	<%= person.toString() %> <br>
	이름 : <%= person.getName() %> <br>
	나이 : <%= person.getAge() %> <br>
	주소 : <%= person.getAddr() %> <br> 
--%>
	
	<hr>
	2) EL
	${person} <br>
	이름 : ${person.name} <br>
	나이 : ${person.age} <br>
	주소 : ${person.addr}<br>
	
	<hr>
	<h3>null 처리 방법</h3>
	<pre>
	EL은 null값을 출력해야하는 경우 ""(빈문자열) 출력
	+ EL은 NullPointerException 발생시에더 ""출력
	+ EL은 null과 비어있음(isEmpty())의 구분이 없다. 
	</pre>
	
	1)JSP 
	<% String str = request.getParameter("str"); %>
	str : <%= str %> <br>
	<%--  <%= str.length() %>  : 에러 발생 --%> 
	
	조건문 결과 : <%= str == null %> <!-- true 반환 -->
	
	<br> <br>
	
	2)EL <br>
	str : ${param.str} <br>
	길이 : ${param.str.length} <br>
	조건문 결과 : ${empty param.str} <br>
	조건문 결과 (부정) : ${!empty param.str}
	<br> <br>
	+ 추가확인 
	<% List<String> list = new ArrayList<String>(); %><br>
	1) jsp로 List가 비어있는지 확인 : <%= list.isEmpty() %> <br>
	2) EL로 List가 비어있는디 확인 : ${empty list }
	
	<hr>
	
	request 확인 : ${requestScope.reqValu } <br>
	session 확인 : ${sessionScope.sessionValue } <br>
	application 확인 : ${applicationScope.appValue }

</body>
</html>