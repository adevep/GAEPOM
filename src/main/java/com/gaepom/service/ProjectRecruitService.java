package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.ProjectRecruit;

public interface ProjectRecruitService {
	List<ProjectRecruit> getProjectRecruitList(ProjectRecruit recruit);

	List<Object> getTotalRecruitList(ProjectRecruit recruit);

	List<Object> getTotalRecruitByPjSeq(Long pjSeq, ProjectRecruit recruit);

	List<Object> getTotalRecruitByLocation(String location);

	List<Object> getTotalRecruitByCategory(String pjCategory);

	ProjectRecruit insertProjectRecruit(ProjectRecruit recruit);

	ProjectRecruit getProjectRecruit(Long id);

	ProjectRecruit updateProjectRecruit(Long id, ProjectRecruit recruit);

	ProjectRecruit updateRec(Long id, ProjectRecruit recruit);

	ProjectRecruit updateRecCount(Long id, int checkCount);

	void deleteProjectRecruit(ProjectRecruit recruit);

	Object getRecByPj(Long pjSeq, ProjectRecruit recruit);

	Object getCheckCountByPj(Long pjSeq, ProjectRecruit recruit);

}
