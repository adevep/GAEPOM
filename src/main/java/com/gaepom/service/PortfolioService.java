package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Portfolio;

public interface PortfolioService {
	
	Portfolio createPortfolio(String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, String userid, Portfolio portfolio);

	Portfolio findPfSeqGetPortfolio(Long pfSeq);

	List<Portfolio> findUserIdGetPortfolio(String userid);

	Portfolio updatePortfolio(Long pfseq, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, Portfolio portfolio);

	void deleteByPfSeq(Long pfSeq);
}