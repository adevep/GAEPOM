package com.gaepom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.PortfolioRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Portfolio;
import com.gaepom.domain.User;

@Service("portfolioService")
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepo;

	@Autowired
	private UserRepository userRepo;
	
	// ===== CREATE =====
	public void savePortfolio(Portfolio portfolio) {
		portfolioRepo.save(portfolio);
	}
	
	public void savePortfolio(Portfolio portfolio, String userid) {
		Optional<User> user = userRepo.findById(userid);
		if (user.isPresent()) {
			portfolio.setUserId(user.get());
			portfolioRepo.save(portfolio);
		}
	}
	
	// ===== READ =====
	public Portfolio findByPfSeq(Long pfSeq) {
		System.out.println("# findByPfSeq | " + pfSeq);
		return portfolioRepo.findByPfSeq(pfSeq);
	}

	public Portfolio findPortfolioByUserId(String userId) {
		System.out.println("# findPortfolioByUserId | " + userId);
		return portfolioRepo.findPortfolioByUserId(userId);
	}
	
	private Object findPortfolioByUserId(User userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Portfolio findByPfSubtitle(String pfSubtitle) {
		System.out.println("# findByPfSubtitle | " + pfSubtitle);
		return portfolioRepo.findByPfSubtitle(pfSubtitle);
	}
	
	// 필요할까요?(ROLE_ADMIN | 관리자 입장에서는 필요할지도?)
	public List<Portfolio> findAllPortfolios() {
		return (List<Portfolio>) portfolioRepo.findAll();
	}
	
	// ===== UPDATE =====
	public void updatePortfolio(Portfolio portfolio) {
		Portfolio currentPortfolio = portfolioRepo.findByPfSeq(portfolio.getPfSeq());
		System.out.println("pfSeq 얻음?");
		
		currentPortfolio.setPfSubtitle(portfolio.getPfSubtitle());
		currentPortfolio.setPfDuration(portfolio.getPfDuration());
		currentPortfolio.setPfDescription(portfolio.getPfDescription());
		currentPortfolio.setParticipation(portfolio.getParticipation());
		currentPortfolio.setPfLang(portfolio.getPfLang());
		currentPortfolio.setPfTools(portfolio.getPfTools());
		currentPortfolio.setPfDbms(portfolio.getPfDbms());
		currentPortfolio.setPfLink(portfolio.getPfLink());
		currentPortfolio.setPfCategory(portfolio.getPfCategory());
		currentPortfolio.setPublished(portfolio.getPublished());
		
		System.out.println("포트폴리오 갱신됨?");
		portfolioRepo.save(currentPortfolio);
	}
	
	// ===== DELETE =====
	public void deleteByPfSeq(Long pfSeq) {
		portfolioRepo.deleteById(pfSeq);
	}
	
	// 필요할까요?(ROLE_ADMIN | 관리자 입장에서는 필요할지도?)
	public void deleteAllPortfolios() {
		portfolioRepo.deleteAll();
	}
	
	// 등록된 포트폴리오 중, 제목이 존재하지 않으면(검색 시 없으면) null 반환
	// 제목을 입력하지 않으면 alert("제목을 입력해 주세요!")와 같은 메시지 출력?
	public boolean isPfSubtitleExist(Portfolio portfolio) {
		return findByPfSubtitle(portfolio.getPfSubtitle()) != null;
	}
	
	// 해당 유저가 이미 작성한 포트폴리오가 존재하면 null 반환
	// alert("이미 작성된 포트폴리오가 존재합니다.") 메시지 출력?
	public boolean isUserIdExist(Portfolio portfolio) {
		return findPortfolioByUserId(portfolio.getUserId()) != null;
	}

}
