package org.project.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.project.my.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void insert(Reply reply) {
		sqlSession.insert("org.project.my.ReplyMapper.insert", reply);
	}

	@Override
	public void update(Reply reply) {
		sqlSession.update("org.project.my.ReplyMapper.update", reply);
	}

	@Override
	public void delete(int replynum) {
		sqlSession.delete("org.project.my.ReplyMapper.delete", replynum);
	}

	@Override
	public Reply selectOne(int replynum) {
		return sqlSession.selectOne("org.project.my.ReplyMapper.selectOne", replynum);
	}

	@Override
	public List<Reply> selectList(int boardnum) {
		return sqlSession.selectList("org.project.my.ReplyMapper.selectList", boardnum);
	}
	
	@Override
	public int updateRestep(Reply reply) {
		// TODO Auto-generated method stub
		return sqlSession.update("org.project.my.ReplyMapper.updateRestep", reply);
	}
}
