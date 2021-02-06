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
import com.gaepom.service.UserServiceImp;

@SessionAttributes("user") 
@Controller
public class LoginController {
	
	@Autowired
	private UserServiceImp UserServiceimp;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model) {
		
		User finduser = UserServiceimp.getUser(user);
		
		System.out.println("해당 계정 조회성공");
		
		if (finduser != null && finduser.getPassword().equals(user.getPassword())) {
			model.addAttribute("user", finduser);
			System.out.println("아이디 비번 일치");
			System.out.println(model.getAttribute("user"));
			return "getuser";
			
		} else {
			return "redirect:index.html";
		}
	}

	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		
		return "redirect:index.html";
	}

}
