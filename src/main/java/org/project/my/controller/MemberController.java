package org.project.my.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.project.my.dto.Member;
import org.project.my.service.LoginService;
import org.project.my.service.MemberService;
import org.project.my.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/join")
	public String join() {
		return "/member/join";
	}
	
	@PostMapping("/join")
	public String join(Member member, RedirectAttributes rattr) throws Exception {
		Map<String, Object>result =  memberService.insert(member);
		String msg = (String)result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:/login";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(String userid) {
		int cnt = memberService.idCheck(userid);
		return cnt;
	}
	
	@PostMapping("/nickCheck")
	@ResponseBody
	public int nickCheck(String nickname) {
		int cnt = memberService.nickCheck(nickname);
		return cnt;
	}
	
	@GetMapping("/detail")
	public String detail(String userid, Model model) {
		Member member = memberService.selectOne(userid);
		model.addAttribute("member", member);
		return "/member/detail";
	}
	
	@PostMapping("/modify")
	public String modify(Member member, String newPasswd, RedirectAttributes rattr) throws Exception {
		Map<String, Object> result = memberService.update(member, newPasswd);
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:/board/";
	}
	
	@GetMapping("/remove")
	public String remove(String userid, RedirectAttributes rattr, HttpSession session) {
		Map<String, Object> result = memberService.delete(userid);
		session.invalidate();
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		return "redirect:/board/";
	}
	
	
	
}
