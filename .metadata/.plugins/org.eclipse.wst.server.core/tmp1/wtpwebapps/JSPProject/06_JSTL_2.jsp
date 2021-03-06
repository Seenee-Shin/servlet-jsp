<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>06.JSTL - c:forEach</title>
</head>
<body>
	<h1> c:forEach</h1>
	<pre>
	java의 for문
	
	속성
	var : 현재 반복 횟수에 해당하는 변수 (== int i) 
	begin : 반복 시작 값
	end : 반복 종료 값
	step : 반복시 마다 증가할 값 (증감식), 미작성시 기본 값 1
	
	items : 반복 접근할 객체 명 (Collection 객체) // 향상된 for 문 
	varStatus : 현재 반복에 해당되는 상태 정보 
	- 제공되는 값
		1) current :현재 반복 횟수 또는 현재 접근중인 객체 
		2) index : 현재 객체가 몇번째 인덱스인지 반환 (0부터 시작)
		3) count : 현재 반복문이 몇바퀴 반복 중인지 반환 (1부터 시작)
		4) first : 첫 번째 반복일 경우 true 반환
		5) last : 마지막 반복일 경우 true 반환
	</pre>
	
	<hr>
	<h3>일반 for문 형식 사용 </h3>
	<c:forEach var="i" begin="1" end="6" >
	<h${i}>${i}번째 반복</h${i}>
	</c:forEach>
	
	<table border="1">
		<tbody>
			<c:forEach var="i" begin="1" end="10">
				<tr>
					<th>${i}</th>
					<td>
						<a href="#">${i}번째 게시글 입니다.</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	
	<hr>	
	<h3>향상된 for문 형식 사용</h3>
	
	<form method="get" action="forResult.jsp">
		<input type="checkbox" name="lang" value="java"> java <br>
		<input type="checkbox" name="lang" value="sql"> sql <br>		
		<input type="checkbox" name="lang" value="jdbc"> jdbc<br>		
		<input type="checkbox" name="lang" value="html"> html <br>		
		<input type="checkbox" name="lang" value="css"> css <br>		
		<input type="checkbox" name="lang" value="javascipt"> javascript <br>		
		<input type="checkbox" name="lang" value="jQuery"> jquery <br>		
		<input type="checkbox" name="lang" value="servlet"> servlet <br>		
		<input type="checkbox" name="lang" value="jsp"> jsp <br>	
		<br>	
		<button>제출</button>
	</form>
	
</body>
</html>