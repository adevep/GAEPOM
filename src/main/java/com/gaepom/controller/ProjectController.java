package com.gaepom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;


import com.gaepom.service.ProjectRecruitService;
import com.gaepom.service.ProjectService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "project") // http://localhost:80/project/getlist

@SessionAttributes("guser")


public class ProjectController {

	// 프로젝트와 모집글에 필요한 메소드들은 ProjectRecruitController로 옮김
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectRecruitService projectRecruitService;

	
	@Autowired
	private ProjectRepository projRepo;
	
	@GetMapping("/getproject")
	public Project getProject(User user, Long projectId) {
		
		try {
			Optional<Project> getProject = projRepo.findById(projectId);
			
			System.out.println("list 반환 성공");
			return getProject.get();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}