package com.gaepom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;
import com.gaepom.service.UserService;
import com.gaepom.service.UserServiceImp;

@SessionAttributes({ "guser", })
@Controller
public class UserController {

	@Autowired
	private UserService userservice;

	@GetMapping("/getuserlist")
	public String getUserList(@ModelAttribute("guser") User user, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		List<User> UserList = userservice.getUserList(user);
		model.addAttribute("userlist", UserList);

		return "getuserlist";
	}

	@GetMapping("/getuserlistaxios")
	public @ResponseBody List<User> getUserListaxios() {
	//	String url = null;
		
		System.out.println("axios 전달");
		
		try {
			List<User> userList = userservice.getUserList2();
	//	url = "viewindex";
			System.out.println("list 반환 성공");
			return userList;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/insertuser")
	public String insertUser(@ModelAttribute User user, @RequestParam("filename") MultipartFile mfile) {
		if (user.getUserId() == null) {
			return "redirect:index.html";
		}
		if (!mfile.isEmpty()) {
			userservice.insertUser(user, mfile);
		} else {
			userservice.insertUserNoimg(user);
		}
		return "redirect:login.html";
	}

	@GetMapping("/getanouser")
	public String getUser(@ModelAttribute("guser") User user, @RequestParam("anouserid") String anouserid,
			Model model) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		model.addAttribute("anouser", userservice.findUserByUserId(anouserid));
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
	public String updateUser(@ModelAttribute("guser") User user, @RequestParam("filename") MultipartFile mfile,
			Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		if (!mfile.isEmpty()) {
			userservice.updateUser(user, mfile);
		} else {
			userservice.updateUserNoimg(user);
		}
		// return "getuser";
		return "redirect:index.html";
	}

	@GetMapping("/deleteuser")
	public String deleteUser(@ModelAttribute("guser") User user, SessionStatus status) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		userservice.deleteUser(user);
		status.setComplete();
		return "redirect:index.html";
	}

}