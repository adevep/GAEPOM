package com.gaepom.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gaepom.domain.Project;
import com.gaepom.domain.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>, JpaRepository<Project, Long> {
	
	@Query("SELECT a FROM Project a INNER JOIN ProjectRecruit b ON a.recSeq = b.recSeq where a.userId =:userId and b.recStatus = 0")
	List<Project> findPjByUserId(@Param("userId") User userId);

	@Query(value ="SELECT * FROM Project where user_id =?", nativeQuery = true)
	List<Project> findPjByUserId2(String userid);

}
