package com.gaepom.service;

import java.util.List;


import com.gaepom.domain.Project;
import com.gaepom.domain.User;

import com.gaepom.domain.ProjectTracking;


public interface ProjectService {
	
	List<Project> getProjectList(Project project);

	List<Project> getPjByUserId(Project project, User userId);
	
	List<Project> getPjByUserId2(Project project, String userid);

	Project insertProject(Project project);

	Project getProject(Long id);

	Project updateProject(Long id, Project project);

	void updateProjTracking(ProjectTracking projectTracking, User user);

	void deleteProject(Project project);

}
