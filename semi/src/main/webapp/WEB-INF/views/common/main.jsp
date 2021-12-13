<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- header include -->
	<jsp:include page="header.jsp"/>
	
	<!-- 메인 화면 이미지 -->
	<div class="py-5 bg-image-full" style="background-image: url('${contextPath}/resources/images/main/main_bg.jpg');">
	    <h1>Servlet/JSP를 이용한<br>Semi Project</h1>
	</div>
	
	<!-- 내용 작성 부분 -->
	<section class="py-5">
	  <div class="container">
	    <h1>${today}</h1>
	    <p class="lead">로그인 회원정보</p>
	  	<p>
	  		ID : ${loginMember.memberId} <br>
	  		Name : ${loginMember.memberName} <br>
	  		Phone : ${loginMember.memberPhone} <br>
	  		E-mail: ${loginMember.memberEmail} <br>
	  		Address: ${loginMember.memberAddress} <br>
	  		
	  	</p>
	  </div>
	  
	</section>
	
	<section class="py-5">
	  <div class="container">
	    <h1>아이디가 일치하는 회원정보 조회 (AJAX)</h1>
	    <p class="lead">
	    	검색할 아이디 : <input type="text" id="searchId">
	    	<button type="button" id="idSearchBtn">아이디 검색</button>
		</p>
		
		<table>
			<tbody id="idSearchResult">
				<tr>
					<td>이라는 회원이 존재하지 않습니다.</td>
				</tr>
				
				<tr>
					<th>아이디</th>
					<td>user01</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td>user01</td>
				</tr>
				
				<tr>
					<th>전화번호</th>
					<td>user01</td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td>user01</td>
				</tr>
				
				<tr>
					<th>주소</th>
					<td>user01</td>
				</tr>
			</tbody>
		</table>
	  </div>
	</section>
	
	<!-- footer include -->
	<jsp:include page="footer.jsp"/>
	
</body>
</html>