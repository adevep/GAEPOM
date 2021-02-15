package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gaepom.domain.Application;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;

public interface ProjectRecruitRepository extends CrudRepository<ProjectRecruit, Long>{

//	@Query("SELECT p FROM ProjectRecruit p WHERE p.userId = :userId")
//	List<ProjectRecruit> findProjectRecruitByUserId (@Param("userId") String userId);
//	
//	@Query("SELECT a FROM Application a WHERE a.userId = :userId and a.selected = 0")
//	RequestWrapper findProjectWithRecruit (@Param("userId") String userId);
//	
}
