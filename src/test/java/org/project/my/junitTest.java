package org.project.my;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.project.my.dao.MemberDAO;
import org.project.my.dao.ReplyDAO;
import org.project.my.dto.Member;
import org.project.my.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/servlet-context.xml"})
public class junitTest {
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	DataSource dataSource;
	@Autowired
	ReplyDAO replyDAO;
	
	Member member = new Member();
	@Test
	public void testDataSource() {
		try {
			Connection conn = dataSource.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void insertTest() {
		member.setUserid("hjh");
		member.setPasswd("1111");
		member.setNickname("리몽");
		member.setEmail("hjh5446");
		member.setName("홍정화");
		member.setCallnum("010-1111-1111");
		memberDAO.insert(member);
	}
	
	@Test
	public void idCheckTest() {
		int cnt = memberDAO.idCheck("hjh");
		System.out.println(cnt);
	}
	
	@Test
	public void replyInsertTest() {
		Reply reply = new Reply();
		reply.setBoardnum(1);
		reply.setContent("내용");
		reply.setUserid("hong");
		replyDAO.insert(reply);
		System.out.println(reply);
	}

}
