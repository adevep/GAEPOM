package com.gaepom.dao;

import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.GUser;

public interface GUserRepository extends CrudRepository<GUser, String> {
	
}
