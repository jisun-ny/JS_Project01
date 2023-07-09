<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<h1></h1>
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.id }">
				<a href="${pageContext.request.contextPath }/users/loginform">로그인</a>
				<a href="${pageContext.request.contextPath }/users/signup_form">회원가입</a>
			</c:when>
			<c:otherwise>
				<p>
					<a href="${pageContext.request.contextPath }/users/info">${id }</a>로그인
					중... <a href="${pageContext.request.contextPath }/users/logout">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
	</div>

	<ul>
		<li>
			<a href="${pageContext.request.contextPath }/cafe/forum">자유게시판</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath }/cafe/selling">[장터]팔아요</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath }/cafe/wanted">[장터]구해요</a>
		</li>
	</ul>
</body>
</html>
