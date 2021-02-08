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

import com.gaepom.domain.Portfolio;
import com.gaepom.domain.User;
import com.gaepom.service.PortfolioService;
import com.gaepom.service.UserService;

@SessionAttributes({"guser",})
@Controller
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private UserService userService;
	
//	@ModelAttribute("guser")
//	public User setUser() {
//		return new User();
//	}
	
	@GetMapping("/getPortfolioList")
	public String getPortfolioList(@ModelAttribute("guser") User user, Model model, Portfolio portfolio) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		List<Portfolio> portfolioList = portfolioService.getPortfolioList(portfolio);

		System.out.println(portfolioList);
		model.addAttribute("portfolioList", portfolioList);
		
		return "getPortfolioList";
	}
	
	@GetMapping("/getPortfolio")
	public String getPortfolio(@ModelAttribute("guser") User user, Portfolio portfolio, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		
		model.addAttribute("portfolio", portfolioService.getPortfolio(portfolio));
		
		return "getPortfolio";
	}

	@GetMapping("/insertPortfolio")
	public String insertPortfolioView(@ModelAttribute("guser") User user) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		return "insertPortfolio";
	}

	@PostMapping("/insertPortfolio")
	public String insertPortfolio(@ModelAttribute("guser") User user, Portfolio portfolio) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		portfolioService.insertPortfolio(portfolio);
		
		return "redirect:getPortfolioList";
	}


//	@GetMapping("/updatePortfolioReq")
//	public String updatePortfolioReq(@ModelAttribute("guser") User user, Portfolio portfolio) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//		
//		return "updatePortfolio";
//	}

	@PostMapping("/updatePortfolio")
	public String updatePortfolio(@ModelAttribute("guser") User user, Portfolio portfolio) {
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
	
		portfolioService.updatePortfolio(portfolio);
		
		return "forward:getPortfolioList";
	}

	@GetMapping("/deletePortfolio")
	public String deletePortfolio(@ModelAttribute("guser") User user, Portfolio portfolio, SessionStatus status) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		portfolioService.deletePortfolio(portfolio);
		status.setComplete();
		
		return "forward:getPortfolioList";
	}
}
