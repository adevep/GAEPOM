package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectRecruitService;

@SessionAttributes("user")
public class ProjectRecruitController {

	@Autowired
	private ProjectRecruitService projectRecruitService;

	@ModelAttribute("user")
	public User setUser() {
		return new User();
	}

	@RequestMapping("/getProjectRecruitList")
	public String getProjectRecruitList(@ModelAttribute("user") User user, Model model,
			ProjectRecruit recruit) {

		if (user.getUserId() == null) {
			return "redirect:login.html";
		}

		List<ProjectRecruit> projectRecruitList = projectRecruitService.getProjectRecruitList(recruit);

		System.out.println(projectRecruitList);
		model.addAttribute("projectRecruitList", projectRecruitList);
		return "getProjectRecruitList";
	}

	@GetMapping("/insertProjectRecruit")
	public String insertProjectRecruitView(@ModelAttribute("user") User user) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		return "insertProjectRecruit";
	}

	@PostMapping("/insertProjectRecruit")
	public String insertProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		projectRecruitService.insertProjectRecruit(recruit);
		System.out.println("-------insertProjectRecruit----------------------");
		return "redirect:getprojectRecruitList";
	}

	@GetMapping("/getProjectRecruit")
	public String getProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit recruit, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		model.addAttribute("projectRecruit", projectRecruitService.getProjectRecruit(recruit));
		return "getProjectRecruit";
	}

	@PostMapping("/updateProjectRecruit")
	public String updateProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		projectRecruitService.updateProjectRecruit(recruit);
		return "forward:getProjectRecruitList";
	}

	@GetMapping("/deleteProjectRecruit")
	public String deleteProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		projectRecruitService.deleteProjectRecruit(recruit);
		return "forward:getProjectRecruitList";
	}
}
