package com.gaepom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.User;

public interface UserService {

	public User login(String userid, String password);

	public User getUser(String userid);

	public List<User> getUserList();

	public User insertUser(User user, String[] stacklist, MultipartFile mfile);

	public User insertUserNoimg(User user, String[] stacklist);

	public User findUserByUserId(String userid);

	public User updateUser(User user, String[] stacklist, MultipartFile mfile);

	public User updateUserNoimg(User user, String[] stacklist);

	public User updateUserTliked(String userid, String tliked);

	public void deleteUser(String userid);

}
