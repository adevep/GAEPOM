package com.gaepom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.UserRepository;
import com.gaepom.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public User getUser(User user) {
		return userRepo.findById(user.getUserId()).get();
	}
	
	public List<User> getUserList(User user) {
		return (List<User>) userRepo.findAll();
	}

	public void insertUser(User user) {
		userRepo.save(user);
	}
	
	public void updateUser(User user) {
		User findUser = userRepo.findById(user.getUserId()).get();

		findUser.setUserId(user.getUserId());
		findUser.setPassword(user.getPassword());
		findUser.setName(user.getName());
		findUser.setAge(user.getAge());
		findUser.setEmail(user.getEmail());
		findUser.setPhoneNum(user.getPhoneNum());
		findUser.setAddress(user.getAddress());		
		findUser.setStack(user.getStack());		
		findUser.setUserImage(user.getUserImage());		
		findUser.setPosition(user.getPosition());		
		
		userRepo.save(findUser);
	}
	
	public void deleteUser(User user) {
		userRepo.deleteById(user.getUserId());
	}
}
