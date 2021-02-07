package com.gaepom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gaepom.domain.User;
import com.gaepom.service.UserServiceImpl_bak;

@SessionAttributes("guser") 
@Controller
public class LoginController {
	
	@Autowired
	private UserServiceImpl_bak UserServiceimp;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model) {
		
		User finduser = UserServiceimp.getUser(user);
		
		System.out.println("해당 계정 조회성공");
		
		if (finduser != null && finduser.getPassword().equals(user.getPassword())) {
			model.addAttribute("guser", finduser);
			System.out.println("로그인 성공");
			System.out.println(model.getAttribute("guser"));
			return "getuser";
			
		} else {
			System.out.println("로그인 실패");
			return "redirect:index.html";
		}
	}

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		
		return "redirect:index.html";
	}

}
