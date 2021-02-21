package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {
	
	Portfolio insertPortfolio(String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, String userid, Portfolio portfolio);

	Portfolio getPortfolio(Long pfSeq);

	List<Portfolio> getPortfolioList(String userid);

	Portfolio updatePortfolio(Long pfseq, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, Portfolio portfolio);

	void deletePortfolio(Long pfSeq);
}