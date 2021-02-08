package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;

@Service
public class ProjectServiceImp implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private ProjectRecruitRepository projectRecruitRepo;

	public List<Project> getProjectList(Project project) {
		return (List<Project>) projectRepo.findAll();
	}
	
//	public List<Project> getProjectListByUserId(String userId) {
//		return (List<Project>) projectRepo.findProjectByUserId(userId);
//	}
	
//	public List<Project> getAllProjectByUserId(String userId) {
//		return (List<Project>) projectRepo.findAllProjectByUserId(userId);
//	}

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

//	public void updateProjectWithRecSeq(Project project, ProjectRecruit recSeq) {
//		Project findProject = projectRepo.findById(project.getPjSeq()).get();
//		findProject.setRecSeq(recSeq);		
//		projectRepo.save(findProject);
//	}
//	
	
//	public void updateProjectWithRecSeq(Long pjSeq, Long recSeq) {
//		projectRepo.updateRecSeq(pjSeq, recSeq);
//	}
//	
//	public void updateProjectWithRecSeq2(Project project, ProjectRecruit recruit) {
//		Project findProject = projectRepo.findById(project.getPjSeq()).get();
//		ProjectRecruit findRec = projectRecruitRepo.findById(recruit.getRecSeq()).get();
//		Long findRecSeq = findRec.getRecSeq();
//		
//		findProject.setRecSeq(findRecSeq);
//		projectRepo.save(findProject);
//		
//	}
	
	public void deleteProject(Project project) {
		projectRepo.deleteById(project.getPjSeq());
	}
}
