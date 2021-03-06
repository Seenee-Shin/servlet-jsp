<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02.JSP action tag</title>
</head>
<body>
	<%-- header.jsp:inclue 파일 경로 작성--%>	
	<jsp:include page="/common/header.jsp"></jsp:include>
	<%--<jsp:include page="/common/header.jsp/">--%>
	<h1> JSP action Tag란?</h1>
	<pre>
	JSP Action?
	XML 기술을 이용하여 기존의 JSP 문법을 확장하는 메커니즘을 제공하는 태그.
	기본적으로 JSP에 내장되어 있다.
	
	JSP Action Tag: 기존 html이 아닌 Java코드를 태그형식으로 작성해둔 것
	브라우저가 아닌 Servlet Container(TomCat)이 해석
	</pre>
	
	<h1>JSP include Tag</h1>
	<pre>
	현재의 jsp에 다른 jsp를 포함시킴
	</pre>
	
	<jsp:include page="/common/footer.jsp"/>
	
	<script type="text/javascript">
	$(document).ready(function() {
		alert("jQuery 사용가능")
	})
	</script>
	
</body>
</html>