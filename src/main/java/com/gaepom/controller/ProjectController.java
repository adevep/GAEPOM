package com.gaepom.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;
import com.gaepom.exception.ProjectNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "project")

public class ProjectController {

	@Autowired
	private ProjectRepository projRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/getproject")
	public Project getProject(User user, Long projectId) {

		Optional<Project> getProject = projRepo.findById(projectId);

		if (getProject.isPresent()) {
			logger.info("{} 프로젝트 조회", getProject.get().getPjSeq());
			return getProject.get();
		} else {
			logger.error("{} 프로젝트 조회  실패", getProject.get().getPjSeq());
			throw new ProjectNotFoundException("해당  ID 프로젝트 조회 실패");
		}
	}

}