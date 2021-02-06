package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;

@Service
public class ProjectServiceImp {

	@Autowired
	private ProjectRepository projectRepo;

	public List<Project> getProjectList(Project project) {
		return (List<Project>) projectRepo.findAll();
	}

	public void insertProject(Project project) {
		projectRepo.save(project);
	}

	public Project getProject(Project project) {
		return projectRepo.findById(project.getPjSeq()).get();
	}

	public void updateProject(Project project) {
		Project findProject = projectRepo.findById(project.getPjSeq()).get();

		findProject.setPjTitle(project.getPjTitle());
		findProject.setPjDescription(project.getPjDescription());
		findProject.setPjDuration(project.getPjDuration());
		findProject.setPjTools(project.getPjTools());
		findProject.setPjCategory(project.getPjCategory());
		findProject.setPjLang(project.getPjLang());
		findProject.setPjDbms(project.getPjDbms());		
		
		projectRepo.save(findProject);
	}

	public void deleteProject(Project project) {
		projectRepo.deleteById(project.getPjSeq());
	}
}
