package org.project.my.service;

import java.util.Map;

import org.project.my.dto.Review;

public interface ReviewService {
	Map<String, Object> insert(Review review);

	Review selectOne(int boardnum);

	Map<String, Object> update(Review review);

}
