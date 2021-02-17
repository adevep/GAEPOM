package com.gaepom.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.ProjectTrackingRepository;

import com.gaepom.domain.Comment;

import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;

@Service
public class ProjectTrackingServicImpl implements ProjectTrackingService{
	@Autowired
	private ProjectTrackingRepository trackingRepo;
	
	@Autowired
	private ProjectRepository projectRepo;

	public List<ProjectTracking> getProjectTrackingList(ProjectTracking tracking) {
		return (List<ProjectTracking>) trackingRepo.findAll();
	}
	
	public List<ProjectTracking> getProjectTrackingList2() {
		return (List<ProjectTracking>) trackingRepo.findAll();
	}
	
	@Transactional
	public void insertProjectTracking(ProjectTracking tracking) {
		trackingRepo.save(tracking);
	}
	
	public ProjectTracking getProjectTracking(Long tracking) {
		return trackingRepo.findById(tracking).get();
	}
	
	public void updateProjComment(Comment comment) {
		ProjectTracking findTracking = trackingRepo.findById(comment.getTrackSeq().getTrackSeq()).get();
		findTracking.getTcomments().add(comment);
		
		trackingRepo.save(findTracking);
	}
	
	public void updateProjectTracking(ProjectTracking tracking) {
		ProjectTracking findProjectTracking = trackingRepo.findById(tracking.getTrackSeq()).get();
		Project findProject = projectRepo.findById(tracking.getProject().getPjSeq()).get();
		findProject.setPjTitle(tracking.getProject().getPjTitle());
		findProject.setPjDescription(tracking.getProject().getPjDescription());
		findProject.setPjDuration(tracking.getProject().getPjDuration());
		findProject.setPjTools(tracking.getProject().getPjTools());
		findProject.setPjCategory(tracking.getProject().getPjCategory());
		findProject.setPjLang(tracking.getProject().getPjLang());
		findProject.setPjDbms(tracking.getProject().getPjDbms());		
		findProjectTracking.setStage(tracking.getStage());
		findProjectTracking.setIssue(tracking.getIssue());
		findProjectTracking.setOutput(tracking.getOutput());
		findProjectTracking.setTrackImage(tracking.getTrackImage());
		findProjectTracking.setTrackLink(tracking.getTrackLink());
		
		trackingRepo.save(findProjectTracking);
		projectRepo.save(findProject);
	}
	
	public void deleteProjectTracking(ProjectTracking tracking) {
		trackingRepo.deleteById(tracking.getTrackSeq());
	}

}
