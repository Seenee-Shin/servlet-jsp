<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<pre>
	requestScope: EL에서 request 범위만을 접근할때 사용 
	sessionScope: EL에서 request 범위만을 접근할때 사용 
	applicationScope: EL에서 applicaition 범위만을 접근할때 사용 
	
	
	request - msg : ${requestScope.msg}
	request - reqValue : ${requestScope.reqValue}
	session - msg : ${sessionScope.msg}
	session - sessionValue : ${sessionScope.sessionValue}
	application - msg : ${applicationScope.msg }
	application - appValue : ${applicationScope.appValue }
	
	**scope 우선순위 확인
	-> scope는 좁은 범위가 높은 우선순위를 가진다. 
	1. page
	2. request
	3. session
	4. application
	
	msg: ${msg} 
	<!-- 우선순위 대로 request의 msg 출력 -->
	</pre>
</body>
</html>