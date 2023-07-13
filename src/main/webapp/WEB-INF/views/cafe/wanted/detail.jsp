<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/wanted/detail.jsp</title>
</head>
<body>
	<div class="container">
		<!--만일 이전글(더 옛날글)의 글 번호가 0이 아니라면 -> 이전글이 존재한다면  -->
		<c:if test="${dto.prevNum ne 0 }">
			<a href="detail?num=${dto.prevNum }&condition=${condition}&keyword=${encodedK }">이전글</a>
		</c:if>
		<!--만일 다음글(더 최근 글)의 글 번호가 0이 아니라면 -> 다음글이 존재한다면  -->
		<c:if test="${dto.nextNum ne 0 }">
			<a href="detail?num=${dto.nextNum }&condition=${condition}&keyword=${encodedK }">다음글</a>
		</c:if>
		
		<!-- 만일 검색 키워드가 있다면? -->
		<c:if test="${not empty condition }">
			<p>
				<strong>${condition }</strong> 조건, <strong>${keyword }</strong> 검색어로 검색된 내용 자세히 보기
			</p>
		</c:if>
	
		<h3>${dto.title }</h3>
		<table>
			<tr>
				<th>글번호</th>
				<td>${dto.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.regdate }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.viewCount }</td>
			</tr>
			<tr>
				<th>업로드 이미지</th>
				<td>
					<img src="${pageContext.request.contextPath }${dto.imagePath }" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>${dto.content }</div>
				</td>
			</tr>
		</table>
		<!-- 로그인 된 아이디와 글의 작성자가 같으면 수정, 삭제 링크 제공 -->
		<c:if test="${sessionScope.id eq dto.writer}">
			<a href="updateform?num=${dto.num }">수정</a>
			<a href="javascript:" onclick="deleteConfirm()">삭제</a>
				<script>
					function deleteConfirm() {
						const allowsDelete = confirm("이 글을 삭제하시겠습니까?");
						if(allowsDelete) {
							location.href = "delete?num=${dto.num}";
						}
					}
				</script>
		</c:if>
	</div>
</body>
</html>