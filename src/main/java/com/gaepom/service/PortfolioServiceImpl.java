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

@Service
public class PortfolioServiceImpl implements PortfolioService {

	public static final Logger logger = LoggerFactory.getLogger(PortfolioServiceImpl.class);

	@Autowired
	private PortfolioRepository portfolioRepo;

	@Autowired
	private UserRepository userRepo;

	// ===== CREATE =====
	public Portfolio createPortfolio(Portfolio portfolio, String userid) {
		Optional<User> user = userRepo.findById(userid);
		if (user.isPresent()) {
			portfolio.setUserId(user.get());
			logger.info("생성 완료 | {}: 포트폴리오 생성됨", portfolio);
			return portfolioRepo.save(portfolio);
		} else {
			logger.error("생성 불가 | {}: 포트폴리오 생성 불가", portfolio);
			throw new PortfolioNotFoundException("포트폴리오 생성 불가: " + portfolio);
		}
	}

	// ===== READ =====
	public Portfolio findPfSeqGetPortfolio(Long pfSeq) {
		if (portfolioRepo.findByPfSeq(pfSeq) != null) {
			logger.info("조회 완료 | {}: 포트폴리오 조회됨", pfSeq);
			return portfolioRepo.findByPfSeq(pfSeq);
		} else {
			logger.error("조회 불가 | {}: 포트폴리오 조회 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번 포트폴리오 조회 불가");
		}
	}

	public List<Portfolio> findUserIdGetPortfolio(String userid) {
		if (portfolioRepo.findAllPortfolioByUserId(userid) != null) {
			logger.info("조회 완료 | {}: 포트폴리오 조회됨", userid);
			return portfolioRepo.findAllPortfolioByUserId(userid);
		} else {
			logger.error("조회 불가 | {}: 포트폴리오 조회 불가", userid);
			throw new PortfolioNotFoundException("유저ID: " + userid + " 포트폴리오 조회 불가");
		}
	}

	public List<Portfolio> findAllPortfolios() {
		if (portfolioRepo.findAllPortfolios() != null) {
			logger.info("조회 완료 | 모든 포트폴리오 조회됨");
			return (List<Portfolio>) portfolioRepo.findAll();
		} else {
			logger.info("조회 완료 | 모든 포트폴리오 조회 불가");
			throw new PortfolioNotFoundException("모든 포트폴리오 조회 불가");
		}
	}

	// ===== UPDATE =====
	public Portfolio updatePortfolio(Long pfSeq, Portfolio portfolio) {
		if (portfolioRepo.findById(pfSeq) != null) {
			Portfolio currentPortfolio = portfolioRepo.findById(pfSeq).get();

			currentPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
			currentPortfolio.setPfDuration(portfolio.getPfDuration());
			currentPortfolio.setPfDescription(portfolio.getPfDescription());
			currentPortfolio.setParticipation(portfolio.getParticipation());
			currentPortfolio.setPfLang(portfolio.getPfLang());
			currentPortfolio.setPfTools(portfolio.getPfTools());
			currentPortfolio.setPfDbms(portfolio.getPfDbms());
			currentPortfolio.setPfLink(portfolio.getPfLink());
			currentPortfolio.setPfCategory(portfolio.getPfCategory());
			currentPortfolio.setUserId(portfolio.getUserId());

			logger.info("수정 완료 | {}번 포트폴리오 수정됨", pfSeq);
			return portfolioRepo.save(currentPortfolio);
		} else {
			logger.error("수정 불가 | {}번 포트폴리오 수정 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번 포트폴리오 수정 불가");
		}
	}

	// ===== DELETE =====
	public Portfolio deleteByPfSeq(Long pfSeq) {
		if (portfolioRepo.findByPfSeq(pfSeq) != null) {
			logger.info("삭제 완료 | {}번 포트폴리오 조회 및 삭제 완료됨", pfSeq);
			return portfolioRepo.findByPfSeq(pfSeq); 
		} else {
			logger.info("삭제 불가 | {}번 포트폴리오 조회 및 삭제 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번 포트폴리오 삭제 불가");
		}
	}
}
