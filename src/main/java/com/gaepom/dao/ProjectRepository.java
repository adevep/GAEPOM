package com.gaepom.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gaepom.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>, JpaRepository<Project, Long> {
//	@Transactional 
//	@Modifying(clearAutomatically = true)
//	    @Query("UPDATE Project SET rec_seq = :recSeq WHERE pj_seq = :pjSeq")
//	    void updateRecSeq(@Param("pjSeq") Long pjSeq, @Param("recSeq") Long recSeq);
	
}
