package com.jiseon.project01.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiseon.project01.cafe.dto.CafeWantedDto;

@Repository
public class CafeWantedDaoImpl implements CafeWantedDao{
	
	@Autowired
	private SqlSession session;

	
	@Override
	public void insert(CafeWantedDto dto) {
		session.insert("cafeWanted.insert", dto);
		
	}

	@Override
	public List<CafeWantedDto> getList(CafeWantedDto dto) {
		return session.selectList("cafeWanted.getList", dto);
	}

	@Override
	public int getCount(CafeWantedDto dto) {
		return session.selectOne("cafeWanted.getCount");
	}

	@Override
	public CafeWantedDto getData(int num) {		
		return session.selectOne("cafeWanted.getData", num);
	}

	@Override
	public CafeWantedDto getDataWKey(CafeWantedDto dto) {
		return session.selectOne("cafeWanted.getDataWKey", dto);
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafeWanted.addViewCount", num);
		
	}

	@Override
	public void update(CafeWantedDto dto) {
		session.update("cafeWanted.update", dto);
		
	}

	@Override
	public void delete(int num) {
		session.delete("cafeWanted.delete", num);
		
	}

}
