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
	private PortfolioRepository portfoliorepo;

	@Autowired
	private UserRepository userrepo;

	// ===== CREATE =====
	public Portfolio createPortfolio(String[] pftoolslist, String[] pflanglist, String[] pfdbmslist, String userid,
			Portfolio portfolio) {
		
		Optional<User> user = userrepo.findById(userid);
		
		if (user.isPresent()) {
			if (pflanglist != null && pfdbmslist == null) {
				
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setPfLang(String.join(",", pflanglist));
				portfolio.setUserId(user.get());
				
			} else if (pflanglist == null && pfdbmslist != null) {
				
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setPfDbms(String.join(",", pfdbmslist));
				portfolio.setUserId(user.get());
				
			} else if (pflanglist == null && pfdbmslist == null) {
				
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setUserId(user.get());
				
			} else {
				
				portfolio.setPfTools(String.join(",", pftoolslist));
				portfolio.setPfLang(String.join(",", pflanglist));
				portfolio.setPfDbms(String.join(",", pfdbmslist));
				portfolio.setUserId(user.get());
				
			}
			logger.info("생성 완료 | {}: 포트폴리오 생성됨", portfolio);
			return portfoliorepo.save(portfolio);
		} else {
			logger.error("생성 불가 | {}: 포트폴리오 생성 불가", portfolio);
			throw new PortfolioNotFoundException("포트폴리오 생성 불가: " + portfolio);
		}
	}

	// ===== READ =====
	public Portfolio findPfSeqGetPortfolio(Long pfSeq) {
		if (portfoliorepo.findByPfSeq(pfSeq) != null) {
			logger.info("조회 완료 | {}: 포트폴리오 조회됨", pfSeq);
			return portfoliorepo.findByPfSeq(pfSeq);
		} else {
			logger.error("조회 불가 | {}: 포트폴리오 조회 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번 포트폴리오 조회 불가");
		}
	}

	public List<Portfolio> findUserIdGetPortfolio(String userid) {
		if (portfoliorepo.findAllPortfolioByUserId(userid) != null) {
			logger.info("조회 완료 | {}: 포트폴리오 조회됨", userid);
			return portfoliorepo.findAllPortfolioByUserId(userid);
		} else {
			logger.error("조회 불가 | {}: 포트폴리오 조회 불가", userid);
			throw new PortfolioNotFoundException("유저ID: " + userid + " 포트폴리오 조회 불가");
		}
	}

//	public List<Portfolio> findAllPortfolios() {
//		if (portfoliorepo.findAllPortfolios() != null) {
//			logger.info("조회 완료 | 모든 포트폴리오 조회됨");
//			return (List<Portfolio>) portfoliorepo.findAll();
//		} else {
//			logger.info("조회 완료 | 모든 포트폴리오 조회 불가");
//			throw new PortfolioNotFoundException("모든 포트폴리오 조회 불가");
//		}
//	}

	// ===== UPDATE =====
	public Portfolio updatePortfolio(Long pfseq, String[] pftoolslist, String[] pflanglist, String[] pfdbmslist,
			Portfolio portfolio) {
		
		if (portfoliorepo.findById(pfseq) != null) {
			
			Portfolio currentPortfolio = portfoliorepo.findById(pfseq).get();
			
			if (pflanglist != null && pfdbmslist == null) {				

				currentPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
				currentPortfolio.setPfDuration(portfolio.getPfDuration());
				currentPortfolio.setPfDescription(portfolio.getPfDescription());
				currentPortfolio.setParticipation(portfolio.getParticipation());
				currentPortfolio.setPfLink(portfolio.getPfLink());
				currentPortfolio.setPfCategory(portfolio.getPfCategory());
				currentPortfolio.setPfPosition(portfolio.getPfPosition());
				currentPortfolio.setPfTools(String.join(",", pftoolslist));
				currentPortfolio.setPfLang(String.join(",", pflanglist));
				
			} else if (pflanglist == null && pfdbmslist != null) {
				
				currentPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
				currentPortfolio.setPfDuration(portfolio.getPfDuration());
				currentPortfolio.setPfDescription(portfolio.getPfDescription());
				currentPortfolio.setParticipation(portfolio.getParticipation());
				currentPortfolio.setPfLink(portfolio.getPfLink());
				currentPortfolio.setPfCategory(portfolio.getPfCategory());
				currentPortfolio.setPfPosition(portfolio.getPfPosition());
				currentPortfolio.setPfTools(String.join(",", pftoolslist));
				currentPortfolio.setPfDbms(String.join(",", pfdbmslist));
				
			} else if (pflanglist == null && pfdbmslist == null) {
				
				currentPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
				currentPortfolio.setPfDuration(portfolio.getPfDuration());
				currentPortfolio.setPfDescription(portfolio.getPfDescription());
				currentPortfolio.setParticipation(portfolio.getParticipation());
				currentPortfolio.setPfLink(portfolio.getPfLink());
				currentPortfolio.setPfCategory(portfolio.getPfCategory());
				currentPortfolio.setPfPosition(portfolio.getPfPosition());
				currentPortfolio.setPfTools(String.join(",", pftoolslist));
				
			} else {
				
				currentPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
				currentPortfolio.setPfDuration(portfolio.getPfDuration());
				currentPortfolio.setPfDescription(portfolio.getPfDescription());
				currentPortfolio.setParticipation(portfolio.getParticipation());
				currentPortfolio.setPfLink(portfolio.getPfLink());
				currentPortfolio.setPfCategory(portfolio.getPfCategory());
				currentPortfolio.setPfPosition(portfolio.getPfPosition());
				currentPortfolio.setPfTools(String.join(",", pftoolslist));
				currentPortfolio.setPfLang(String.join(",", pflanglist));
				currentPortfolio.setPfDbms(String.join(",", pfdbmslist));
				
			}
			logger.info("수정 완료 | {}번 포트폴리오 수정됨", pfseq);
			portfoliorepo.save(currentPortfolio);
			return currentPortfolio;
		} else {
			logger.error("수정 불가 | {}번 포트폴리오 수정 불가", pfseq);
			throw new PortfolioNotFoundException(pfseq + "번 포트폴리오 수정 불가");
		}
	}

	// ===== DELETE =====
	public void deleteByPfSeq(Long pfSeq) {
		if (portfoliorepo.findByPfSeq(pfSeq) != null) {
			logger.info("삭제 완료 | {}번 포트폴리오 조회 및 삭제 완료됨", pfSeq);
			portfoliorepo.deleteById(pfSeq);
		} else {
			logger.info("삭제 불가 | {}번 포트폴리오 조회 및 삭제 불가", pfSeq);
			throw new PortfolioNotFoundException(pfSeq + "번 포트폴리오 삭제 불가");
		}
	}
}