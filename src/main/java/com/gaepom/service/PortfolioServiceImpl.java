package com.gaepom.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.PortfolioRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Portfolio;
import com.gaepom.domain.User;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepo;

	@Autowired
	private UserRepository userRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Portfolio getPortfolio(Portfolio portfolio) {
		return portfolioRepo.findById(portfolio.getPfSeq()).get();
	}

	public List<Portfolio> getPortfolioList(Portfolio portfolio) {
		return (List<Portfolio>) portfolioRepo.findAll();
	}

//	public Portfolio findPortfolioByUserId(String userId) {
//		System.out.println("# findPortfolioByUserId | " + userId);
//		System.out.println(portfolioRepo.findById(userId));
//		return (Portfolio) portfolioRepo.findById(userId);
//	}

//	public List<Portfolio> findFailedPortfoliosByUserId(String userId) {
//		System.out.println("# findFailedPortfoliosByUserId | " + userId);
//		System.out.println(portfolioRepo.findFailedPortfoliosByUserId(userId));
//		return (List<Portfolio>) portfolioRepo.findFailedPortfoliosByUserId(userId);
//	}

	public void insertPortfolio(Portfolio portfolio) {
		portfolioRepo.save(portfolio);
		logger.info(portfolio.getPfSeq() + "번 님이 새로운 포트폴리오를 등록했습니다.");
	}

	public void updatePortfolio(Portfolio portfolio) {
		Portfolio findPortfolio = portfolioRepo.findById(portfolio.getPfSeq()).get();

		findPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
		findPortfolio.setPfDuration(portfolio.getPfDuration());
		findPortfolio.setPfDescription(portfolio.getPfDescription());
		findPortfolio.setParticipation(portfolio.getParticipation());
		findPortfolio.setPfLang(portfolio.getPfLang());
		findPortfolio.setPfTools(portfolio.getPfTools());
		findPortfolio.setPfDbms(portfolio.getPfDbms());
		findPortfolio.setPfLink(portfolio.getPfLink());
		findPortfolio.setPfCategory(portfolio.getPfCategory());

		portfolioRepo.save(findPortfolio);
		logger.info(portfolio.getPfSeq() + "번 님이 포트폴리오를 갱신했습니다.");
	}

	public void deletePortfolio(Portfolio portfolio) {
		portfolioRepo.deleteById(portfolio.getPfSeq());
		logger.info(portfolio.getPfSeq() + "님이 포트폴리오를 삭제하였습니다.");
	}

//	@Override
//	public Portfolio findPortfolioByUserId(String userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
