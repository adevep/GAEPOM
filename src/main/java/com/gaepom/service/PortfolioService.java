package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {
	
	List<Portfolio> getPortfolioList(Portfolio portfolio);

	Portfolio getPortfolio(Portfolio portfolio);
	
//	Portfolio findPortfolioByUserId(String userId);
	
	void insertPortfolio(Portfolio portfolio);
	
	void updatePortfolio(Portfolio portfolio);

	void deletePortfolio(Portfolio portfolio);
}
