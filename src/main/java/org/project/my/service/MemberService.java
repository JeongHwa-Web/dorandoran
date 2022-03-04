package org.project.my.service;

import java.util.Map;

import org.project.my.dto.Member;

public interface MemberService {

	Map<String, Object> insert(Member member) throws Exception;
	Map<String, Object> update(Member member, String newPasswd) throws Exception;
	Map<String, Object> delete(String userid);
	Member selectOne(String userid);
	int idCheck(String userid);
	int nickCheck(String nickname);
	
}
