package com.gaepom.service;

import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.User;


public interface UserService extends CrudRepository<User, String> {

	User findUserByUserid(String userid);

	
		
}
