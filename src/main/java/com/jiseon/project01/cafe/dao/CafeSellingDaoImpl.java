package com.jiseon.project01.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiseon.project01.cafe.dto.CafeSellingDto;

@Repository
public class CafeSellingDaoImpl implements CafeSellingDao{
	
	@Autowired
	private SqlSession session;

	
	@Override
	public void insert(CafeSellingDto dto) {
		session.insert("cafeSelling.insert", dto);
		
	}

	@Override
	public List<CafeSellingDto> getList(CafeSellingDto dto) {
		return session.selectList("cafeSelling.getList", dto);
	}

	@Override
	public int getCount(CafeSellingDto dto) {
		return session.selectOne("cafeSelling.getCount");
	}

	@Override
	public CafeSellingDto getData(int num) {		
		return session.selectOne("cafeSelling.getData", num);
	}

	@Override
	public CafeSellingDto getDataWKey(CafeSellingDto dto) {
		return session.selectOne("cafeSelling.getDataWKey", dto);
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafeSelling.addViewCount", num);
		
	}

	@Override
	public void update(CafeSellingDto dto) {
		session.update("cafeSelling.update", dto);
		
	}

	@Override
	public void delete(int num) {
		session.delete("cafeSelling.delete", num);
		
	}

}
