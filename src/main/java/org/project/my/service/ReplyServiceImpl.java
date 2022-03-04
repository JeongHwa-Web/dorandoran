package org.project.my.service;

import java.util.List;

import org.project.my.dao.ReplyDAO;
import org.project.my.dto.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public void insert(Reply reply) {
		replyDAO.insert(reply);
	}

	@Override
	public void update(Reply reply) {
		replyDAO.update(reply);
		
	}

	@Override
	public void delete(int replynum) {
		replyDAO.delete(replynum);		
	}

	@Override
	public List<Reply> selectList(int boardnum) {
		return replyDAO.selectList(boardnum);
	}

	@Override
	public Reply selectOne(int replynum) {
		return replyDAO.selectOne(replynum);
	}
	
}
