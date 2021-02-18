package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.User;

public interface ProjectService {
	
	//Project Tracking쪽에서 쓰세요 
	List<Project> getProjectList(Project project);

	List<Project> getPjByUserId(Project project, User userId);
	

	Project insertProject(Project project);

	Project getProject(Long id);
	//ㄴProject getProject(Project project);

	Project updateProject(Long id, Project project);

	// void updateProjectWithRecSeq(Long pjSeq, Long recSeq);
	// void updateProjectWithRecSeq2(Project project, Long recSeq);

	void deleteProject(Project project);

}
