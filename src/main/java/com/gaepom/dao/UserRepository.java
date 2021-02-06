package com.gaepom.dao;

import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	
}
