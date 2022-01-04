<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- JSTL c태그 사용을 위한 taglib 추가 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 프로젝트의 시작 (최상위 주소)를 간단히 얻어올 수 있도록 application scope로 contextPath라는 변수를 생성함--%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="application"/>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Semi Project</title>

<!-- Bootstrap4 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

<!-- 공용 CSS / CSS같은 자원의 경로는 주소 경로를 적어준다. -->
<!-- tip : header, footer 같이 여러 주소에서 사용되어 지는 화면의 자원 주소 경로 지정시 절대 경로를 사용하는 것이 좋다 -->
<link rel="stylesheet" href="${contextPath}/resources/css/style.css">

</head>

<body>
	<!-- navbar을 이용한 header -->
	<header class="header navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
		
			<!-- 좌상단 로고 -->
			<a class="navbar-brand" href="${contextPath}">Semi Project</a>
			
			<!-- 화면 크기가 작아진 경우 나타나는 햄버거 버튼(반응형) -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<!-- 우상단 메뉴 -->
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">

					<li class="nav-item"><a class="nav-link" href="${contextPath}/board/list">Board</a></li>

					<!-- 로그인 버튼 -->
					<%-- Modal 동작 버튼은 data-toggle="modal" 속성과 href 속성값으로 보여질 Modal 영역 id를 작성하면된다. --%>

					<!-- 로그인에 따라 화면 바꾸기 -->
					<c:choose>
						<c:when test="${empty sessionScope.loginMember}">
							<!-- 로그인 전 -->
							<li class="nav-item active"><a class="nav-link" data-toggle="modal" href="#login-modal">Login</a></li>
						</c:when>
						
						<c:otherwise>
							<!-- 로그인 후 -->
							
							<li class="nav-item active"><a class="nav-link"  href="${contextPath}/member/myPage">${sessionScope.loginMember.memberName}</a></li>
							<li class="nav-item active"><a class="nav-link"  href="${contextPath}/member/logout">Logout</a></li>
							<!-- a태그는 모두 get방식 -->
						</c:otherwise>
					</c:choose>

				</ul>
			</div>
			
		</div>
	</header>

	<!-- Modal : 화면에 불투명한 배경을 씌우고 그 위에 새로 나타나는 창 -->
	<div class="modal fade" id="login-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<%-- 모달 헤더 --%>
				<div class="modal-header">
					<h5 class="modal-title" id="myModalLabel">로그인 모달창</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
				</div>

				<%-- 모달 바디 --%>
				<div class="modal-body">
				
					<!-- form 태그를 이용하여 아이디 비밀번호을 입력 받은 후 서버 제출 -->
					<!-- onsubmit을 이용하여 로그인 시 입력 받은 값이 유효한지 판별 -->
					<form class="form-signin" method="POST" action="${contextPath}/member/login
					" onsubmit="return loginValidate()">

						<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디" value="${cookie.saveId.value}"> <br> 
						<!-- EL을 이용한 cookie접근 ${cookie.saveId} =>  -->
						<input type="password" class="form-control" id="memberPw" name="memberPw" placeholder="비밀번호"> <br>

						<div class="checkbox mb-3">
							<label> 
								<!-- c:set으로 생성된 변수는 page scpoe -> 해당 page어디서든 사용 가능 지역변수x  -->
								<c:if test="${!empty cookie.saveId.value}">
								<c:set var="chk" value="checked"/>
								
								</c:if>
								
								<input type="checkbox" name="save" id="save" ${chk}> 아이디 저장 
								<!-- checked : on / unchecked : null -->
							</label>
						</div>


						<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>


						<a class="btn btn-lg btn-secondary btn-block" href="${contextPath}/member/signup">회원가입</a>
						
					</form>
				</div>
				
				<%-- 모달 푸터 --%>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>