package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gaepom.domain.Application;
import com.gaepom.domain.Project;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long>, JpaRepository<Application, Long> {

	@Query("SELECT a FROM Application a WHERE a.userId = :userId")
	List<Application> findApplicationByUserId(@Param("userId") String userId);

	@Query("SELECT a FROM Application a WHERE a.userId = :userId and a.selected = 1")
	List<Application> findFailedAppsByUserId(@Param("userId") String userId);

	@Query("SELECT a FROM Application a WHERE a.userId = :userId and a.selected = 2")
	List<Application> findAcceptedAppsByUserId(@Param("userId") String userId);

	@Query("SELECT a FROM Application a WHERE pjSeq = ?1")
	List<Application> findApplicationByPjSeq(Project pjSeq);

}
