package org.project.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.project.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void insert(Member member) {
		sqlSession.insert("org.project.my.MemberMapper.insert", member);
	}

	@Override
	public void update(Member member) {
		sqlSession.update("org.project.my.MemberMapper.update", member);
	}

	@Override
	public void delete(String userid) {
		sqlSession.delete("org.project.my.MemberMapper.delete", userid);
	}

	@Override
	public Member selectOne(String userid) {
		return sqlSession.selectOne("org.project.my.MemberMapper.selectOne", userid);
	}

	@Override
	public List<Member> selectList() {
		return sqlSession.selectList("org.project.my.MemberMapper.selectList");
	}

	@Override
	public int idCheck(String userid) {
		return sqlSession.selectOne("org.project.my.MemberMapper.idCheck", userid);
	}

	@Override
	public int nickCheck(String nickname) {
		return sqlSession.selectOne("org.project.my.MemberMapper.nickCheck", nickname);
	}

}
