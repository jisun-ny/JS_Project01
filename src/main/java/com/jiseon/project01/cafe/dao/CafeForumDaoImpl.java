package com.jiseon.project01.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiseon.project01.cafe.dto.CafeForumDto;

@Repository
public class CafeForumDaoImpl implements CafeForumDao{
	
	@Autowired
	private SqlSession session;

	
	@Override
	public void insert(CafeForumDto dto) {
		session.insert("cafeForum.insert", dto);
		
	}

	@Override
	public List<CafeForumDto> getList(CafeForumDto dto) {
		return session.selectList("cafeForum.getList", dto);
	}

	@Override
	public int getCount(CafeForumDto dto) {
		return session.selectOne("cafeForum.getCount");
	}

	@Override
	public CafeForumDto getData(int num) {		
		return session.selectOne("cafeForum.getData", num);
	}

	@Override
	public CafeForumDto getDataWKey(CafeForumDto dto) {
		return session.selectOne("cafeForum.getDataWKey", dto);
	}

	@Override
	public void addViewCount(int num) {
		session.update("cafeForum.addViewCount", num);
		
	}

	@Override
	public void update(CafeForumDto dto) {
		session.update("cafeForum.update", dto);
		
	}

	@Override
	public void delete(int num) {
		session.delete("cafeForum.delete", num);
		
	}

}
