<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/wanted.jsp</title>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath }/cafe/wanted/insertform">새 글 작성</a>
		<h3>자유 게시판</h3>
		<table>
			<thead>
				<tr>
					<th>글 번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.writer }</td>
						<td>
							<a href="${pageContext.request.contextPath }/cafe/wanted/detail?num=${tmp.num }&condition=${condition}&keyword=${encodedK}">${tmp.title }</a>
						</td>
						<td>${tmp.regdate }</td>
						<td>${tmp.viewCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>