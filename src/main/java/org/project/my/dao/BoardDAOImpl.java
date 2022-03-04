package org.project.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.project.my.dto.Board;
import org.project.my.dto.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(Board board) {
		sqlSession.insert("org.project.my.BoardMapper.insert", board);
	}

	@Override
	public void update(Board board) {
		sqlSession.update("org.project.my.BoardMapper.update", board);
	}

	@Override
	public void delete(int boardnum) {
		sqlSession.delete("org.project.my.BoardMapper.delete", boardnum);
	}

	@Override
	public Board selectOne(int boardnum) {
		return sqlSession.selectOne("org.project.my.BoardMapper.selectOne", boardnum);
	}

	@Override
	public List<Board> selectList(Page page) {
		return sqlSession.selectList("org.project.my.BoardMapper.selectList", page);
	}
	
	@Override
	public int selectTotCnt(Page page) {
		return sqlSession.selectOne("org.project.my.BoardMapper.selectTotCnt", page);
	}

	@Override
	public void tradeSuccess(Board board) {
		sqlSession.update("org.project.my.BoardMapper.tradeSuccess", board);
	}

	@Override
	public List<Board> buyList(String truserid) {
		return sqlSession.selectList("org.project.my.BoardMapper.buyList", truserid);
	}
	
	@Override
	public List<Board> sellList(String wruserid) {
		return sqlSession.selectList("org.project.my.BoardMapper.sellList", wruserid);
	}

	@Override
	public void reviewCheck(int boardnum) {
		sqlSession.update("org.project.my.BoardMapper.reviewCheck", boardnum);
	}

	@Override
	public void updateReadCnt(int boardnum) {
		sqlSession.update("org.project.my.BoardMapper.updateReadCnt", boardnum);
	}
}
