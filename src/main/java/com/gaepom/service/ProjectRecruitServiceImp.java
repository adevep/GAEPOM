package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;

@Service
public class ProjectRecruitServiceImp implements ProjectRecruitService {
	
	@Autowired
	private ProjectRecruitRepository recruitRecruitRepo;
	
	@Autowired
	private ProjectRepository projectRepo;


	public List<ProjectRecruit> getProjectRecruitList(ProjectRecruit recruit) {
		return (List<ProjectRecruit>) recruitRecruitRepo.findAll();
	}

	public void insertProjectRecruit(ProjectRecruit recruit) {
		recruitRecruitRepo.save(recruit);
	}

	public ProjectRecruit getProjectRecruit(ProjectRecruit recruit) {
		return recruitRecruitRepo.findById(recruit.getRecSeq()).get();
	}

	public void updateProjectRecruit(ProjectRecruit recruit) {
		ProjectRecruit findProjectRecruit = recruitRecruitRepo.findById(recruit.getRecSeq()).get();
		Project findProject = projectRepo.findById(recruit.getProject().getPjSeq()).get();

		findProject.setPjTitle(recruit.getProject().getPjTitle());
		findProject.setPjDescription(recruit.getProject().getPjDescription());
		findProject.setPjDuration(recruit.getProject().getPjDuration());
		findProject.setPjTools(recruit.getProject().getPjTools());
		findProject.setPjCategory(recruit.getProject().getPjCategory());
		findProject.setPjLang(recruit.getProject().getPjLang());
		findProject.setPjDbms(recruit.getProject().getPjDbms());		
		findProjectRecruit.setLocation(recruit.getLocation());
		findProjectRecruit.setNeedNum(recruit.getNeedNum());
		findProjectRecruit.setPreference(recruit.getPreference());
		findProjectRecruit.setRecDuration(recruit.getRecDuration());
		findProjectRecruit.setRecStatus(recruit.getRecStatus());
		
		recruitRecruitRepo.save(findProjectRecruit);
		projectRepo.save(findProject);
	}

	public void deleteProjectRecruit(ProjectRecruit recruit) {
		recruitRecruitRepo.deleteById(recruit.getRecSeq());
	}

}