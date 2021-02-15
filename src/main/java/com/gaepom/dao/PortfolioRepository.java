package com.gaepom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long>, JpaRepository<Portfolio, Long> {

	@Query(value = "SELECT * FROM Portfolio pf WHERE pf.pf_seq=?", nativeQuery = true)
	Portfolio findByPfSeq(Long pfSeq);

	@Query(value = "SELECT * FROM Portfolio pf WHERE pf.user_id=?", nativeQuery = true)
	Portfolio findPortfolioByUserId(String userId);

	@Query(value = "SELECT * FROM Portfolio pf WHERE pf.pf_subtitle=?", nativeQuery = true)
	Portfolio findByPfSubtitle(String pfSubtitle);
}
