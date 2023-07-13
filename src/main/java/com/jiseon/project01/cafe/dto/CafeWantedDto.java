package com.jiseon.project01.cafe.dto;

import org.springframework.web.multipart.MultipartFile;

public class CafeWantedDto {
		private int num;
		private String writer;
		private String title;
		private String imagePath;
		private String Content;
		private int viewCount;
		private String regdate;

		//이미지 파일 업로드 처리를 위한 필드
		private MultipartFile image;
		// 페이징 처리
		private int startRowNum;
		private int endRowNum;
		private int prevNum;
		private int nextNum;

		public CafeWantedDto() {
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getWriter() {
			return writer;
		}

		public void setWriter(String writer) {
			this.writer = writer;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public String getContent() {
			return Content;
		}

		public void setContent(String content) {
			Content = content;
		}

		public int getViewCount() {
			return viewCount;
		}

		public void setViewCount(int viewCount) {
			this.viewCount = viewCount;
		}

		public String getRegdate() {
			return regdate;
		}

		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}

		public MultipartFile getImage() {
			return image;
		}

		public void setImage(MultipartFile image) {
			this.image = image;
		}

		public int getStartRowNum() {
			return startRowNum;
		}

		public void setStartRowNum(int startRowNum) {
			this.startRowNum = startRowNum;
		}

		public int getEndRowNum() {
			return endRowNum;
		}

		public void setEndRowNum(int endRowNum) {
			this.endRowNum = endRowNum;
		}

		public int getPrevNum() {
			return prevNum;
		}

		public void setPrevNum(int prevNum) {
			this.prevNum = prevNum;
		}

		public int getNextNum() {
			return nextNum;
		}

		public void setNextNum(int nextNum) {
			this.nextNum = nextNum;
		}

		

	}


