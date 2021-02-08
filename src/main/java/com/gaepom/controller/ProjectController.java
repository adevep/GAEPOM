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
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectRecruitService;
import com.gaepom.service.ProjectService;
@Controller
@SessionAttributes("guser")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectRecruitService projectRecruitService;
	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}
	
	@RequestMapping("/getProjectList")
	public String getProjectList(@ModelAttribute("guser") User user, Model model, Project project) {
		
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		List<Project> projectList = projectService.getProjectList(project);
		System.out.println(projectList);
		model.addAttribute("projectList", projectList);
		return "getProjectList";
	}
	//project랑 project recruit 합치기 위한 거
	@RequestMapping("/getProjectListAll")
	public String getProjectListAll(@ModelAttribute("guser") User user, Model model, Project project, ProjectRecruit recruit) {
		
		if (user.getUserId() == null) {
			return "redirect:login.html";
		}
		List<Project> projectList = projectService.getProjectList(project);
		List<ProjectRecruit> projectRecruitList = projectRecruitService.getProjectRecruitList(recruit);
		System.out.println(projectList);
		System.out.println(projectRecruitList);
		model.addAttribute("projectList", projectList);
		model.addAttribute("projectRecruitList", projectRecruitList);
		return "getProjectListAll";
	}
	
	
	@GetMapping("/insertProject")
	public String insertProjectView(@ModelAttribute("guser") User user) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		return "insertProject";
	}
	@PostMapping("/insertProject")
	public String insertProject(@ModelAttribute("guser") User user, Project project) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		projectService.insertProject(project);
		System.out.println("-------insertProject----------------------");
		return "redirect:getProjectList";
	}
	
	//project랑 project recruit 합치기 위한 거
	@PostMapping("/insertProjectAll")
	public String insertProjectAll(@ModelAttribute("guser") User user, Project project, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		project.setRecSeq(projectRecruitService.insertProjectRecruit(recruit));
		System.out.println("----insert recruit----");
		projectService.insertProject(project);
		System.out.println("-------insertProject----------------------");
		
		return "redirect:getProjectListAll";
	}
	
	@GetMapping("/getProject")
	public String getProject(@ModelAttribute("guser") User user, Project project, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		model.addAttribute("project", projectService.getProject(project));
		return "getProject";
	}
	@PostMapping("/updateProject")
	public String updateProject(@ModelAttribute("guser") User user, Project project) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		projectService.updateProject(project);
		return "forward:getProjectList";
	}
	@GetMapping("/deleteProject")
	public String deleteProject(@ModelAttribute("guser") User user, Project project) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		projectService.deleteProject(project);
		return "forward:getProjectList";
	}
}