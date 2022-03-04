package org.project.my.service;

import java.util.HashMap;
import java.util.Map;

import org.project.my.dao.BoardDAO;
import org.project.my.dao.ReviewDAO;
import org.project.my.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDAO reviewDAO;
	@Autowired
	private BoardDAO boardDAO;


	@Override
	public Review selectOne(int boardnum) {
		return reviewDAO.selectOne(boardnum);
	}

	@Override
	@Transactional
	public Map<String, Object> insert(Review review) {
		int boardnum = review.getBoardnum();
		reviewDAO.insert(review);
		boardDAO.reviewCheck(boardnum);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "리뷰가 등록되었습니다.");
		return result;
	}

	@Override
	public Map<String, Object> update(Review review) {
		reviewDAO.update(review);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 0);
		result.put("msg", "리뷰가 수정되었습니다.");
		return result;
	}
	
}
