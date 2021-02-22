package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	@Query(value = "SELECT * FROM g_user WHERE position=?", nativeQuery = true)
	List<User> findAllUserByPosition(String position);
}
