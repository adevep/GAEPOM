package com.gaepom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.User;
import com.gaepom.exception.UserNotFoundException;
import com.gaepom.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class LoginController {

	@Autowired
	private UserService userservice;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public User login(@RequestParam String userid, @RequestParam String password) {		
		User loginUser = userservice.login(userid, password);
				
		if (loginUser != null) {
			return loginUser;
		} else {
			throw new UserNotFoundException("ID와 비밀번호를 확인해주세요.");
		}
	}

}

