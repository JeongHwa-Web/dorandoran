package org.project.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.my.dto.Board;
import org.project.my.dto.Page;
import org.project.my.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/")
	public String index(Page page, Model model) {
		List<Board>blist = boardService.selectList(page);
		model.addAttribute("blist", blist);
		return "/index";
	}
	
	@GetMapping("add")
	public String add() {
		return "/board/add";
	}
	
	@PostMapping("add")
	public String add(Board board, RedirectAttributes rattr, HttpServletRequest request, HttpSession session) throws Exception {
		board.setIp(request.getRemoteAddr());
		board.setWruserid((String)session.getAttribute("userid"));
		Map<String, Object>result = boardService.insert(board);
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:/board/";
	}
	
	@GetMapping("detail")
	public String detail(int boardnum, Model model) {
		Map<String, Object> result = boardService.selectOne(boardnum);
		boardService.updateReadCnt(boardnum);
		model.addAttribute("result", result);
		return "/board/detail";
	}
	
	@GetMapping("modify")
	public String modify(int boardnum, Model model) {
		Map<String, Object> result = boardService.selectOne(boardnum);
		model.addAttribute("result", result);
		return "/board/modify";
	}
	
	@PostMapping("modify")
	public String modify(Board board, @RequestParam(required = false) List<Integer>removeFile, HttpServletRequest request, RedirectAttributes rattr) throws Exception {
		board.setIp(request.getRemoteAddr());
		Map<String, Object> result = boardService.update(board, removeFile);
		int boardnum = board.getBoardnum();
		String msg = (String)result.get("msg");
		rattr.addAttribute("boardnum", boardnum);
		rattr.addFlashAttribute("msg", msg);
		return "redirect:detail";
	}
	
	@GetMapping("tradeSuccess")
	public String tradeSuccess(Board board, RedirectAttributes rattr) {
		Map<String, Object> result = boardService.tradeSuccess(board);
		int boardnum = board.getBoardnum();
		rattr.addAttribute("boardnum", boardnum);
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:detail";
	}
	
	@GetMapping("remove")
	public String remove(int boardnum, RedirectAttributes rattr) {
		Map<String, Object> result = boardService.delete(boardnum);
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:/board/";
	}
	
}
