<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signup_form.jsp</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath }/users/signup" method="post" id=myForm>
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id"/>
		</div>
		<div>
			<label for="pwd">비밀번호</label>
			<input type="password" name="pwd" id="pwd"/>
		</div>
		<div>
			<label for="pwd2">비밀번호 확인</label>
			<input type="password" name="pwd2" id="pwd2"/>
		</div>
		<div>
			<label for="email">이메일</label>
			<input type="text" name="email" id="email"/>
		</div>
		<button type="submit">가입</button>
	</form>
</body>
</html>