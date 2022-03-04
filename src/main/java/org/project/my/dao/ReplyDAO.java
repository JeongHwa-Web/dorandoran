package org.project.my.dao;

import java.util.List;

import org.project.my.dto.Reply;

public interface ReplyDAO {
	void insert(Reply reply);
	void update(Reply reply);
	void delete(int replynum);
	Reply selectOne(int replynum);
	List<Reply> selectList(int boardnum);
	int updateRestep(Reply reply);
}
