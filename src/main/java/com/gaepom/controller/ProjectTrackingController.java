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

import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectTrackingService;

@SessionAttributes("guser")
//@Controller
public class ProjectTrackingController {
//	
//	@Autowired
//	private ProjectTrackingService projectTrackingService;
//
//	@ModelAttribute("guser")
//	public User setUser() {
//		return new User();
//	}
//	
//		
//	@RequestMapping("/getProjectTrackingList")
//	public String getProjectTrackingList(@ModelAttribute("guser") User user, Model model, ProjectTracking tracking) {
//
//		if (user.getUserId() == null) {
//			return "redirect:login.html";
//		}
//
//		List<ProjectTracking> projectTrackingList = projectTrackingService.getProjectTrackingList(tracking);
//
//		model.addAttribute("projectTrackingList", projectTrackingList);
//		return "getProjectTrackingList";
//	}
//
//	@GetMapping("/insertProjectTracking")
//	public String insertProjectTrackingView(@ModelAttribute("guser") User user) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//
//		return "insertProjectTracking";
//	}
//
//	@PostMapping("/insertProjectTracking")
//	public String insertProjectTracking(@ModelAttribute("guser") User user, ProjectTracking tracking) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//
//		projectTrackingService.insertProjectTracking(tracking);
//		return "redirect:getprojectTrackingList";
//	}
//
//	@GetMapping("/getProjectTracking")
//	public String getProjectTracking(@ModelAttribute("guser") User user, ProjectTracking tracking, Model model) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//
//		model.addAttribute("projectTracking", projectTrackingService.getProjectTracking(tracking));
//		return "getProjectTracking";
//	}
//
//	@PostMapping("/updateProjectTracking")
//	public String updateProjectTracking(@ModelAttribute("guser") User user, ProjectTracking tracking) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//
//		projectTrackingService.updateProjectTracking(tracking);
//		return "forward:getprojectTrackingList";
//	}
//
//	@GetMapping("/deleteProjectTracking")
//	public String deleteProjectTracking(@ModelAttribute("guser") User user, ProjectTracking tracking) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
//
//		projectTrackingService.deleteProjectTracking(tracking);
//		return "forward:getprojectTrackingList";
//	}
}
