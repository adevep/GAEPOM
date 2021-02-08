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
import com.gaepom.exception.PortfolioNotFoundException;
import com.gaepom.exception.PortfolioException;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepo;
	
	@Autowired
	private UserRepository userRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*public Portfolio getPortfolio(Portfolio portfolio) {
		System.out.println("=== # getPortfolio() 조회 중 ===");
		return portfolioRepo.findById(portfolio.getPfSeq()).get();
	}

	public List<Portfolio> getPortfolioList(Portfolio portfolio) {
		System.out.println("=== # getPortfolioList() 조회 중 ===");
		return (List<Portfolio>) portfolioRepo.findAll();
	}

	public void insertPortfolio(Portfolio portfolio) {
		System.out.println("=== # insertPortfolio() 조회 중 ===");
		portfolioRepo.save(portfolio);
		logger.info(portfolio.getPfSeq() + "번 님이 새로운 포트폴리오를 등록했습니다.");
	}
	
	public void updatePortfolio(Portfolio portfolio) {
		System.out.println("=== # updatePortfolio() 조회 중===");
		Portfolio findPortfolio = portfolioRepo.findById(portfolio.getPfSeq()).get();
//		Portfolio findPortfolio = portfolioRepo.findById(portfolio.getPortfolioId().getPortfolioId()).get();

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
	}*/
	
	public Portfolio getPortfolio(Portfolio portfolio) {
		Optional<Portfolio> findPortfolio = portfolioRepo.findById(portfolio.getPfSeq());
		if (findPortfolio.isPresent()) {
			return findPortfolio.get();
		} else {
			throw new PortfolioNotFoundException("해당 회원의 포트폴리오를 찾을 수 없습니다.");
		}
	}
	
	public List<Portfolio> getPortfolioList(Portfolio portfolio) {
		Optional<Portfolio> findPortfolio = portfolioRepo.findById(portfolio.getPfSeq());
		if (findPortfolio.isPresent()) {
			return (List<Portfolio>) portfolioRepo.findAll();
		} else {
			throw new PortfolioNotFoundException("포트폴리오 목록을 찾을 수 없습니다.");
		}
	}
	
	public void insertPortfolio(Portfolio portfolio) {
		Optional<Portfolio> findPortfolio = portfolioRepo.findById(portfolio.getPfSeq());
		if (!findPortfolio.isPresent()) {
			portfolioRepo.save(portfolio);
			logger.info(portfolio.getPfSeq() + "번 님이 새로운 포트폴리오를 등록했습니다.");
		} else {
			throw new PortfolioException("이미 포트폴리오가 존재합니다.");
		}
	}
	
//	public Portfolio findPortfolioByPfSeq(Long pfseq) {
//		Optional<Portfolio> findPortfolio = portfolioRepo.findById(pfseq);
//		if (findPortfolio.isPresent()) {
//			return findPortfolio.get();
//		} else {
//			throw new PortfolioNotFoundException("해당 회원을 찾을 수 없습니다.");
//		}
//	}
	
	@Override
	public Portfolio findPortfolioByUserId(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updatePortfolio(Portfolio portfolio) {
		Optional<Portfolio> findPortfolio = portfolioRepo.findById(portfolio.getPfSeq());
		if (findPortfolio.isPresent()) {
						
			findPortfolio.get().setPfSubtitle(portfolio.getPfSubtitle());
			findPortfolio.get().setPfDuration(portfolio.getPfDuration());
			findPortfolio.get().setPfDescription(portfolio.getPfDescription());
			findPortfolio.get().setParticipation(portfolio.getParticipation());
			findPortfolio.get().setPfLang(portfolio.getPfLang());
			findPortfolio.get().setPfTools(portfolio.getPfTools());
			findPortfolio.get().setPfDbms(portfolio.getPfDbms());
			findPortfolio.get().setPfLink(portfolio.getPfLink());
			findPortfolio.get().setPfCategory(portfolio.getPfCategory());
			
			portfolioRepo.save(findPortfolio.get());
			logger.info(portfolio.getPfSeq() + "번 님이 포트폴리오를 갱신했습니다.");
		} else {
			throw new PortfolioNotFoundException("해당 회원님의 포트폴리오를 찾을 수 없습니다.");		
		}
	}
	
	public void deletePortfolio(Portfolio portfolio) {
		Optional<Portfolio> findPortfolio = portfolioRepo.findById(portfolio.getPfSeq());
		if (findPortfolio.isPresent()) {
			portfolioRepo.delete(findPortfolio.get());
			logger.info(portfolio.getPfSeq() + "님이 포트폴리오를 삭제하였습니다.");
			
		} else {
			throw new PortfolioNotFoundException("해당 회원님의 포트폴리오를 찾을 수 없습니다.");
		}
	}
}
