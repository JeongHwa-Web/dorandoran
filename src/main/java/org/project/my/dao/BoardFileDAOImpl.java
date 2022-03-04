package org.project.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.project.my.dto.BoardFile;
import org.project.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFileDAOImpl implements BoardFileDAO{
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void insert(BoardFile boardFile) {
		sqlSession.insert("org.project.my.BoardFileMapper.insert", boardFile);
	}
	@Override
	public List<BoardFile> selectList(int boardnum) {
		return sqlSession.selectList("org.project.my.BoardFileMapper.selectList", boardnum);
	}
	@Override
	public void delete(int photonum) {
		sqlSession.delete("org.project.my.BoardFileMapper.delete", photonum);
		
	}

}
