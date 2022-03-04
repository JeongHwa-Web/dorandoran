package org.project.my.dao;

import org.project.my.dto.Review;

public interface ReviewDAO {
	Review selectOne(int boardnum);

	void insert(Review review);

	void update(Review review);
	
	int gradeAvg(String wruserid);
}
