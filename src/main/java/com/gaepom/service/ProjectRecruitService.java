package com.gaepom.service;
import java.util.List;
import com.gaepom.domain.ProjectRecruit;
public interface ProjectRecruitService {
	List<ProjectRecruit> getProjectRecruitList(ProjectRecruit recruit);
	ProjectRecruit insertProjectRecruit(ProjectRecruit recruit);
	ProjectRecruit getProjectRecruit(ProjectRecruit recruit);
	void updateProjectRecruit(ProjectRecruit recruit);
	void deleteProjectRecruit(ProjectRecruit recruit);
	
	String getPosition(ProjectRecruit recruit);
	
//	List<ProjectRecruit> getProjectRecruitListByUserId(String userId);
}
