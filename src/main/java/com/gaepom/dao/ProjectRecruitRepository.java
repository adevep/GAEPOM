package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gaepom.domain.Application;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.RequestWrapper;

public interface ProjectRecruitRepository extends CrudRepository<ProjectRecruit, Long>{

//	@Query("SELECT p FROM ProjectRecruit p WHERE p.userId = :userId")
//	List<ProjectRecruit> findProjectRecruitByUserId (@Param("userId") String userId);
//	
	@Query("SELECT a, b FROM ProjectRecruit a INNER JOIN Project b On a.recSeq = b.recSeq")
	List<Object> findTotalRecruit();
	
	@Query("SELECT a, b FROM ProjectRecruit a INNER JOIN Project b On a.recSeq = b.recSeq where b.pjSeq =:pjSeq")
	List<Object> findTotalRecruitByPjSeq(@Param("pjSeq") Long pjSeq);
}
