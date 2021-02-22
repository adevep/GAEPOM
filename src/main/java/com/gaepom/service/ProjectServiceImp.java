package com.gaepom.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;
import com.gaepom.exception.ProjectException;
import com.gaepom.exception.ProjectNotFoundException;
import com.gaepom.domain.ProjectTracking;

@Service
public class ProjectServiceImp implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private UserRepository userRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Project> getProjectList(Project project) {

		logger.info("전체 프로젝트 리스트 조회");
		return (List<Project>) projectRepo.findAll();
	}

	public List<Project> getPjByUserId(Project project, User user) {

		Optional<User> usercheck = userRepo.findById(user.getUserId());

		if (usercheck.isPresent()) {
			logger.info("{} 회원 프로젝트 리스트 조회", user.getUserId());
			return (List<Project>) projectRepo.findPjByUserId(user);
		} else {
			logger.error("{} 회원 프로젝트 리스트 조회 실패", user.getUserId());
			throw new ProjectNotFoundException("해당 유저의 프로젝트가 존재하지 않습니다.");
		}
	}

	public List<Project> getPjByUserId2(Project project, String userid) {
		return (List<Project>) projectRepo.findPjByUserId2(userid);
	}

	public Project insertProject(Project project) {

		Optional<Project> projectcheck = projectRepo.findById(project.getPjSeq());

		if (!projectcheck.isPresent()) {
			logger.info("{} 프로젝트 등록", project.getPjTitle());
			return projectRepo.save(project);
		} else {
			logger.error("{} 프로젝트 등록 실패", project.getPjTitle());
			throw new ProjectException("프로젝트 등록 실패");
		}
	}

	public Project getProject(Long id) {

		Optional<Project> project = projectRepo.findById(id);

		if (project.isPresent()) {
			logger.info("{} 프로젝트 조회", id);
			return project.get();
		} else {
			logger.error("{} 프로젝트 조회 실패", id);
			throw new ProjectNotFoundException("해당 프로젝트 ID가 존재하지 않습니다.");
		}
	}

	public Project updateProject(Long id, Project project) {

		Optional<Project> findProject = projectRepo.findById(id);

		if (findProject.isPresent()) {
			findProject.get().setPjTitle(project.getPjTitle());
			findProject.get().setPjDescription(project.getPjDescription());
			findProject.get().setPjDuration(project.getPjDuration());
			findProject.get().setPjTools(project.getPjTools());
			findProject.get().setPjCategory(project.getPjCategory());
			findProject.get().setPjLang(project.getPjLang());
			findProject.get().setPjDbms(project.getPjDbms());

			logger.info("{} 프로젝트 갱신 성공", id);
			return projectRepo.save(findProject.get());
		} else {
			logger.error("{} 프로젝트 글 갱신 실패", id);
			throw new ProjectNotFoundException("해당하는 프로젝트가 없습니다.");
		}

	}

	public void updateProjTracking(ProjectTracking projectTracking, User user) {

		Optional<Project> findProject = projectRepo.findById(projectTracking.getProject().getPjSeq());

		if (findProject.isPresent()) {
			findProject.get().setTrackSeq(projectTracking);

			logger.info("{} 프로젝트 갱신 성공", findProject.get().getPjSeq());
			projectRepo.save(findProject.get());
		} else {
			logger.error("{} 프로젝트 글 갱신 실패", findProject.get().getPjSeq());
			throw new ProjectNotFoundException("해당하는 프로젝트가 없습니다.");
		}
	}

	public void deleteProject(Project project) {

		Optional<Project> projectcheck = projectRepo.findById(project.getPjSeq());

		if (projectcheck.isPresent()) {
			logger.info("{} 프로젝트 글 삭제", project.getPjSeq());
			projectRepo.deleteById(project.getPjSeq());
		} else {
			logger.error("{} 프로젝트 글 삭제 실패", project.getPjSeq());
			throw new ProjectNotFoundException("해당하는 프로젝트가 존재하지 않습니다.");
		}
	}
}
