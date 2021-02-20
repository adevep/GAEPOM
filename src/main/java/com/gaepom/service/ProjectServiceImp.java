package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;


@Service
public class ProjectServiceImp implements ProjectService {
	//Project Tracking쪽에서 쓰세요 
	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private ProjectRecruitRepository projectRecruitRepo;

	public List<Project> getProjectList(Project project) {
		return (List<Project>) projectRepo.findAll();
	}
	
	public List<Project> getPjByUserId(Project project, User userId) {
		return (List<Project>) projectRepo.findPjByUserId(userId);
	}
	
	public Project insertProject(Project project) {
		return projectRepo.save(project);
	}

//	public ProjectRecruit getProjectRecruit(Long id) {
//		return recruitRecruitRepo.findById(id).get();
//	}

	public Project getProject(Long id) {
		return projectRepo.findById(id).get();
	}
//	public Project getProject(Project project) {
//		return projectRepo.findById(project.getPjSeq()).get();
//	}

	public Project updateProject(Long id, Project project) {
		System.out.println("여기오나?");
		Project findProject = projectRepo.findById(id).get();

		findProject.setPjTitle(project.getPjTitle());
		findProject.setPjDescription(project.getPjDescription());
		findProject.setPjDuration(project.getPjDuration());
		findProject.setPjTools(project.getPjTools());
		findProject.setPjCategory(project.getPjCategory());
		findProject.setPjLang(project.getPjLang());
		findProject.setPjDbms(project.getPjDbms());

		return projectRepo.save(findProject);
	}

	
	public void updateProjTracking(ProjectTracking projectTracking) {
		Project findProject = projectRepo.findById(projectTracking.getProject().getPjSeq()).get();
		findProject.setTrackSeq(projectTracking);
		
		
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
