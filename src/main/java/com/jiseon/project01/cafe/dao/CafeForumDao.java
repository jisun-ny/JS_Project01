package com.jiseon.project01.cafe.dao;

import java.util.List;

import com.jiseon.project01.cafe.dto.CafeForumDto;

public interface CafeForumDao {
	//글 추가
	public void insert(CafeForumDto dto);
	//글 목록 얻어오기
	public List<CafeForumDto> getList(CafeForumDto dto);
	//글의 갯수
	public int getCount(CafeForumDto dto);
	//글 정보 얻어오기
	public CafeForumDto getData(int num);
	//키워드를 활용한 글 정보 얻어오기
	public CafeForumDto getDataWKey (CafeForumDto dto);
	//조회수 증가시키기	
	public void addViewCount(int num);
	//글 수정
	public void update(CafeForumDto dto);
	//글 삭제
	public void delete(int num);
	
}
