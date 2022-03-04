package org.project.my.dao;

import java.util.List;

import org.project.my.dto.Board;
import org.project.my.dto.Page;

public interface BoardDAO {
	void insert(Board board);
	void update(Board board);
	void delete(int boardnum);
	Board selectOne(int boardnum);
	int selectTotCnt(Page page);
	void tradeSuccess(Board board);
	List<Board> selectList(Page page);
	List<Board> buyList(String truserid);
	List<Board> sellList(String wruserid);
	void reviewCheck(int boardnum);
	void updateReadCnt(int boardnum);
}
