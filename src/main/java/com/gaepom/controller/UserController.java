package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;
import com.gaepom.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping("/insertuser")
	public User insertUser(User user, @RequestParam("file") MultipartFile mfile) {
		User inserteduser = null;
		if (!mfile.isEmpty()) {
			inserteduser = userservice.insertUser(user, mfile);
		} else {
			inserteduser = userservice.insertUserNoimg(user);
		}
		return inserteduser;
	}

	@GetMapping("/getuser")
	public User getUser(@RequestParam String userid) {
		User geteduser = userservice.getUser(userid);
		return geteduser;
	}
	
	@GetMapping("/getuserlist")
	public List<User> getUserList() {
		List<User> users = userservice.getUserList();
		return users;
	}
	
	@GetMapping("/getuserlistposition")
	public List<User> getUserListByPosition(@RequestParam String position) {
		List<User> positionusers = userservice.getUserListByPostion(position);
		return positionusers;
	}


	@PutMapping("/updateuser")
	public User updateUser(User user, @RequestParam(value = "file", required = false) MultipartFile mfile) {
		User updateduser = null;
		if (mfile != null) {
			updateduser = userservice.updateUser(user, mfile);
		} else {
			updateduser = userservice.updateUserNoimg(user);
		}
		return updateduser;

	}
<<<<<<< Updated upstream

=======
	
	@PutMapping("/updateusertliked")
	public User updateUserTliked(@RequestParam String userid, @RequestParam String tliked) {
		User updateduser = null;
		
		updateduser = userservice.updateUserTliked(userid, tliked);

		return updateduser; 
	}
	
>>>>>>> Stashed changes
	@DeleteMapping("/deleteuser")
	public void deleteUser(@RequestParam String userid) {
		userservice.deleteUser(userid);
	}

}
