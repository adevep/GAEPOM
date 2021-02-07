package com.gaepom.dao;

import org.springframework.data.repository.CrudRepository;

import com.gaepom.domain.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, Long> {

}
