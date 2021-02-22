package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gaepom.domain.ProjectRecruit;

public interface ProjectRecruitRepository extends CrudRepository<ProjectRecruit, Long> {

	@Query("SELECT a, b FROM ProjectRecruit a INNER JOIN Project b On a.recSeq = b.recSeq")
	List<Object> findTotalRecruit();

	@Query("SELECT a, b FROM ProjectRecruit a INNER JOIN Project b On a.recSeq = b.recSeq where b.pjSeq =:pjSeq")
	List<Object> findTotalRecruitByPjSeq(@Param("pjSeq") Long pjSeq);

	@Query("SELECT a, b FROM ProjectRecruit a INNER JOIN Project b On a.recSeq = b.recSeq where a.location=:location")
	List<Object> findTotalRecruitByLocation(@Param("location") String location);

	@Query("SELECT a, b FROM ProjectRecruit a INNER JOIN Project b On a.recSeq = b.recSeq where b.pjCategory=:pjCategory")
	List<Object> findTotalRecruitByCategory(@Param("pjCategory") String pjCategory);

	@Query("SELECT a.needNum from ProjectRecruit a INNER JOIN Project b on a.recSeq = b.recSeq where b.pjSeq = :pjSeq")
	Object findNeedNum(@Param("pjSeq") Long pjSeq);

	@Query("SELECT a.checkCount from ProjectRecruit a INNER JOIN Project b on a.recSeq = b.recSeq where b.pjSeq = :pjSeq")
	Object findCheckCount(@Param("pjSeq") Long pjSeq);

}
