<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/selling/update.jsp</title>
</head>
<body>
	<script>
		alert("${id}님의 글을 수정했습니다.");
		location.href = "${pageContext.request.contextPath }/cafe/forum/detail?num=${param.num}";
	</script>
</body>
</html>