package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {
	
	public Portfolio insertPortfolio(Portfolio portfolio, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, String userid);

	public Portfolio getPortfolio(Long pfSeq);

	public Portfolio getUserPortfolio(String userid);
	
	public List<Portfolio> getPortfolioList();
	
	public Portfolio updatePortfolio(Portfolio portfolio, Long pfseq, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist);

	public Portfolio updateUserPortfolio(Portfolio portfolio, String userid, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist);
	
	public void deletePortfolio(Long pfSeq);
}