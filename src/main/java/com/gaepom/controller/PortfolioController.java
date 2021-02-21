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
@RestController
@RequestMapping(value = "portfolio")
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioservice;

	// ===== 포트폴리오 생성(등록) =====
	@PostMapping("/insert")
	public ResponseEntity<Portfolio> insertPortfolio(
			@RequestParam String[] pftoolslist,
			@RequestParam(required = false) String[] pflanglist,
			@RequestParam(required = false) String[] pfdbmslist,
			@RequestParam String userid, Portfolio portfolio) {
		Portfolio insertportfolio = portfolioservice.insertPortfolio(pftoolslist, pflanglist, pfdbmslist, userid, portfolio);
		return new ResponseEntity<>(insertportfolio, HttpStatus.CREATED);
	}

	// ===== 특정 시퀀스(@param pfSeq)의 포트폴리오 조회 =====
	@GetMapping("/get")
	public ResponseEntity<Portfolio> getPortfolio(@RequestParam Long pfSeq) {
		Portfolio getportfolio = portfolioservice.getPortfolio(pfSeq);
		return new ResponseEntity<>(getportfolio, HttpStatus.OK);
	}

	// ===== 모든 포트폴리오 조회 =====
	@GetMapping("/getlist")
	public ResponseEntity<List<Portfolio>> getPortfolioList(@RequestParam String userid) {
		List<Portfolio> getportfolios = portfolioservice.getPortfolioList(userid);
		return new ResponseEntity<>(getportfolios, HttpStatus.OK);
	}

	// ===== 특정 시퀀스(@param pfSeq)의 포트폴리오 수정 =====
	@PutMapping("/update")
	public ResponseEntity<Portfolio> updatePortfolio(@RequestParam Long pfseq, String[] pftoolslist,
			String[] pflanglist, String[] pfdbmslist, Portfolio portfolio) {
		Portfolio updateportfolio = portfolioservice.updatePortfolio(pfseq, pftoolslist, pflanglist, pfdbmslist, portfolio);
		return new ResponseEntity<>(updateportfolio, HttpStatus.OK);
	}

	// ===== 특정 시퀀스(@param pfSeq)의 포트폴리오 삭제 =====
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deletePortfolio(@RequestParam Long pfSeq) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}