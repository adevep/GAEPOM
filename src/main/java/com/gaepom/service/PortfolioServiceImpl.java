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

	@Autowired
	private PortfolioRepository portfoliorepo;

	@Autowired
	private UserRepository userrepo;

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	// ===== CREATE =====
	public Portfolio insertPortfolio(Portfolio portfolio, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, String userid) {
		Optional<User> findUser = userrepo.findById(userid);
		if (findUser.isPresent()) {
			if (pflanglist != null && pfdbmslist == null) {
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setPfLang(String.join(",", pflanglist));
				portfolio.setUserId(findUser.get());
			} else if (pflanglist == null && pfdbmslist != null) {
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setPfDbms(String.join(",", pfdbmslist));
				portfolio.setUserId(findUser.get());
			} else if (pflanglist == null && pfdbmslist == null) {
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setUserId(findUser.get());
			} else {
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setPfLang(String.join(",", pflanglist));
				portfolio.setPfDbms(String.join(",", pfdbmslist));
				portfolio.setUserId(findUser.get());
			}
			logger.info("생성 완료 | {}: 포트폴리오 생성됨", portfolio);
			return portfoliorepo.save(portfolio);
		} else {
			logger.error("생성 불가 | {}: 포트폴리오 생성 불가", portfolio);
			throw new PortfolioNotFoundException("포트폴리오 생성 불가: " + portfolio);
		}
	}

	// ===== READ =====
	public Portfolio getPortfolio(Long pfSeq) {
		Optional<Portfolio> findPortfolio = portfoliorepo.findById(pfSeq);
		if (findPortfolio.isPresent()) {
			logger.info("조회 완료 | {}번글: 포트폴리오 조회됨", pfSeq);
			return findPortfolio.get();
		} else {
			logger.error("조회 불가 | {}번글: 포트폴리오 조회 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번글 포트폴리오 조회 불가");
		}
	}

	public List<Portfolio> getPortfolioList(String userid) {
		if (portfoliorepo.findAllPortfolioByUserId(userid) != null) {
			logger.info("조회 완료 | {}님: 포트폴리오 조회됨", userid);
			return portfoliorepo.findAllPortfolioByUserId(userid);
		} else {
			logger.error("조회 불가 | {}님: 포트폴리오 조회 불가", userid);
			throw new PortfolioNotFoundException("유저ID: " + userid + " 포트폴리오 조회 불가");
		}
	}

	// ===== UPDATE =====
	public Portfolio updatePortfolio(Portfolio portfolio, Long pfseq, String[] pftoolslist,
			String[] pflanglist, String[] pfdbmslist) {
		
		Optional<Portfolio> findPortfolio = portfoliorepo.findById(portfolio.getPfSeq());
		
		if (findPortfolio.isPresent()) {
			
			if (pflanglist != null && pfdbmslist == null) {		
				
				findPortfolio.get().setPfSubtitle(portfolio.getPfSubtitle());
				findPortfolio.get().setPfDuration(portfolio.getPfDuration());
				findPortfolio.get().setPfDescription(portfolio.getPfDescription());
				findPortfolio.get().setParticipation(portfolio.getParticipation());
				findPortfolio.get().setPfLink(portfolio.getPfLink());
				findPortfolio.get().setPfCategory(portfolio.getPfCategory());
				findPortfolio.get().setPfPosition(portfolio.getPfPosition());
				findPortfolio.get().setPfTools(String.join(",", pftoolslist));
				findPortfolio.get().setPfLang(String.join(",", pflanglist));
				
			} else if (pflanglist == null && pfdbmslist != null) {
				
				findPortfolio.get().setPfSubtitle(portfolio.getPfSubtitle());
				findPortfolio.get().setPfDuration(portfolio.getPfDuration());
				findPortfolio.get().setPfDescription(portfolio.getPfDescription());
				findPortfolio.get().setParticipation(portfolio.getParticipation());
				findPortfolio.get().setPfLink(portfolio.getPfLink());
				findPortfolio.get().setPfCategory(portfolio.getPfCategory());
				findPortfolio.get().setPfPosition(portfolio.getPfPosition());
				findPortfolio.get().setPfTools(String.join(",", pftoolslist));
				findPortfolio.get().setPfDbms(String.join(",", pfdbmslist));
				
			} else if (pflanglist == null && pfdbmslist == null) {
				
				findPortfolio.get().setPfSubtitle(portfolio.getPfSubtitle());
				findPortfolio.get().setPfDuration(portfolio.getPfDuration());
				findPortfolio.get().setPfDescription(portfolio.getPfDescription());
				findPortfolio.get().setParticipation(portfolio.getParticipation());
				findPortfolio.get().setPfLink(portfolio.getPfLink());
				findPortfolio.get().setPfCategory(portfolio.getPfCategory());
				findPortfolio.get().setPfPosition(portfolio.getPfPosition());
				findPortfolio.get().setPfTools(String.join(",", pftoolslist));
				
			} else {
				
				findPortfolio.get().setPfSubtitle(portfolio.getPfSubtitle());
				findPortfolio.get().setPfDuration(portfolio.getPfDuration());
				findPortfolio.get().setPfDescription(portfolio.getPfDescription());
				findPortfolio.get().setParticipation(portfolio.getParticipation());
				findPortfolio.get().setPfLink(portfolio.getPfLink());
				findPortfolio.get().setPfCategory(portfolio.getPfCategory());
				findPortfolio.get().setPfPosition(portfolio.getPfPosition());
				findPortfolio.get().setPfTools(String.join(",", pftoolslist));
				findPortfolio.get().setPfLang(String.join(",", pflanglist));
				findPortfolio.get().setPfDbms(String.join(",", pfdbmslist));
				
			}
			portfoliorepo.save(findPortfolio.get());
			logger.info("수정 완료 | {}번글 포트폴리오 수정됨", pfseq);
			return findPortfolio.get();
		} else {
			logger.error("수정 불가 | {}번글 포트폴리오 수정 불가", pfseq);
			throw new PortfolioNotFoundException(pfseq + "번글 포트폴리오 수정 불가");
		}
	}

	// ===== DELETE =====
	public void deletePortfolio(Long pfSeq) {
		Optional<Portfolio> findPortfolio = portfoliorepo.findById(pfSeq);
		if (findPortfolio.isPresent()) {
			portfoliorepo.delete(findPortfolio.get());
			logger.info("삭제 완료 | {}번글 포트폴리오 조회 및 삭제 완료됨", pfSeq);
		} else {
			logger.info("삭제 불가 | {}번글 포트폴리오 조회 및 삭제 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번글 포트폴리오 삭제 불가");
		}
	}
}