package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Project;

public interface ProjectService {

	List<Project> getProjectList(Project project);

	void insertProject(Project project);

	Project getProject(Project project);

	void updateProject(Project project);

	void deleteProject(Project project);
}
