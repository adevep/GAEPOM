package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.Portfolio;
import com.gaepom.service.PortfolioService;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "portfolio")
@RestController
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioservice;

	// ===== 포트폴리오 생성(등록) =====
	@PostMapping("/insert")
	public ResponseEntity<Portfolio> insertPortfolio(Portfolio portfolio, @RequestParam String[] pftoolslist,
			@RequestParam(required = false) String[] pflanglist, @RequestParam(required = false) String[] pfdbmslist,
			@RequestParam String userid) {
		Portfolio insertedportfolio = portfolioservice.insertPortfolio(portfolio, pftoolslist, pflanglist, pfdbmslist,
				userid);
		return new ResponseEntity<>(insertedportfolio, HttpStatus.CREATED);
	}

	// ===== 특정 시퀀스(@param pfSeq)의 포트폴리오 조회 =====
	@GetMapping("/get")
	public ResponseEntity<Portfolio> getPortfolio(@RequestParam Long pfSeq) {
		Portfolio getedportfolio = portfolioservice.getPortfolio(pfSeq);
		return new ResponseEntity<>(getedportfolio, HttpStatus.OK);
	}

	// ===== 모든 포트폴리오 조회 =====
	@GetMapping("/getlist")
	public ResponseEntity<List<Portfolio>> getPortfolioList(@RequestParam String userid) {
		List<Portfolio> portfolios = portfolioservice.getPortfolioList(userid);
		return new ResponseEntity<>(portfolios, HttpStatus.OK);
	}

	// ===== 특정 시퀀스(@param pfSeq)의 포트폴리오 수정 =====
	@PutMapping("/update")
	public ResponseEntity<Portfolio> updatePortfolio(Portfolio portfolio, @RequestParam Long pfSeq,
			String[] pftoolslist, String[] pflanglist, String[] pfdbmslist) {
		Portfolio updatedportfolio = portfolioservice.updatePortfolio(portfolio, pfSeq, pftoolslist, pflanglist,
				pfdbmslist);
		return new ResponseEntity<>(updatedportfolio, HttpStatus.OK);
	}

	// ===== 특정 시퀀스(@param pfSeq)의 포트폴리오 삭제 =====
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deletePortfolio(@RequestParam Long pfSeq) {
		portfolioservice.deletePortfolio(pfSeq);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}