package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.domain.ProjectRecruit;

@Service
public class ProjectRecruitServiceImp {
	
	@Autowired
	private ProjectRecruitRepository recruitRepo;
	
	public List<ProjectRecruit> getProjectRecruitList(ProjectRecruit projectRecruit) {
		return (List<ProjectRecruit>) recruitRepo.findAll();
	}
	
	public void insertProjectRecruit(ProjectRecruit projectRecruit) {
		recruitRepo.save(projectRecruit);
	}
	
	public ProjectRecruit getProjectRecruit(ProjectRecruit projectRecruit) {
		return recruitRepo.findById(projectRecruit.getRecSeq()).get();
	}
	
	public void updateProjectRecruit(ProjectRecruit projectRecruit) {
		ProjectRecruit findProjectRecruit = recruitRepo.findById(projectRecruit.getRecSeq()).get();
		
		findProjectRecruit.setLocation(projectRecruit.getLocation());
		findProjectRecruit.setNeedNum(projectRecruit.getNeedNum());
		findProjectRecruit.setPreference(projectRecruit.getPreference());
		findProjectRecruit.setRecDuration(projectRecruit.getRecDuration());
		recruitRepo.save(findProjectRecruit);
	}
	
	public void deleteProjecetRecruit(ProjectRecruit projectRecruit) {
		recruitRepo.deleteById(projectRecruit.getRecSeq());
	}



}
