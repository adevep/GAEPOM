package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gaepom.domain.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long>, JpaRepository<Portfolio, Long> {

	@Query("SELECT pf FROM Portfolio pf WHERE pf.userId = :userId")
	List<Portfolio> findPortfolioByUserId (@Param("userId") String userId);
	
	List<Portfolio> findByPfSubtitleContaining(String pfSubtitle);
}
