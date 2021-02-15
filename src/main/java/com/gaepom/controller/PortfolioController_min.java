//package com.gaepom.controller;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.gaepom.domain.Portfolio;
//import com.gaepom.exception.PortfolioNotFoundException;
//import com.gaepom.service.PortfolioService;
//
//@CrossOrigin(origins="http://localhost:8081")
//@RestController
//@RequestMapping("/")
//public class PortfolioController_min {
//
//	public static final Logger logger = LoggerFactory.getLogger(PortfolioController_min.class);
//
//	@Autowired
//	private PortfolioService portfolioService;
//
//	// ===== 포트폴리오 생성 =====
//	@RequestMapping(value = "/portfolio", method = RequestMethod.POST)
////	public ResponseEntity<?> createPortfolio(@PathVariable("userId") String userId, @RequestBody Portfolio portfolio) {
//	public ResponseEntity<?> createPortfolio(Portfolio portfolio) {
//		logger.info("생성 완료 | {}: 포트폴리오 생성됨", portfolio);
//
//		if (portfolioService.isPfSubtitleExist(portfolio)) {
//			logger.error("생성 불가 | {}: 같은 제목의 포트폴리오가 이미 존재함", portfolio.getPfSubtitle());
//			return new ResponseEntity<>(new PortfolioNotFoundException("포트폴리오 제목: " + portfolio.getPfSubtitle() + " 생성 불가!"), HttpStatus.CONFLICT);
//		} //else if (portfolioService.isUserIdExist(portfolio)) {
////			logger.error("생성 불가 | {}: 해당 유저의 포트폴리오가 이미 존재함", portfolio.getUserId());
////			return new ResponseEntity<>(new PortfolioNotFoundException("유저명: " + portfolio.getUserId() + " 생성 불가!"), HttpStatus.CONFLICT);
////		}
//		portfolioService.savePortfolio(portfolio);
//
//		return new ResponseEntity<>(portfolio, HttpStatus.CREATED);
//	}
//
//	// ===== 특정 시퀀스(@param pfSeq)로 포트폴리오 조회 =====
//	@RequestMapping(value = "/portfolio/{pfSeq}", method = RequestMethod.GET)
//	public ResponseEntity<?> findPfSeqGetPortfolio(@PathVariable("pfSeq") Long pfSeq) {
//		logger.info("{}번 포트폴리오 조회됨", pfSeq);
//		Portfolio portfolio = portfolioService.findByPfSeq(pfSeq);
//
//		if (portfolio == null) {
//			logger.error("조회 불가 | {}: 존재하지 않은 포트폴리오", pfSeq);
//			return new ResponseEntity<>(new PortfolioNotFoundException("포트폴리오 번호: " + pfSeq + " 조회 불가!"), HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(portfolio, HttpStatus.OK);
//	}
//	// ===== 특정 유저명(@param userId)으로 포트폴리오 조회 =====
////	@RequestMapping(value = "/portfolio/{userId}", method = RequestMethod.GET)
////	public ResponseEntity<?> findUserIdGetPortfolio(@PathVariable("userId") String userId) {
////		logger.info("유저명 {}, 포트폴리오 조회됨", userId);
////		Portfolio portfolio = portfolioService.findPortfolioByUserId(userId);
////		
////		if (portfolio == null) {
////			logger.error("조회 불가 | {}: 존재하지 않은 유저명", userId);
////			return new ResponseEntity<>(new PortfolioNotFoundException("유저명: " + userId + " 조회 불가!"), HttpStatus.NOT_FOUND);
////		}
////		return new ResponseEntity<>(portfolio, HttpStatus.OK);
////	}
//	
//	// ===== 모든 포트폴리오 조회 =====
//	@RequestMapping(value = "/portfolio", method = RequestMethod.GET)
//	public ResponseEntity<List<Portfolio>> listAllPortfolios() {
//		List<Portfolio> portfolios = portfolioService.findAllPortfolios();
//
//		if (portfolios.isEmpty()) {
//			return new ResponseEntity<>(portfolios, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(portfolios, HttpStatus.OK);
//	}
//
//	// ===== 특정 포트폴리오 수정 =====
//	@RequestMapping(value = "/portfolio/{pfSeq}", method = RequestMethod.PUT)
////	@RequestMapping(value = "/portfolio/{userId}", method = RequestMethod.PUT)
//	public ResponseEntity<?> updatePortfolio(@PathVariable("pfSeq") Long pfSeq, @RequestBody Portfolio portfolio) {
////	public ResponseEntity<?> updatePortfolio(@PathVariable("userId") String userId, @RequestBody Portfolio portfolio) {
//		System.out.println("111");
////		logger.info("유저명 {}, 포트폴리오 수정됨", pfSeq);
//		logger.info("수정 완료 | {}번 포트폴리오 수정됨", pfSeq);
//		Portfolio currentPortfolio = portfolioService.findByPfSeq(pfSeq);
//
//		if (currentPortfolio == null) {
//			System.out.println("222");
//			logger.error("수정 불가 | {}: 존재하지 않은 포트폴리오", pfSeq);
//			return new ResponseEntity<>(new PortfolioNotFoundException("포트폴리오 번호: " + pfSeq + " 수정 불가!"), HttpStatus.NOT_FOUND);
//		}
//		portfolioService.updatePortfolio(currentPortfolio);
//		return new ResponseEntity<>(currentPortfolio, HttpStatus.OK);
//	}
//	
//	// ===== 특정 포트폴리오 삭제 =====
//    @RequestMapping(value = "/portfolio/{pfSeq}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deletePortfolio(@PathVariable("pfSeq") Long pfSeq) {
//        logger.info("{}번 포트폴리오 조회 및 삭제 완료됨", pfSeq);
//
//        Portfolio portfolio = portfolioService.findByPfSeq(pfSeq);
//        
//        if (portfolio == null) {
//            logger.error("삭제 불가 | {}: 존재하지 않은 포트폴리오", pfSeq);
//            return new ResponseEntity<>(new PortfolioNotFoundException("포트폴리오 번호: " + pfSeq + " 삭제 불가!"), HttpStatus.NOT_FOUND);
//        }
//        portfolioService.deleteByPfSeq(pfSeq);
//        return new ResponseEntity<Portfolio>(HttpStatus.NO_CONTENT);
//    }
//    
//    // ===== 모든 포트폴리오 삭제 =====
//    @RequestMapping(value = "/portfolio", method = RequestMethod.DELETE)
//    public ResponseEntity<Portfolio> deleteAllPortfolios() {
//        logger.info("모든 포트폴리오 삭제됨");
//
//        portfolioService.deleteAllPortfolios();
//        return new ResponseEntity<Portfolio>(HttpStatus.NO_CONTENT);
//    }
//}
