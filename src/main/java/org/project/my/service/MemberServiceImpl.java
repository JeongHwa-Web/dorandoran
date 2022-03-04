package org.project.my.service;

import java.util.HashMap;
import java.util.Map;

import org.project.my.dao.MemberDAO;
import org.project.my.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	FileService fileService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public Map<String, Object> insert(Member member) throws Exception {
		Map<String, Object>result = new HashMap<>();
		//프로필사진업로드
		String filename = fileService.fileUpLoad(member.getFile());
		member.setFilename(filename);
		//비밀번호 암호화
		String scurepw = bCryptPasswordEncoder.encode(member.getPasswd());
		member.setPasswd(scurepw);
		//db등록
		memberDAO.insert(member);
		result.put("code", 0);
		result.put("msg", "가입완료");
		return result;
	}

	@Override
	public Map<String, Object> delete(String userid) {
		Map<String, Object>result = new HashMap<>();
		memberDAO.delete(userid);
		result.put("code", 0);
		result.put("msg", "삭제되었습니다.");
		return result;
	}
	
	@Override
	public Member selectOne(String userid) {
		return memberDAO.selectOne(userid);
	}

	@Override
	@Transactional
	public Map<String, Object> update(Member member, String newPasswd) throws Exception {
		Map<String, Object> result = new HashMap<>();
		Member dbMember = memberDAO.selectOne(member.getUserid());
		//비밀번호확인
		if(dbMember.getSimplejoin() == 0) {	
			boolean pwMatch = bCryptPasswordEncoder.matches(member.getPasswd(), dbMember.getPasswd());
			if(!pwMatch) {
				result.put("code", 2);
				result.put("msg", "현재 비밀번호가 일치하지 않습니다.");
				return result;
			}
		}
		//비밀번호 변경
		if(!newPasswd.equals("")) {			
			member.setPasswd(bCryptPasswordEncoder.encode(newPasswd));
		}else {
			member.setPasswd(bCryptPasswordEncoder.encode(member.getPasswd()));
		}
		
		//프로필사진저장
		String filename = fileService.fileUpLoad(member.getFile());
		member.setFilename(filename);
		
		//db저장
		memberDAO.update(member);
		result.put("code", 0);
		result.put("msg", "회원정보가 변경되었습니다.");
		return result;
	}
	
	@Override
	public int idCheck(String userid) {
		return memberDAO.idCheck(userid);
	}
	
	@Override
	public int nickCheck(String nickname) {
		return memberDAO.nickCheck(nickname);
	}


	
	
}
