package com.gaepom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectRecruitService;

@SessionAttributes
@Controller
public class ProjectRecruitController {
	
	@Autowired
	private ProjectRecruitService projectRecruitService;
	
	@ModelAttribute("user")
	public User setUser() {
		return new User();
	}
	
	@RequestMapping("/getProjectRecruitList")
	public String getProjectRecruitList(@ModelAttribute("user") User user, Model model, ProjectRecruit projectRecruit) {
		if(user.getUserId() == null) {
			//return "redirect:login.html
		}
		List<ProjectRecruit> projectRecruits = projectRecruitService.getProjectRecruitList(projectRecruit);
		
		System.out.println(projectRecruits);
		model.addAttribute("projectRecruits", projectRecruits);
		return "getProjectRecruits";
	}
	
	@GetMapping("/insertProjectRecruit")
	public String insertProjectRecruitView(@ModelAttribute("user") User user) {
		if(user.getUserId() == null) {
			return "redirect:login";
		}
		return "insertProjectRecruit";
	}
	
	@PostMapping("/insertProjectRecruit")
	public String insertProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit projectRecruit) {
		if(user.getUserId() == null) {
			return "redirect:login";
		}
		projectRecruitService.insertProjectRecruit(projectRecruit);
		System.out.println("----insertProjectRecruit----");
		return "redirect:getProjectRecruits";
	}
	
	@GetMapping("/getProjectRecruit")
	public String getProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit projectRecruit, Model model) {
		if(user.getUserId() == null) {
			return "redirect:login";
		}
		model.addAttribute("projectRecruit", projectRecruitService.getProjectRecruit(projectRecruit));
		return "getProjectRecruit";
	}
	
	@PostMapping("/updateProjectRecruit")
	public String updateProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit projectRecruit) {
		if(user.getUserId() == null) {
			return "redirect:login";
		}
		projectRecruitService.updateProjectRecruit(projectRecruit);
		return "forward:getProjectRecruits";
	}
	
	public String deleteProjectRecruit(@ModelAttribute("user") User user, ProjectRecruit projectRecruit) {
		if(user.getUserId() == null) {
			return "redirect:login";
		}
		projectRecruitService.delecteProjectRecruit(projectRecruit);
		return "forward:getProjectRecruits";
	}
	
	

}





