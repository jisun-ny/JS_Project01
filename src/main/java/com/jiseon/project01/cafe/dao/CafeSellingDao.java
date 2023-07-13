package com.jiseon.project01.cafe.dao;

import java.util.List;

import com.jiseon.project01.cafe.dto.CafeSellingDto;

public interface CafeSellingDao {
	//글 추가
	public void insert(CafeSellingDto dto);
	//글 목록 얻어오기
	public List<CafeSellingDto> getList(CafeSellingDto dto);
	//글의 갯수
	public int getCount(CafeSellingDto dto);
	//글 정보 얻어오기
	public CafeSellingDto getData(int num);
	//키워드를 활용한 글 정보 얻어오기
	public CafeSellingDto getDataWKey (CafeSellingDto dto);
	//조회수 증가시키기	
	public void addViewCount(int num);
	//글 수정
	public void update(CafeSellingDto dto);
	//글 삭제
	public void delete(int num);
	
}
