<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/login.jsp</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${not empty sessionScope.id }">
				<p>
					<strong>${sessionScope.id }</strong>님이 로그인되었습니다.
					<a href="${requestScope.url}">확인</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
				아이디 혹은 비밀번호가 틀려요
				<a href="loginform?url=${requestScope.encodedUrl }">다시시도</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>