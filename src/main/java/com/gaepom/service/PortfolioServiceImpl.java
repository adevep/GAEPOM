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
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.exception.PortfolioException;

@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioRepository pfRepo;
	
	@Autowired
	private UserRepository userRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Portfolio getPortfolio(Portfolio pf) {
		System.out.println("=== # getPortfolio() 조회 중 ===");
		logger.info(pf.getPfSeq() + "번 님이 포트폴리오를 조회하였습니다.");
		return pfRepo.findById(pf.getPfSeq()).get();
	}

	public List<Portfolio> getPortfolioList(Portfolio pf) {
		System.out.println("=== # getPortfolioList() 조회 중 ===");
		return (List<Portfolio>) pfRepo.findAll();
	}

	public void insertPortfolio(Portfolio pf) {
		System.out.println("=== # insertPortfolio() 조회 중 ===");
		logger.info(pf.getPfSeq() + "번 님이 새로운 포트폴리오를 등록했습니다.");
		pfRepo.save(pf);
	}
	
	public void updatePortfolio(Portfolio pf) {
		System.out.println("=== # updatePortfolio() 조회 중===");
		Portfolio findPortfolio = pfRepo.findById(pf.getPfSeq()).get();
		Project findUser = userRepo.findById(pf.().get()).get();

		findPortfolio.setPjTitle(tracking.getProject().getPjTitle());
		findPortfolio.setPjDescription(tracking.getProject().getPjDescription());
		findPortfolio.setPjDuration(tracking.getProject().getPjDuration());
		findPortfolio.setPjTools(tracking.getProject().getPjTools());
		findPortfolio.setPjCategory(tracking.getProject().getPjCategory());
		findPortfolio.setPjLang(tracking.getProject().getPjLang());
		findPortfolio.setPjDbms(tracking.getProject().getPjDbms());		
		findPortfolioTracking.setStage(tracking.getStage());
		findPortfolioTracking.setIssue(tracking.getIssue());
		findPortfolioTracking.setOutput(tracking.getOutput());
		findPortfolioTracking.setTrackImage(tracking.getTrackImage());
		findPortfolioTracking.setTrackLink(tracking.getTrackLink());
		
		trackingRepo.save(findPortfolioTracking);
		projectRepo.save(findPortfolio);
	}

	public void deleteProjectTracking(ProjectTracking tracking) {
		trackingRepo.deleteById(tracking.getTrackSeq());
	}

//	@Override
//	public <S extends Portfolio> S save(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends Portfolio> Iterable<S> saveAll(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<Portfolio> findById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean existsById(Long id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Iterable<Portfolio> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Portfolio> findAllById(Iterable<Long> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(Portfolio entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends Portfolio> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
}
