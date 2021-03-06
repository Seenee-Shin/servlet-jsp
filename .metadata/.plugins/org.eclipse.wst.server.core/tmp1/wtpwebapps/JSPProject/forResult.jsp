<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>c:forEach 향상된 for문 결과</title>
</head>
<body>
	EL을 이용하여 같은 name이 여러개인 Parameter 얻어오기<br>

	${param.lang} <br> 
	<!-- 같은 name이 여러개인 경우 첫번째 값만 표시-->
	
	${paramValues.lang } <br>
	<!-- 같은 name을 가진 Parameter를 하나의 배열로 반환 
		java의 배열을 toString이 없기 떄문에 hashcode()값이 반환 -->
		
	${paramValues.lang[0]}<br>
	${paramValues.lang[1]}<br>
	${paramValues.lang[2]}<br>
	<!-- 배열을 인덱스로 접근 -->
	
	<hr>
	<h3>향상된 for문으로 배열 인덱스 접근</h3>
	<!--paramValues.lang를 반복 접근. 현재 반복 상태를 나타내는 varStatus="선언  -->
	<c:forEach var="item" items="${paramValues.lang}" varStatus="vs">
		
		item 		  : ${item} <br>
		현재 값 	  : ${vs.current} <br>
		현재 인덱스   : ${vs.index} <br>
		현재 반복횟수 : ${vs.count} <br>
		첫번째 반복?  :	${vs.first} <br>
		마지막 반복?  :	${vs.last} <br>
		<hr>
	</c:forEach>
</body>
</html>