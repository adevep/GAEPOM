package com.gaepom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;



public interface UserService {
	
	public User login(String userid, String password);
	public User getUser(String userid);
	public List<User> getUserList(User User);
	public User insertUser(User user, MultipartFile mfile);
	public User insertUserNoimg(User user);
	public User findUserByUserId(String userid);
	public User updateUser(User user, MultipartFile mfile);
	public User updateUserNoimg(User user);
	public void deleteUser(User user);
	
		
}
