package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {
	
	void savePortfolio(Portfolio portfolio);

	Portfolio findByPfSeq(Long pfSeq);

	Portfolio findByPfSubtitle(String pfSubtitle);
	
	Portfolio findPortfolioByUserId(String userId);

	List<Portfolio> findAllPortfolios();

	void updatePortfolio(Portfolio portfolio);

	void deleteByPfSeq(Long pfSeq);
	
	void deleteAllPortfolios();
	
	boolean isPfSubtitleExist(Portfolio portfolio);

	boolean isUserIdExist(Portfolio portfolio);
}
