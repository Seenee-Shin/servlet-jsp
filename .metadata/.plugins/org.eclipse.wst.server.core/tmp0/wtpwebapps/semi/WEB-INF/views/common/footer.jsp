<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Footer -->
<footer class="py-5 bg-dark footer">
	<div class="container">
		<p class="m-0 text-center text-white">Copyright &copy; KH Information Educational Institute A-Class</p>
	</div>
</footer>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<!-- Bootstrap4 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

<!-- 공용 JS -->
<script src="${contextPath}/resources/js/semi.js"></script>

<!-- session 에 message 속성이 존재하는 경우 alter 창을 해당 내용 출력 -->
<c:if test="${!empty sessionScope.message }">
	<script>
		$(function(){
		alert("${message}");
		//EL 작성시 scope를 작성하지 않으면 scope를 순서대로 검색, 일치하는 속성이 있으면 출력
		})
	</script>
	
	<%--     문제 : session에 message 키 값이 유지되어있어서 정상회원임에도 값이 남아있다. 
         해결 : c:if문이 끝나기 전에 session의 message를 지운다  --%>
         
   <%-- message 1회 출력 후 session에서 제거 --%>
   <c:remove var="message" scope="session"/>
</c:if>