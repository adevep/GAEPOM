package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.Portfolio;
import com.gaepom.service.PortfolioService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	// ===== 포트폴리오 생성 =====
	@PostMapping("/insertportfolio")
	public ResponseEntity<Portfolio> createPortfolio(@RequestParam(required = true) String userid, Portfolio portfolio) {
		Portfolio createportfolio = portfolioService.createPortfolio(portfolio, userid);

		return new ResponseEntity<>(createportfolio, HttpStatus.CREATED);
	}

	// ===== 특정 시퀀스(@param pfSeq)로 포트폴리오 조회 =====
	@GetMapping("/portfolio/{pfSeq}")
	public ResponseEntity<Portfolio> findPfSeqGetPortfolio(@PathVariable("pfSeq") Long pfSeq) {
		Portfolio pfseqportfolio = portfolioService.findPfSeqGetPortfolio(pfSeq);

		return new ResponseEntity<>(pfseqportfolio, HttpStatus.OK);
	}

	// ===== 특정 유저명(@param userId)으로 포트폴리오 조회 =====
	@GetMapping("/portfolios")
	public ResponseEntity<List<Portfolio>> findUserIdGetPortfolio(@RequestParam(required = true) String userid) {
		List<Portfolio> useridportfolios = portfolioService.findUserIdGetPortfolio(userid);

		return new ResponseEntity<>(useridportfolios, HttpStatus.OK);
	}

	// ===== 모든 포트폴리오 조회 =====
	@GetMapping("/portfolio")
	public ResponseEntity<List<Portfolio>> findAllPortfolios() {
		List<Portfolio> allportfolios = portfolioService.findAllPortfolios();
		
		return new ResponseEntity<>(allportfolios, HttpStatus.OK);
	}

	// ===== 특정 포트폴리오 수정 =====
	@PutMapping("/updateportfolio/{pfSeq}")
	public ResponseEntity<Portfolio> updatePortfolio(@PathVariable("pfSeq") Long pfSeq, Portfolio portfolio) {
		Portfolio currentPortfolio = portfolioService.findPfSeqGetPortfolio(pfSeq);
		portfolioService.updatePortfolio(pfSeq, portfolio);
		
		return new ResponseEntity<>(currentPortfolio, HttpStatus.OK);
	}

	// ===== 특정 포트폴리오 삭제 =====
	@DeleteMapping("/deleteportfolio/{pfSeq}")
	public ResponseEntity<Portfolio> deletePortfolio(@PathVariable("pfSeq") Long pfSeq) {
		Portfolio deleteportfolio = portfolioService.deleteByPfSeq(pfSeq);
		portfolioService.deleteByPfSeq(pfSeq);

		return new ResponseEntity<>(deleteportfolio, HttpStatus.NO_CONTENT);
	}
}
