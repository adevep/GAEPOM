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

import com.gaepom.domain.GUser;
import com.gaepom.service.GUserService;

@SessionAttributes("guser") 
@Controller
public class LoginController {
	
	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}
	
	@Autowired
	private UserServiceImp UserServiceimp;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public String login(@ModelAttribute("guser") User user, Model model) {
		
		User finduser = UserServiceimp.getUser(user);
				
		if (finduser != null && finduser.getPassword().equals(user.getPassword())) {
			model.addAttribute("guser", finduser);
			System.out.println(model.getAttribute("guser"));
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
