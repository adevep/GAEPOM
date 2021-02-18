package com.gaepom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;
import com.gaepom.exception.UserNotFoundException;
import com.gaepom.service.UserService;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

	@Autowired
	private UserService userservice;
	

//	@GetMapping("/getuserlist")
//	public String getUserList(@RequestParam String userid, @RequestParam String password, @RequestParam String name, @RequestParam int age,
//			@RequestParam String email, @RequestParam String phoneNumm, @RequestParam String address, @RequestParam String stack) {
//			return "redirect:login.html";
//		}
//		List<User> UserList = Userservice.getUserList(user);
//		model.addAttribute("userlist", UserList);
//		
//		return "getuserlist";
//	}
	
	@PostMapping("/insertuser")
	public User insertUser(User user, @RequestParam("file") MultipartFile mfile) {
		
		if (user.getUserId() == null || user.getPassword() == null) {		
			return null;
		} else {
			if(!mfile.isEmpty()) {
				userservice.insertUser(user, mfile);
			} else {
				userservice.insertUserNoimg(user);
			}
			return user;
		}			
	}

//	@GetMapping("/getanouser")
//	public String getUser(@ModelAttribute("guser") User user, @RequestParam("anouserid") String anouserid, Model model) {
//		if (user.getUserId() == null) {
//			return "redirect:login.html";
//		}
//
//		model.addAttribute("anouser", userservice.findUserByUserId(anouserid));
//		return "getuser";
//	}

	@PostMapping("/updateuser")
	public User updateUser(User user, @RequestParam(value = "file", required = false) MultipartFile mfile) {
		if (user.getUserId() == null || user.getPassword() == null) {		
			return null;
		} else {
			User updatedUser = null;
			if(mfile != null) {
				updatedUser = userservice.updateUser(user, mfile);
			} else {
				updatedUser = userservice.updateUserNoimg(user);
			}
			return updatedUser;
		}			
	}

	@PostMapping("/deleteuser")
	public void deleteUser(User user) {
		if (user.getUserId() == null || user.getPassword() == null) {	
			throw new UserNotFoundException("삭제하려는 유저정보가 없습니다.");
		}
		userservice.deleteUser(user);
	}

}

