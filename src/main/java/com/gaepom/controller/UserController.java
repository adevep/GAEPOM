package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gaepom.domain.User;
import com.gaepom.service.UserServiceImp;


@SessionAttributes({"guser",})
@Controller
public class UserController {

	@Autowired
	private UserServiceImp Userserviceimp;
	

	@GetMapping("/getuserlist")
	public String getUserList(@ModelAttribute("guser") User user, Model model) {
		System.out.println(11);
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		System.out.println(22);
		List<User> UserList = Userserviceimp.getUserList(user);
		System.out.println(33);
		model.addAttribute("userlist", UserList);
		System.out.println(44);
		
		return "getuserlist";
	}



	
	@PostMapping("/insertuser")
	public String insertUser(@ModelAttribute User user) {
		if (user.getUserId() == null) {		
			return "redirect:index.html";
		}
		
		Userserviceimp.insertUser(user);
		System.out.println("-------insertuser----------------------");
		return "redirect:login.html";
	}

	@GetMapping("/getanouser")
	public String getUser(@ModelAttribute("guser") User user, @RequestParam("anouserid") String anouserid, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		model.addAttribute("anouser", Userserviceimp.findUserByUserId(anouserid));
		return "getuser";
	}
	
	@GetMapping("/updateuserreq")
	public String updateUserReq(@ModelAttribute("guser") User user, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		return "updateuser";
	}

	@PostMapping("/updateuser")
	public String updateUser(@ModelAttribute("guser") User user, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		Userserviceimp.updateUser(user);
		return "getuser";
	}

	@GetMapping("/deleteuser")
	public String deleteUser(@ModelAttribute("guser") User user, SessionStatus status) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		Userserviceimp.deleteUser(user);
		status.setComplete();
		return "redirect:index.html";
	}

}
