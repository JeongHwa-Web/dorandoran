package org.project.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.my.dto.Board;
import org.project.my.dto.Review;
import org.project.my.service.BoardService;
import org.project.my.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BoardService boardService;
	
	@GetMapping("buyList")
	public String ReviewBuyList(Model model, HttpSession session) {
		String truserid = (String) session.getAttribute("userid");
		List<Board> buyList = boardService.buyList(truserid);
		model.addAttribute("buyList", buyList);
		return "/review/buyList";
	}
	
	@GetMapping("sellList")
	public String ReviewSellList(Model model, HttpSession session) {
		String wruserid = (String) session.getAttribute("userid");
		List<Board> sellList = boardService.sellList(wruserid);
		model.addAttribute("sellList", sellList);
		return "/review/sellList";
	}
	
	@GetMapping("reviewAdd")
	public String reviewAdd(int boardnum, Model model) {
		model.addAttribute("boardnum", boardnum);
		return "/review/reviewAdd";
	}
	
	@PostMapping("reviewAdd")
	public String reviewAdd(Review review, RedirectAttributes rattr){
		Map<String, Object> result = reviewService.insert(review);
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:buyList";
	}
	
	@GetMapping("reviewModify")
	public String reviewModify(int boardnum, Model model) {
		Review review = reviewService.selectOne(boardnum);
		model.addAttribute("review", review);
		return "/review/reviewModify";
	}
	
	@PostMapping("reviewModify")
	public String reviewModify(Review review, RedirectAttributes rattr) {
		Map<String, Object> result = reviewService.update(review);
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:buyList";
	}
	
	@GetMapping("reviewDetail")
	public String reviewDetail(int boardnum, Model model) {
		Review review = reviewService.selectOne(boardnum);
		model.addAttribute("review", review);
		return "/review/reviewDetail";
	}
	
}
