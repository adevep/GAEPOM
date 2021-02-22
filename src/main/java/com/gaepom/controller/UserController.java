package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;
import com.gaepom.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "user")
@RestController

public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping("/insert")
	public ResponseEntity<User> insertUser(User user, @RequestParam String[] stacklist,
			@RequestParam(value = "file", required = false) MultipartFile mfile) {
		User inserteduser = null;
		if (mfile != null) {
			inserteduser = userservice.insertUser(user, stacklist, mfile);
		} else {
			inserteduser = userservice.insertUserNoimg(user, stacklist);
		}
		return new ResponseEntity<>(inserteduser, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<User> getUser(@RequestParam String userid) {
		User geteduser = userservice.getUser(userid);
		return new ResponseEntity<>(geteduser, HttpStatus.OK);
	}

	@GetMapping("/getlist")
	public ResponseEntity<List<User>> getUserList() {
		List<User> users = userservice.getUserList();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(User user, @RequestParam String[] stacklist,
			@RequestParam(value = "file", required = false) MultipartFile mfile) {
		User updateduser = null;
		if (mfile != null) {
			updateduser = userservice.updateUser(user, stacklist, mfile);
		} else {
			updateduser = userservice.updateUserNoimg(user, stacklist);
		}
		return new ResponseEntity<>(updateduser, HttpStatus.OK);

	}

	@PutMapping("/updatetliked")
	public ResponseEntity<User> updateUserTliked(@RequestParam String userid, @RequestParam String tliked) {
		User updateduser = null;
		updateduser = userservice.updateUserTliked(userid, tliked);
		return new ResponseEntity<>(updateduser, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteUser(@RequestParam String userid) {
		userservice.deleteUser(userid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
