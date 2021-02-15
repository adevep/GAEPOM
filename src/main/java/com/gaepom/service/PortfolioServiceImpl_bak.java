//package com.gaepom.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.gaepom.dao.PortfolioRepository;
//import com.gaepom.dao.UserRepository;
//import com.gaepom.domain.Portfolio;
//import com.gaepom.domain.User;
//
//@Service("portfolioService")
//public class PortfolioServiceImpl implements PortfolioService {
//
//    private static HashMap<Long, Portfolio> portfolios = new HashMap<>();
//    private static HashMap<String, Long> pfSeqPfSubTitleHashMap = new HashMap<>();
	
	// ===== CREATE =====
//	public void savePortfolio(Portfolio portfolio) {
//		synchronized (this) {
//			portfolios.put(portfolio.getPfSeq(), portfolio);
//			pfSeqPfSubTitleHashMap.put(portfolio.getPfSubtitle(), portfolio.getPfSeq());
//		}
//	}

    // ===== READ =====
//	public Portfolio findByPfSeq(long pfSeq) {
//		return portfolios.get(pfSeq);
//	}
	
//	public Portfolio findByPfSubtitle(String title) {
//		
//		if (pfSeqPfSubTitleHashMap.get(title) != null) {
//			return portfolios.get(pfSeqPfSubTitleHashMap.get(title));
//		}
//		return null;
//	}
	
//	public List<Portfolio> findAllPortfolios() {
//		return new ArrayList<>(portfolios.values());
//	}
	
	// ===== UPDATE =====
//	public void updatePortfolio(Portfolio portfolio) {
//		synchronized (this) {
//			portfolios.put(portfolio.getPfSeq(), portfolio);
//			pfSeqPfSubTitleHashMap.put(portfolio.getPfSubtitle(), portfolio.getPfSeq());
//		}
//	}
//	
//	// ===== DELETE =====
//	public void deletePortfolioByPfSeq(Long pfSeq) {
//		synchronized (this) {
//			pfSeqPfSubTitleHashMap.remove(portfolios.get(pfSeq).getPfSubtitle());
//			portfolios.remove(pfSeq);
//		}
//	}
//	
//	// ===== DELETE =====
//	// 필요할까요?(관리자 입장에서는 필요할지도?)
//	public void deleteAllPortfolios() {
//		portfolios.clear();
//	}
//
//	// 등록된 포트폴리오 중, 제목이 존재하지 않으면(검색 시 없으면) null 반환
//	public boolean isPortfolioExist(Portfolio portfolio) {
//		return findByPfSubtitle(portfolio.getPfSubtitle()) != null;
//	}
//}
