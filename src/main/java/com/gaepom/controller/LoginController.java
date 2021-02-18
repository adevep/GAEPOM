package com.gaepom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.User;
import com.gaepom.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LoginController {

	@Autowired
	private UserService userservice;

	@PostMapping("/login")
	public User login(@RequestParam String userid, @RequestParam String password) {
		User loginUser = userservice.login(userid, password);
		return loginUser;
	}
}

