<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/forum/insertform.jsp</title>
<style>
	textarea{
		width: 768px;
		height: 300px;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>자유게시판</h3>
		<form action="insert" method = "post" enctype="multipart/form-data">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" />
			</div>
			<div>
				<label for="image">첨부 이미지</label>
				<input type="file" id="image" name="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG" />
			</div>
			<img src="" alt="이미지 미리보기" id="preview"/>
			<div>
				<label for="content">내용</label>
				<textarea row="10" id="content" name="content"></textarea>
			</div>
			<button type="submit">저장</button>
		</form>
	</div>
	
	<script>
		document.querySelector("#image").addEventListener("change", (e)=> {
			//선택된 파일 배열 객체를 얻어낸다. (files는 배열이다)
			const files = e.target.files;
			console.log(files);
			//만일 파일 데이터가 존재한다면
			if(files.length > 0) {
				//파일로부터 데이터를 읽어들일 객체 생성
				const reader = new FileReader();
				//로딩이 완료 되었을 때(파일 데이터를 모두 읽었을 때) 실행할 함수 등록
				reader.onload = (event) => {
					//읽은 파일 데이터 얻어내기
					const data = event.target.result;
					document.querySelector("#preview").setAttribute("src", data);
				};
				//파일 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);
				//dataURL : 이미지 파일을 문자열로 나타내고 나중에 디코딩을 해주면 다시 이미지를 불러올 수 있음.
			};
		});
	</script>
</body>
</html>