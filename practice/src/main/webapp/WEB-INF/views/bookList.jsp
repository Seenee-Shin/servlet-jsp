<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>책 리스트 </title>
</head>
<body>
	<h1>책 리스트</h1>
	<table border="1" >
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작가</th>
				<th>가격</th>
				<th>재고</th>
			</tr>
		</thead>
		
		<tbody>
			<c:choose>
				<c:when test="${empty bookList }">
					<td colspan="5">
					등록된 책이 없습니다. 
					</td>
				</c:when>
				
				<c:otherwise>
				<c:forEach items="${bookList}" var="book" varStatus="vs">
					<tr>
					<td>${vs.count}</td>
					<td>${book.bookTitle}</td>
					<td>${book.bookAuthor}</td>
					<td>${book.bookPrice}</td>
					<td>${book.sale}</td>
					
					</tr>
				</c:forEach>
				</c:otherwise>
				
			</c:choose>
		</tbody>
	</table>
</body>
</html>