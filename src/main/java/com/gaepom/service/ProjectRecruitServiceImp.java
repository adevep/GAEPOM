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

	public List<Object> getTotalRecruitList(ProjectRecruit recruit) {
		return (List<Object>) recruitRecruitRepo.findTotalRecruit();
	}

	public List<Object> getTotalRecruitByPjSeq(Long pjSeq, ProjectRecruit recruit) {
		return (List<Object>) recruitRecruitRepo.findTotalRecruitByPjSeq(pjSeq);
	}

	public List<Object> getTotalRecruitByLocation(String location) {
		return (List<Object>) recruitRecruitRepo.findTotalRecruitByLocation(location);
	}

	public List<Object> getTotalRecruitByCategory(String pjCategory) {
		return (List<Object>) recruitRecruitRepo.findTotalRecruitByCategory(pjCategory);
	}

	public ProjectRecruit insertProjectRecruit(ProjectRecruit recruit) {
		return recruitRecruitRepo.save(recruit);
	}

	public ProjectRecruit getProjectRecruit(Long id) {
		return recruitRecruitRepo.findById(id).get();
	}

	public Object getRecByPj(Long pjSeq, ProjectRecruit recruit) {
		return recruitRecruitRepo.findNeedNum(pjSeq);
	}

	public Object getCheckCountByPj(Long pjSeq, ProjectRecruit recruit) {
		return recruitRecruitRepo.findCheckCount(pjSeq);
	}

	public ProjectRecruit updateRec(Long id, ProjectRecruit recruit) {
		ProjectRecruit findProjectRecruit = recruitRecruitRepo.findById(id).get();

		findProjectRecruit.setLocation(recruit.getLocation());
		findProjectRecruit.setNeedNum(recruit.getNeedNum());
		findProjectRecruit.setPreference(recruit.getPreference());
		findProjectRecruit.setRecDuration(recruit.getRecDuration());
		findProjectRecruit.setRecStatus(recruit.getRecStatus());
		findProjectRecruit.setNeedPosi(recruit.getNeedPosi());

		return recruitRecruitRepo.save(findProjectRecruit);
	}

	public ProjectRecruit updateRecCount(Long id, int checkCount) {

		Long projectRecruitSeq = projectRepo.findById(id).get().getRecSeq().getRecSeq();
		ProjectRecruit findProjectRecruit = recruitRecruitRepo.findById(projectRecruitSeq).get();

		findProjectRecruit.setCheckCount(checkCount);
		return recruitRecruitRepo.save(findProjectRecruit);
	}

	public ProjectRecruit updateProjectRecruit(Long id, ProjectRecruit recruit) {
		ProjectRecruit findProjectRecruit = recruitRecruitRepo.findById(id).get();

		Long pj = findProjectRecruit.getProject().getPjSeq();
		Project findProject = projectRepo.findById(pj).get();

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