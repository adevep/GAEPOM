package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {

	public Portfolio insertPortfolio(Portfolio portfolio, String[] pftoolslist, String[] pflanglist,
			String[] pfdbmslist, String userid);

	public Portfolio getPortfolio(Long pfSeq);

	public List<Portfolio> getPortfolioList(String userid);

	public Portfolio updatePortfolio(Portfolio portfolio, Long pfseq, String[] pftoolslist, String[] pflanglist,
			String[] pfdbmslist);

	public void deletePortfolio(Long pfSeq);
}