package com.gaepom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gaepom.dao.PortfolioRepository;
import com.gaepom.domain.Application;
import com.gaepom.domain.Portfolio;
import com.gaepom.domain.User;
import com.gaepom.service.PortfolioService;
import com.gaepom.service.UserService;

@SessionAttributes("guser")
//@Controller
@RestController
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private PortfolioRepository portfolioRepo;

//	@Autowired
//	private UserService userService;

//	@ModelAttribute("guser")
//	public User setUser() {
//		return new User();
//	}

	@GetMapping("/getportfolio/{userId}")
	public ResponseEntity<List<Portfolio>> findPortfolioByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Portfolio> pfs = portfolioService.findPortfolioByUserId(userId);
			return new ResponseEntity<>(pfs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/portfolios")
	public ResponseEntity<List<Portfolio>> getAllPortfolios(@RequestParam(required = false) String pfSubtitle) {
		try {
			List<Portfolio> Portfolios = new ArrayList<Portfolio>();

			if (pfSubtitle == null) {
				portfolioRepo.findAll().forEach(Portfolios::add);
			}
//				else {
//				portfolioRepo.findByPfSubtitleContaining(pfSubtitle).forEach(Portfolios::add);
//			}

			if (Portfolios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Portfolios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/portfolios")
	public ResponseEntity<Portfolio> createPortfolios(@RequestBody Portfolio portfolio) {
		try {
			Portfolio _portfolio = portfolioRepo
					.save(new Portfolio(portfolio.getPfSubtitle(),
										portfolio.getPfDuration(),
										portfolio.getPfDescription(),
										portfolio.getParticipation(),
										portfolio.getPfLang(),
										portfolio.getPfTools(),
										portfolio.getPfDbms(),
										portfolio.getPfLink(),
										portfolio.getPfCategory()
										));
			return new ResponseEntity<>(_Portfolios, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@RequestMapping("/getportfoliolist")
//	public String getPortfolioList(@ModelAttribute("guser") User user, Model model, Portfolio portfolio) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//
//		List<Portfolio> portfolioList = portfolioService.getPortfolioList(portfolio);
//
//		System.out.println(portfolioList);
//		model.addAttribute("portfoliolist", portfolioList);
//
//		return "getportfoliolist";
//	}

//	@RequestMapping("/getportfoliolistall")
//	public String getPortfolioListAll(@ModelAttribute("guser") User user, Model model, Portfolio portfolio) {
//		if (user.getUserId() == null) {
//			return "redirect:login.html";
//		}
//
//		List<Portfolio> portfolioList = portfolioService.getPortfolioList(portfolio);
//		List<User> userList = userService.getUserList(user);
//
//		System.out.println(portfolioList);
//		System.out.println(userList);
//		model.addAttribute("portfolioList", portfolioList);
//		model.addAttribute("userList", userList);
//
//		return "getportfoliolistall";
//	}

	@GetMapping("/getportfolio")
	public String getPortfolio(@ModelAttribute("guser") User user, Portfolio portfolio, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		model.addAttribute("portfolio", portfolioService.getPortfolio(portfolio));

		return "getportfolio";
	}

//	@GetMapping("/getportfoliobyuserid")
//	public String getPortfolio(@ModelAttribute("guser") User user, @RequestParam("userId") String userId, Model model) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//		
//		System.out.println("# getPortfolioByUserId | " + userId);
//		
//		List<Portfolio> findPortfolio = portfolioService.findPortfolioByUserId(userId);
//		System.out.println(findPortfolio);
//		model.addAttribute("findPortfolio", findPortfolio);
//
//		return "getPortfolioList";
//	}

	@GetMapping("/insertportfolio")
	public String insertPortfolioView(@ModelAttribute("guser") User user) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		return "insertportfolio";
	}

	@PostMapping("/insertportfolio")
	public String insertPortfolio(@ModelAttribute("guser") User user, Portfolio portfolio) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		portfolioService.insertPortfolio(portfolio);
		System.out.println("=== # insertPortfolio() ===");

		return "redirect:getportfoliolist";
	}

	@GetMapping("/updateportfolioreq")
	public String updatePortfolioReq(@ModelAttribute("guser") User user, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		return "updateportfolio";
	}

	@PostMapping("/updateportfolio")
	public String updatePortfolio(@ModelAttribute("guser") User user, Portfolio portfolio) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		portfolioService.updatePortfolio(portfolio);

		return "redirect:getportfoliolist";
	}

	@GetMapping("/deleteportfolio")
	public String deletePortfolio(@ModelAttribute("guser") User user, Portfolio portfolio, SessionStatus status) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		portfolioService.deletePortfolio(portfolio);
		status.setComplete();

		return "forward:getportfoliolist";
	}
}
