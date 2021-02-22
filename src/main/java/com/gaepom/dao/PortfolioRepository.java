package com.gaepom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {

	@Query(value = "SELECT * FROM Portfolio WHERE user_id=?", nativeQuery = true)
	List<Portfolio> findAllPortfolioByUserId(String userid);
}
