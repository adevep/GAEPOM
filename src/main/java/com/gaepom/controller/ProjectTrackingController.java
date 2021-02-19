package com.gaepom.controller;

import java.util.List;

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

import com.gaepom.domain.ProjectTracking;
import com.gaepom.service.ProjectService;
import com.gaepom.service.ProjectTrackingService;

//@SessionAttributes("guser")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ProjectTrackingController {
	@Autowired
	private ProjectTrackingService projectTrackingService;

	@Autowired
	private ProjectService projectService;


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
	public ResponseEntity<ProjectTracking> insertProjectTracking(@RequestBody ProjectTracking tracking) {

		projectTrackingService.insertProjectTracking(tracking);
		projectService.updateProjTracking(tracking);

		return new ResponseEntity<>(tracking, HttpStatus.OK);
	}

	@PutMapping("/updateprojecttracking")
	public ResponseEntity<ProjectTracking> updateProjectTracking(@RequestBody ProjectTracking tracking) {

		projectTrackingService.updateProjectTracking(tracking);

		return new ResponseEntity<>(tracking, HttpStatus.OK);
	}
	
	@PutMapping("/updatetrackinglike")
	public ResponseEntity<ProjectTracking> updateTrackingLike(@RequestParam Long trackSeq, @RequestParam int trackLike) {
		
		ProjectTracking updateTrackingLike = null;
		updateTrackingLike = projectTrackingService.updateTrackingLike(trackSeq, trackLike);
		
		return new ResponseEntity<>(updateTrackingLike, HttpStatus.OK);
	}

	@DeleteMapping("/deleteprojecttracking")
	public ResponseEntity<HttpStatus> deleteProjectTracking(Long trackSeq) {

		ProjectTracking tracking = projectTrackingService.getProjectTracking(trackSeq);
		projectTrackingService.deleteProjectTracking(tracking);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
