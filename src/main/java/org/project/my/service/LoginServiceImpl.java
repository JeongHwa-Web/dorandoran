package org.project.my.service;

import java.util.HashMap;
import java.util.Map;

import org.project.my.dao.MemberDAO;
import org.project.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public Map<String, Object> login(String userid, String passwd) {
		Map<String, Object> result = new HashMap<>();
		Member member = memberDAO.selectOne(userid);
		//아이디 체크
		if(member==null) {
			result.put("code", 1);
			result.put("msg", "아이디가 존재하지 않습니다.");
			return result;
		}
		//비밀번호 체크
		boolean pwMatch = bCryptPasswordEncoder.matches(passwd, member.getPasswd());
		if(!pwMatch) {
			result.put("code", 2);
			result.put("msg", "비밀번호가 일치하지 않습니다.");
			return result;
		}
		//간편가입 체크
		int simplejoin = member.getSimplejoin();
		if(simplejoin==1) {
			result.put("code", 3);
			result.put("msg", "네이버회원입니다.");
			return result;
		}
		result.put("code", 0);
		result.put("msg", "로그인되었습니다.");
		return result;
	}

}
