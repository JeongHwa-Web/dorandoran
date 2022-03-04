package org.project.my.dao;

import java.util.List;

import org.project.my.dto.BoardFile;

public interface BoardFileDAO {
	void insert(BoardFile boardFile);
	List<BoardFile> selectList(int boardnum);
	void delete(int photonum);
}
