package org.project.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.project.my.dto.Reply;
import org.project.my.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/list/{boardnum}")
	public List<Reply> replyList(@PathVariable int boardnum){
		List<Reply> replyList = replyService.selectList(boardnum);
		return replyList;
	}
	
	@PostMapping("/")
	public void replyAdd(@RequestBody Reply reply, HttpServletRequest request, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		reply.setUserid(userid);
		String ip = request.getRemoteAddr();
		reply.setIp(ip);
		replyService.insert(reply);
	}
	
	@PutMapping("/")
	public void replyModify(@RequestBody Reply reply, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		reply.setIp(ip);
		replyService.update(reply);
	}
	
	@DeleteMapping("{replynum}")
	public void replyRemove(@PathVariable int replynum) {
		replyService.delete(replynum);
	}
	
	@GetMapping("/selectReply/{replynum}")
	public Reply selectReply(@PathVariable int replynum) {
		return replyService.selectOne(replynum);
	}
}
