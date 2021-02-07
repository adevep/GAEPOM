package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Project;

public interface ProjectService {

	List<Project> getProjectList(Project project);

	void insertProject(Project project);

	Project getProject(Project project);

	void updateProject(Project project);
	
	//void updateProjectWithRecSeq(Long pjSeq, Long recSeq);
	//void updateProjectWithRecSeq2(Project project, Long recSeq);
	
	void deleteProject(Project project);
}
