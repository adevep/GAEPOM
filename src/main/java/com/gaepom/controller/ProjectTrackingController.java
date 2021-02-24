package com.gaepom.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectService;
import com.gaepom.service.ProjectTrackingService;
import com.gaepom.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(value = "track")
@RestController

public class ProjectTrackingController {

	@Autowired
	private ProjectTrackingService projectTrackingService;

	@Autowired
	private ProjectService projectService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/gettrackinglist")
	public ResponseEntity<List<ProjectTracking>> getProjectTrackingListaxios() {

		try {
			List<ProjectTracking> projectTrackingList = projectTrackingService.getProjectTrackingList();

			logger.info("tracking list 반환 성공");
			return new ResponseEntity<>(projectTrackingList, HttpStatus.OK);
		} catch (Exception e) {
			logger.debug(e + "tracking list 반환 실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getprojecttracking")
	public ResponseEntity<ProjectTracking> getProjectTracking(Long trackSeq) {

		try {
			ProjectTracking tracking = projectTrackingService.getProjectTracking(trackSeq);

			logger.info("tracking 반환 성공");
			return new ResponseEntity<ProjectTracking>(tracking, HttpStatus.OK);

		} catch (Exception e) {
			logger.debug(e + "tracking 반환 실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/insertprojecttracking")
	public ResponseEntity<ProjectTracking> insertProjectTracking(ProjectTracking tracking, User user, Project project,
			@RequestParam(value = "file", required = false) MultipartFile mfile) {
		ProjectTracking insertTracking = null;

		if (mfile != null) {
			Project newProject = projectService.updateProject(project.getPjSeq(), project);
			insertTracking = projectTrackingService.insertProjectTracking(tracking, newProject, user, mfile);
			projectService.updateProjTracking(insertTracking, user);
			logger.info("tracking 생성 성공");
		} else {

			Project newProject = projectService.updateProject(project.getPjSeq(), project);
			insertTracking = projectTrackingService.insertProjectTrackingNoImg(tracking, newProject, user);
			projectService.updateProjTracking(insertTracking, user);
			logger.info("tracking 생성 성공");
		}

		logger.debug("tracking 생성 실패");
		return new ResponseEntity<>(insertTracking, HttpStatus.OK);
	}

	@PutMapping("/updateprojecttracking")
	public ResponseEntity<ProjectTracking> updateProjectTracking(ProjectTracking tracking, Project project, User user,
			@RequestParam(value = "file", required = false) MultipartFile mfile) {
		ProjectTracking updateTracking = null;
		if (mfile != null) {
			updateTracking = projectTrackingService.updateProjectTrackingImg(tracking, project, user, mfile);
		} else {
			updateTracking = projectTrackingService.updateProjectTracking(tracking, project, user);
		}

		logger.debug("tracking update 실패");
		return new ResponseEntity<>(updateTracking, HttpStatus.OK);
	}

	@PutMapping("/updatetrackinglike")
	public ResponseEntity<ProjectTracking> updateTrackingLike(@RequestParam Long trackSeq,
			@RequestParam int trackLike) {

		ProjectTracking updateTrackingLike = null;
		updateTrackingLike = projectTrackingService.updateTrackingLike(trackSeq, trackLike);

		return new ResponseEntity<>(updateTrackingLike, HttpStatus.OK);
	}

	@DeleteMapping("/deleteprojecttracking")
	public ResponseEntity<HttpStatus> deleteProjectTracking(Long trackSeq) {

		projectTrackingService.deleteProjectTracking(trackSeq);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}