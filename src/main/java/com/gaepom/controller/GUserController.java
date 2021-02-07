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

import com.gaepom.domain.GUser;
import com.gaepom.service.GUserService;
import com.gaepom.service.GUserServiceImp;

@SessionAttributes({"guser",})
@Controller
public class GUserController {

	@Autowired
	private GUserServiceImp GUserserviceimp;

	@GetMapping("/getuserlist")
	public String getUserList(@ModelAttribute("guser") User user, Model model) {
		
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		List<User> UserList = Userserviceimp.getUserList(user);

		model.addAttribute("userlist", UserList);
		return "getuserlist";
	}



	
	@PostMapping("/insertuser")
	public String insertUser(@ModelAttribute User user) {
		if (user.getUserId() == null) {		
			return "redirect:index.html";
		}
		System.out.println(1);
		GUserserviceimp.insertGUser(guser);
		System.out.println("-------insertguser----------------------");
		return "redirect:login";
	}

	@GetMapping("/getanouser")
	public String getUser(@ModelAttribute("guser") User user, @RequestParam("anouserid") String anouserid, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		model.addAttribute("anouser", Userserviceimp.findUserByUserId(anouserid));
		return "getuser";
	}
//
//	@PostMapping("/updateUser")
//	public String updateUser(@ModelAttribute("user") user user, User User) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}
//
//		GUserserviceimp.updateUser(User);
//		return "forward:getUserList";
//	}
//
//	@GetMapping("/deleteUser")
//	public String deleteUser(@ModelAttribute("user") user user, User User) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}
//
//		GUserserviceimp.deleteUser(User);
//		return "forward:getUserList";
//	}

}
