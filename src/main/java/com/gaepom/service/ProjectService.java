package com.gaepom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.User;

import com.gaepom.domain.ProjectTracking;


public interface ProjectService {
	
	//Project Tracking쪽에서 쓰세요 
	List<Project> getProjectList(Project project);

	List<Project> getPjByUserId(Project project, User userId);
	
	List<Project> getPjByUserId2(Project project, String userid);


	Project insertProject(Project project);

	Project getProject(Long id);
	//ㄴProject getProject(Project project);

	Project updateProject(Long id, Project project);

	
//	void updateProjTrackingImg(ProjectTracking projectTracking, MultipartFile mfile);

	void updateProjTracking(ProjectTracking projectTracking, User user);

	void deleteProject(Project project);

}
