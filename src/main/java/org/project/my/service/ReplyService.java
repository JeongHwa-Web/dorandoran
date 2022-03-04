package org.project.my.service;

import java.util.List;

import org.project.my.dto.Reply;

public interface ReplyService {
	void insert(Reply reply);
	void update(Reply reply);
	void delete(int replynum);
	List<Reply> selectList(int boardnum);
	Reply selectOne(int replynum);
}
