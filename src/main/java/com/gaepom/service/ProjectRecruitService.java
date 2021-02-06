package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.ProjectRecruit;

public interface ProjectRecruitService {
	
	List<ProjectRecruit> getProjectRecruitList(ProjectRecruit projectRecruit);
	
	void insertProjectRecruit(ProjectRecruit projectRecruit);
	
	ProjectRecruit getProjectRecruit(ProjectRecruit projectRecruit);
	
	void updateProjectRecruit(ProjectRecruit projectRecruit);
	
	void delecteProjectRecruit(ProjectRecruit projectRecruit);
	

}
