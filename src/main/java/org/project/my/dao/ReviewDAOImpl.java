package org.project.my.dao;

import org.apache.ibatis.session.SqlSession;
import org.project.my.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDAOImpl implements ReviewDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Review selectOne(int boardnum) {
		return sqlSession.selectOne("org.project.my.ReviewMapper.selectOne", boardnum);
	}

	@Override
	public void insert(Review review) {
		sqlSession.insert("org.project.my.ReviewMapper.insert", review);
	}

	@Override
	public void update(Review review) {
		sqlSession.update("org.project.my.ReviewMapper.update", review);
	}

	@Override
	public int gradeAvg(String wruserid) {
		return sqlSession.selectOne("org.project.my.ReviewMapper.gradeAvg", wruserid);
	}
	
}
