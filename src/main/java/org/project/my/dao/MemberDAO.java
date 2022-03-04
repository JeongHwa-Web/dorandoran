package org.project.my.dao;

import java.util.List;

import org.project.my.dto.Member;

public interface MemberDAO {

	void insert(Member member);
	void update(Member member);
	void delete(String userid);
	Member selectOne(String userid);
	List<Member> selectList();
	int idCheck(String userid);
	int nickCheck(String nickname);

}
