<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 변수선언 if, for 같은 주요기능 담당 --%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- 문자열 관련 기능 담당 : jstl 같은 태그형식이 아닌 EL형식으로 작성 --%>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	
	<div class="container"  style="min-height:700px">
		
		<div class="row my-5">
		
			<jsp:include page="sideMenu.jsp"></jsp:include>		
			
			<div class="col-sm-8">
				
				<h3>My Page</h3>
				
				
			
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="update" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${sessionScope.loginMember.memberId}</h5>
							</div>
						</div>
	
						<!-- 이름 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>이름</h6>
							</div>
							<div class="col-md-6">
								<h5 id="name">${loginMember.memberName}</h5>
							</div>
						</div>
	
						<!-- 전화번호 -->
						<%-- memberPhone을 "-"기준으로 나누기 --%>
						<c:set var="ph" value="${fn:split(loginMember.memberPhone, '-')}"/>
						
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="phone1">전화번호</label>
							</div>
							<!-- 전화번호1 -->
							<div class="col-md-3">
								<select class="custom-select" id="phone1" name="phone">
									<option>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>019</option>
								</select>
							</div>
							
	
							<!-- 전화번호2 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone2" name="phone" value="${ph[1]}" >
							</div>
	
							<!-- 전화번호3 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone3" name="phone" value="${ph[2]}">
							</div>
						</div>
	
						<!-- 이메일 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="memberEmail">Email</label>
							</div>
							<div class="col-md-6">
								<input type="email" class="form-control" id="email" name="email" value="${loginMember.memberEmail}">
							</div>
						</div>
						<br>
	
						<!-- 주소 -->
						<!-- 오픈소스 도로명 주소 API -->
						<!-- https://www.poesis.org/postcodify/ -->
						<%-- memberAdrdess를 ",," 기준으로 나눈 배열 저강 --%>
						<c:set var="addr" value="${fn:split( loginMember.memberAddress, ',,') }"/>
						
						
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="postcodify_search_button">우편번호</label>
							</div>
							<div class="col-md-3">
								<input type="text" name="address" class="form-control postcodify_postcode5" value="${addr[0]}">
							</div>
							<div class="col-md-3">
								<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
							</div>
						</div>
	
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="address1">도로명 주소</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control postcodify_address" name="address" id="address1"  value="${addr[1]}">
							</div>
						</div>
	
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="address2">상세주소</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control postcodify_details" name="address" id="address2"  value="${addr[2]}">
							</div>
						</div>
	
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
		
		
	<!-- 오픈소스 도로명 주소 검색 API -->
	<!-- https://www.poesis.org/postcodify/ -->
	<!-- postcodify 라이브러리를 CDN 방식으로 추가. -->
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script>
		// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
		$(function () {
			$("#postcodify_search_button").postcodifyPopUp();
		});
	</script>
	<!-- 마이페이지 조회, 수정 관련 스크립트 -->
	<script>
		$(function(){
			const ph0 = "${ph[0]}";
			
			//js코드에 EL을 작성하는 경우 ""로 감싸지 않으면 문제 발생  
			//문자가 숫자로 인식하거나 올바르지 않은 JS표기법, 변수의 값 대입 X 등
			
			
			//select -> option 접근하기 
			$("#phone1>option").each(function(index, item){
				if(ph0 == item.innerText){
					item.setAttribute("selected", true)
				}
			})
		})
	</script>
	
	<script src="${contextPath}/resources/js/memberUpdate.js"></script>
	
	
</body>
</html>
