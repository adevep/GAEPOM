package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.domain.Application;
import com.gaepom.domain.User;
import com.gaepom.service.ApplicationService;

//@RestController
@Controller
@SessionAttributes("guser")
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}

	@RequestMapping("/getApplicationList")
	public String getApplicationList(@ModelAttribute("guser") User user, Model model,
			Application application) {

		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		List<Application> applicationList = applicationService.getApplicationList(application);

		System.out.println(applicationList);
		model.addAttribute("applicationList", applicationList);
		return "getApplicationList";
	}

	@GetMapping("/insertApplication")
	public String insertApplicationView(@ModelAttribute("guser") User user) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		return "insertApplication";
	}

	@PostMapping("/insertApplication")
	public String insertApplication(@ModelAttribute("guser") User user, Application application) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		applicationService.insertApplication(application);
		System.out.println("-------insertApplication----------------------");
		return "redirect:getApplicationList";
	}

	@GetMapping("/getApplication")
	public String getApplication(@ModelAttribute("guser") User user, Application application, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		model.addAttribute("application", applicationService.getApplication(application));
		return "getApplication";
	}

	@PostMapping("/updateApplication")
	public String updateApplication(@ModelAttribute("guser") User user, Application application) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		applicationService.updateApplication(application);
		return "forward:getApplicationList";
	}

	@GetMapping("/deleteApplication")
	public String deleteApplication(@ModelAttribute("guser") User user, Application application) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		applicationService.deleteApplication(application);
		return "forward:getApplicationList";
	}
}