package com.gaepom.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.RequestWrapper;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectRecruitService;
import com.gaepom.service.ProjectService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "recruit")
public class ProjectRecruitController {
	@Autowired
	private ProjectRecruitService projectRecruitService;

	@Autowired
	private ProjectService projectService;

	@ModelAttribute("guser")
	public User setUser() {
		return new User();
	}

	// 프로젝트 총 리스트 출력 (고쳐서 두개다 추력하게)
	@GetMapping("/getall")
	public ResponseEntity<List<Project>> findAllRecs(User user, @RequestBody RequestWrapper requestWrapper) {
		List<Project> pj = projectService.getProjectList(requestWrapper.getProject());
		List<ProjectRecruit> recs = projectRecruitService.getProjectRecruitList(requestWrapper.getRecruit());
		return new ResponseEntity<>(pj, HttpStatus.OK);
	}
	
//	// 프로젝트 + 프로젝트 모집글 내용이 함께 상세페이지로 검색
//	@GetMapping("/gettotalpj")
//	public ResponseEntity<RequestWrapper> findAllRecPj(User user, @RequestBody RequestWrapper requestWrapper) {
//		List<Project> pj = projectService.getProjectList(requestWrapper.getProject());
//		List<ProjectRecruit> recs = projectRecruitService.getProjectRecruitList(requestWrapper.getRecruit());
//		List<RequetWrapper> = JsonFormat
//		return new ResponseEntity<>(pj, HttpStatus.OK);
//	}


	// 모집글 총 리스트 출력
	@GetMapping("/getlist")
	public ResponseEntity<List<ProjectRecruit>> findAllRecsList(ProjectRecruit recruit) {
		List<ProjectRecruit> recs = projectRecruitService.getProjectRecruitList(recruit);
		return new ResponseEntity<>(recs, HttpStatus.OK);
	}

	// 프로젝트와 모집글 함께 생성
	@PostMapping("create")
	public ResponseEntity<Project> insertProjectRecruit(User user, @RequestBody RequestWrapper requestWrapper) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("-------insertRecruit--------");
		requestWrapper.getProject().setRecSeq(projectRecruitService.insertProjectRecruit(requestWrapper.getRecruit()));
		System.out.println("-------inserProject--------");
		Project pj = projectService.insertProject(requestWrapper.getProject());

		return new ResponseEntity<>(pj, HttpStatus.CREATED);
	}

	// recSeq로 모집글 조회
	@GetMapping("/get/{id}")
	public ResponseEntity<ProjectRecruit> getRecruitByRecSeq(User user, @PathVariable("id") long id,
			@RequestBody ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			System.out.println("실패1");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			ProjectRecruit rec = projectRecruitService.getProjectRecruit(id);
			System.out.println("성공");
			return new ResponseEntity<>(rec, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// pjSeq로 프로젝트만 불러오기 (Project Controller에서 하면 데이터를 많이 받아오므로 여기에 둠)
	@GetMapping("/getpj/{id}")
	public ResponseEntity<Project> getPjById(User user, @PathVariable("id") long id,
			@RequestBody RequestWrapper requestWrapper) {
		if (user.getUserId() == null) {
			System.out.println("실패1");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			Project pj = projectService.getProject(id);
			System.out.println("프로젝트 불러오기 성공");
			return new ResponseEntity<>(pj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 프로젝트 수정
	@PutMapping("/updatepj/{id}")
	public ResponseEntity<ProjectRecruit> updateRecruit(User user, @PathVariable("id") long id,
			@RequestBody ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ProjectRecruit rec = projectRecruitService.updateProjectRecruit(id, recruit);
		return new ResponseEntity<>(rec, HttpStatus.CREATED);
	}

	// 모집글수정
	@PutMapping("/updaterec/{id}")
	public ResponseEntity<ProjectRecruit> updateRec(User user, @PathVariable("id") long id,
			@RequestBody ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ProjectRecruit rec = projectRecruitService.updateRec(id, recruit);
		return new ResponseEntity<>(rec, HttpStatus.CREATED);

	}

	// 프로젝트 모집글 제거 (트래커 글이 삭제될때 같이 삭제되야 하므로 생각해보고 수정필요)
	@DeleteMapping("/deleterec/{id}")
	public ResponseEntity<HttpStatus> deleteRecruit(User user, @PathVariable("id") long recSeq,
			@RequestBody ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("성공");
		projectRecruitService.deleteProjectRecruit(recruit);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// 프로젝트 제거 트래커 고려해서 수정필요
	@DeleteMapping("/deletepj/{id}")
	public ResponseEntity<HttpStatus> deleteProject(User user, @PathVariable("id") long id, Project project) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		projectService.deleteProject(project);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}