package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {
	
	public Portfolio getPortfolio(Portfolio portfolio);
	
	public List<Portfolio> getPortfolioList(Portfolio portfolio);
	
	public Portfolio findPortfolioByUserId(String userid);
	
	public void insertPortfolio(Portfolio portfolio);
	
	public void updatePortfolio(Portfolio portfolio);

	public void deletePortfolio(Portfolio portfolio);
}
