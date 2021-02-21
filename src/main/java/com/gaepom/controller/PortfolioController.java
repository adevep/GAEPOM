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
		Portfolio insertportfolio = portfolioservice.createPortfolio(pftoolslist, pflanglist, pfdbmslist, userid, portfolio);
		return new ResponseEntity<>(insertportfolio, HttpStatus.CREATED);
	}

	// ===== 특정 시퀀스(@param pfSeq)로 포트폴리오 조회 =====
	@GetMapping("/get")
	public ResponseEntity<Portfolio> findPfSeqGetPortfolio(@RequestParam Long pfSeq) {
		Portfolio getportfolio = portfolioservice.findPfSeqGetPortfolio(pfSeq);
		return new ResponseEntity<>(getportfolio, HttpStatus.OK);
	}

	// ===== 특정 유저명(@param userId)으로 포트폴리오 조회 =====
	@GetMapping("/getlist")
	public ResponseEntity<List<Portfolio>> findUserIdGetPortfolio(@RequestParam String userid) {
		List<Portfolio> getportfolios = portfolioservice.findUserIdGetPortfolio(userid);
		return new ResponseEntity<>(getportfolios, HttpStatus.OK);
	}

	// ===== 특정 포트폴리오 수정 =====
	@PutMapping("/updateportfolio/{pfSeq}")
	public ResponseEntity<Portfolio> updatePortfolio(@PathVariable("pfSeq") Long pfseq, String[] pftoolslist,
			String[] pflanglist, String[] pfdbmslist, Portfolio portfolio) {
		Portfolio updatedportfolio = portfolioservice.updatePortfolio(pfseq, pftoolslist, pflanglist, pfdbmslist, portfolio);
		return new ResponseEntity<>(updatedportfolio, HttpStatus.OK);
	}

	// ===== 특정 포트폴리오 삭제 =====
	@DeleteMapping("/deleteportfolio/{pfSeq}")
	public void deletePortfolio(@PathVariable("pfSeq") Long pfSeq) {
		portfolioservice.deleteByPfSeq(pfSeq);
	}
}