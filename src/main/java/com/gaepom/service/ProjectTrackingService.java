package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Comment;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;

import org.springframework.web.multipart.MultipartFile;

public interface ProjectTrackingService {

	List<ProjectTracking> getProjectTrackingList();

	ProjectTracking insertProjectTracking(ProjectTracking tracking, Project project, User user, MultipartFile mfile);

	ProjectTracking insertProjectTrackingNoImg(ProjectTracking tracking, Project project, User user);

	ProjectTracking getProjectTracking(Long tracking);

	ProjectTracking updateProjectTracking(ProjectTracking tracking, Project project, User user);

	ProjectTracking updateProjectTrackingImg(ProjectTracking tracking, Project project, User user, MultipartFile mfile);

	void updateProjComment(Comment comment);

	ProjectTracking updateTrackingLike(Long trackSeq, int trackLike);

	void deleteProjectTracking(Long trackSeq);

}
