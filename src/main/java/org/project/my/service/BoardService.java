package org.project.my.service;

import java.util.List;
import java.util.Map;

import org.project.my.dto.Board;
import org.project.my.dto.Page;

public interface BoardService {
	Map<String, Object>insert(Board board) throws Exception;
	Map<String, Object>update(Board board, List<Integer>removeFile) throws Exception;
	Map<String, Object>delete(int boardnum);
	Map<String, Object> selectOne(int boardnum);
	Map<String, Object> tradeSuccess(Board board);
	List<Board> selectList(Page page);
	List<Board> buyList(String truserid);
	List<Board> sellList(String wruserid);
	void updateReadCnt(int boardnum);
}
