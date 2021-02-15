package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRecruitRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;

@Service
public class ProjectRecruitServiceImp implements ProjectRecruitService {

	@Autowired
	private ProjectRecruitRepository recruitRecruitRepo;

	@Autowired
	private ProjectRepository projectRepo;

	public List<ProjectRecruit> getProjectRecruitList(ProjectRecruit recruit) {
		return (List<ProjectRecruit>) recruitRecruitRepo.findAll();
	}

	public ProjectRecruit insertProjectRecruit(ProjectRecruit recruit) {
		return recruitRecruitRepo.save(recruit);
	}

	public ProjectRecruit getProjectRecruit(Long id) {
		return recruitRecruitRepo.findById(id).get();
	}

	public ProjectRecruit updateRec(Long id, ProjectRecruit recruit) {
		System.out.println("projectRecruit check1" + recruit);
		ProjectRecruit findProjectRecruit = recruitRecruitRepo.findById(id).get();
		System.out.println("projectRecruit check2" + findProjectRecruit);

		findProjectRecruit.setLocation(recruit.getLocation());
		findProjectRecruit.setNeedNum(recruit.getNeedNum());
		findProjectRecruit.setPreference(recruit.getPreference());
		findProjectRecruit.setRecDuration(recruit.getRecDuration());
		findProjectRecruit.setRecStatus(recruit.getRecStatus());
		findProjectRecruit.setNeedPosi(recruit.getNeedPosi());

		return recruitRecruitRepo.save(findProjectRecruit);
	}

	public ProjectRecruit updateProjectRecruit(Long id, ProjectRecruit recruit) {
		System.out.println("projectRecruit check1" + recruit);
		ProjectRecruit findProjectRecruit = recruitRecruitRepo.findById(id).get();
		System.out.println("projectRecruit check2" + findProjectRecruit);
		Long pj = findProjectRecruit.getProject().getPjSeq();
		System.out.println(pj);
		Project findProject = projectRepo.findById(pj).get();
		System.out.println(findProject);
		findProject.setPjTitle(recruit.getProject().getPjTitle());
		findProject.setPjDescription(recruit.getProject().getPjDescription());
		findProject.setPjDuration(recruit.getProject().getPjDuration());
		findProject.setPjTools(recruit.getProject().getPjTools());
		findProject.setPjCategory(recruit.getProject().getPjCategory());
		findProject.setPjLang(recruit.getProject().getPjLang());
		findProject.setPjDbms(recruit.getProject().getPjDbms());
		findProjectRecruit.setLocation(recruit.getLocation());
		findProjectRecruit.setNeedNum(recruit.getNeedNum());
		findProjectRecruit.setPreference(recruit.getPreference());
		findProjectRecruit.setRecDuration(recruit.getRecDuration());
		findProjectRecruit.setRecStatus(recruit.getRecStatus());
		findProjectRecruit.setNeedPosi(recruit.getNeedPosi());

		projectRepo.save(findProject);

		return recruitRecruitRepo.save(findProjectRecruit);
	}

	public void deleteProjectRecruit(ProjectRecruit recruit) {
		recruitRecruitRepo.deleteById(recruit.getRecSeq());
	}
}