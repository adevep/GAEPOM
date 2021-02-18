package com.gaepom.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gaepom.dao.ProjectTrackingRepository;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;
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
	
	@Autowired
	private ProjectTrackingRepository repoTracking;

	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}
	
	@GetMapping("/gettrackinglistaxios")
	public List<ProjectTracking> getProjectTrackingListaxios(User user) {
		System.out.println("axios 전달");
		
		try {
			List<ProjectTracking> projectTrackingList = projectTrackingService.getProjectTrackingList2();
			System.out.println("list 반환 성공"); 
			
			return projectTrackingList;
		} catch(Exception e) { 
			e.printStackTrace();
			return null;
		}
	} 
	
	@GetMapping("/getprojecttracking")
	public ProjectTracking getProjectTracking(User user, Long trackSeq) {
		try {
			Optional<ProjectTracking> getTracking = repoTracking.findById(trackSeq);
			
			System.out.println("tracking 반환 성공");
			return getTracking.get();
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	// TODO: return type 등 고치기
	@PostMapping("/insertprojecttracking")
	public ProjectTracking insertProjectTracking(User user, @RequestBody ProjectTracking tracking) {
		projectTrackingService.insertProjectTracking(tracking);
		projectService.updateProjTracking(tracking);
			
		return tracking;
	}
	
	@PutMapping("/updateprojecttracking")
	public ProjectTracking updateProjectTracking(User user, @RequestBody ProjectTracking tracking) {
		
		projectTrackingService.updateProjectTracking(tracking);
		
		return tracking;
	}
	
	@DeleteMapping("/deleteprojecttracking")
	public void deleteProjectTracking(User user, Long trackSeq) {
		
		Optional<ProjectTracking> tracking = repoTracking.findById(trackSeq);
		projectTrackingService.deleteProjectTracking(tracking.get());
	}

}
