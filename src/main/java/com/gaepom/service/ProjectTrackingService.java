package com.gaepom.service;

import java.util.List;


import com.gaepom.domain.Comment;
import com.gaepom.domain.ProjectTracking;

public interface ProjectTrackingService {
	List<ProjectTracking> getProjectTrackingList(ProjectTracking tracking);
	
	List<ProjectTracking> getProjectTrackingList2();

	void insertProjectTracking(ProjectTracking tracking);

	ProjectTracking getProjectTracking(Long tracking);

	void updateProjectTracking(ProjectTracking tracking);
	
	void updateProjComment(Comment comment);
	
	ProjectTracking updateTrackingLike(Long trackSeq, int trackLike);

	void deleteProjectTracking(ProjectTracking tracking);

}
