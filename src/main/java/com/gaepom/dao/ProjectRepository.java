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
//	@Transactional 
//	@Modifying(clearAutomatically = true)
//	    @Query("UPDATE Project SET rec_seq = :recSeq WHERE pj_seq = :pjSeq")
//	    void updateRecSeq(@Param("pjSeq") Long pjSeq, @Param("recSeq") Long recSeq);
	
//	@Query("SELECT p FROM Project p WHERE p.userId = :userId")
//	List<Project> findProjectByUserId (@Param("userId") String userId);
//	
	//List<Project>findProjectByUserId (@Param("userId") String userId);
	
	
//	@Query("SELECT p, r FROM Project p INNER JOIN ProjectRecruit r ON p.recSeq = r.recSeq where p.userId= :userId")
//	List<Project> findAllProjectByUserId (@Param("userId") String userId);
//	
//	SELECT c1, c2 FROM Country c1, Country c2
//	WHERE c2 MEMBER OF c1.neighbors
	
	@Query("SELECT a FROM Project a INNER JOIN ProjectRecruit b ON a.recSeq = b.recSeq where a.userId =:userId and b.recStatus = 0")
	List<Project> findPjByUserId(@Param("userId") User userId);

}
