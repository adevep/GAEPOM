package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gaepom.domain.Application;
import com.gaepom.domain.Portfolio;
import com.gaepom.domain.User;
import com.gaepom.service.PortfolioService;
import com.gaepom.service.UserService;

@SessionAttributes("guser")
@Controller
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private UserService userService;

	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}

	@RequestMapping("/getportfoliolist")
	public String getPortfolioList(@ModelAttribute("guser") User user, Model model, Portfolio portfolio) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		List<Portfolio> portfolioList = portfolioService.getPortfolioList(portfolio);

		System.out.println(portfolioList);
		model.addAttribute("portfoliolist", portfolioList);

		return "getportfoliolist";
	}

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

		return "forward:getportfoliolist";
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
