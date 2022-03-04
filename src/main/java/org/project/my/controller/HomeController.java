package org.project.my.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.my.service.LoginService;
import org.project.my.service.MemberService;
import org.project.my.service.NaverLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class HomeController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private NaverLoginService naverLoginService;
	@Autowired
	private MemberService memverService;
	
	@GetMapping("login")
	public String login(HttpSession session, Model model) throws Exception {
		Map<String, String> result = naverLoginService.getApiUrl();
		String apiURL = result.get("apiURL");
		String state = result.get("state");
		session.setAttribute("state", state);
		model.addAttribute("apiURL", apiURL);
		return "login";
	}
	
	@PostMapping("login")
	public String login(String userid, String passwd, boolean remember, RedirectAttributes rattr, 
		HttpSession session, HttpServletResponse response) {
			Map<String, Object>result = loginService.login(userid, passwd);
			int code = (int)result.get("code");
			String msg = (String)result.get("msg");
			rattr.addFlashAttribute("msg",msg);
			Cookie cookie = new Cookie("userid",userid);
			if(code==0) {
				if(remember) {
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*3);
					response.addCookie(cookie);
				}else {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				String nickname = memverService.selectOne(userid).getNickname();
				session.setAttribute("nickname", nickname);
				session.setAttribute("userid", userid);
				session.setMaxInactiveInterval(60*60*3);
				return "redirect:board/";
			}else {
				return "redirect:login";
			}
	}
	
	@GetMapping("naver_callback")
	public String naver_callback(String code, String state, RedirectAttributes rattr, HttpSession session) throws Exception {
		Map<String, String>userInfo = naverLoginService.getTokenUserInfo(code, state);
		Map<String, Object> result = naverLoginService.insert(userInfo);
		int ncode = (int) result.get("code");
		String msg = (String) result.get("msg");
		rattr.addFlashAttribute("msg", msg);
		if(ncode == 2) {//일반회원경우
			return "redirect:login";
		}
		String nickname = userInfo.get("nickname");
		String userid = userInfo.get("userid");
		session.setAttribute("nickname", nickname);
		session.setAttribute("userid", userid);
		return "redirect:board/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:board/";
	}
	
}
