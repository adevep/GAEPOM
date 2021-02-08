package com.gaepom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;



public interface UserService {
	
	public User getUser(User User);
	public List<User> getUserList(User User);
	public void insertUser(User user, MultipartFile mfile);
	public User findUserByUserId(String userid);
	public void updateUser(User user);
	public void deleteUser(User user);
}