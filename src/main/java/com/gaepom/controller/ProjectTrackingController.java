package com.gaepom.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectService;
import com.gaepom.service.ProjectTrackingService;
import com.gaepom.service.UserService;

//@SessionAttributes("guser")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ProjectTrackingController {
	@Autowired
	private ProjectTrackingService projectTrackingService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;


	@GetMapping("/gettrackinglistaxios")
	public ResponseEntity<List<ProjectTracking>> getProjectTrackingListaxios() {

		try {
			List<ProjectTracking> projectTrackingList = projectTrackingService.getProjectTrackingList2();
			System.out.println("list 반환 성공");

			return new ResponseEntity<>(projectTrackingList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getprojecttracking")
	public ResponseEntity<ProjectTracking> getProjectTracking(Long trackSeq) {

		try {
			ProjectTracking tracking = projectTrackingService.getProjectTracking(trackSeq);

			System.out.println("tracking 반환 성공");
			return new ResponseEntity<ProjectTracking>(tracking, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO: return type 등 고치기
	@PostMapping("/insertprojecttracking")
	public ResponseEntity<ProjectTracking> insertProjectTracking(ProjectTracking tracking, User user ,Project project, @RequestParam(value = "file", required = false) MultipartFile mfile) {
		ProjectTracking insertTracking = null;
		
		System.out.println(tracking);
		System.out.println(project);
		System.out.println(user);
		System.out.println(project.getPjTitle());
		if(mfile != null) {
			Project newProject = projectService.updateProject(project.getPjSeq(), project);
			insertTracking = projectTrackingService.insertProjectTracking(tracking, newProject, user, mfile);
			projectService.updateProjTracking(insertTracking, user);
		} else {
			
			Project newProject = projectService.updateProject(project.getPjSeq(), project);
			insertTracking = projectTrackingService.insertProjectTrackingNoImg(tracking, newProject, user);
			projectService.updateProjTracking(insertTracking, user);	
		}

		return new ResponseEntity<>(insertTracking, HttpStatus.OK);
	}

	@PutMapping("/updateprojecttracking")
	public ResponseEntity<ProjectTracking> updateProjectTracking(ProjectTracking tracking, Project project, User user, @RequestParam(value = "file", required = false) MultipartFile mfile) {
		ProjectTracking updateTracking = null;
			System.out.println(tracking.getTrackSeq());
			System.out.println(project.getPjSeq());
			System.out.println(user);
			System.out.println(mfile);
			
			if (mfile != null) {
				updateTracking= projectTrackingService.updateProjectTrackingImg(tracking, project, user, mfile);
			} else {
				updateTracking =projectTrackingService.updateProjectTracking(tracking, project, user);
			}
			return new ResponseEntity<>(updateTracking, HttpStatus.OK);
	}
	
	@PutMapping("/updatetrackinglike")
	public ResponseEntity<ProjectTracking> updateTrackingLike(@RequestParam Long trackSeq, @RequestParam int trackLike) {
		
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