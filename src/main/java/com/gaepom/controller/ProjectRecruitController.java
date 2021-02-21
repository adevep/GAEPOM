package com.gaepom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.dao.UserRepository;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectRecruit;
import com.gaepom.domain.RequestWrapper;
import com.gaepom.domain.User;
import com.gaepom.service.ProjectRecruitService;
import com.gaepom.service.ProjectService;
import com.gaepom.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(value = "recruit")
public class ProjectRecruitController {
	@Autowired
	private ProjectRecruitService projectRecruitService;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 프로젝트 총 리스트 출력 (고쳐서 두개다 추력하게)
	@GetMapping("/getpjs")
	public ResponseEntity<List<Project>> findAllRecs(User user, Project project) {
		if (user.getUserId() == null) {
			logger.info("유저 아이디가 존재하지 않습니다.");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Project> pjs = projectService.getProjectList(project);
		return new ResponseEntity<>(pjs, HttpStatus.OK);
	}

	// 프로젝트 + 프로젝트 모집글 내용이 함께 상세페이지로 검색
	@GetMapping("/gettotalpj")
	public ResponseEntity<List<Object>> findAllRecPj(User user, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Object> total = projectRecruitService.getTotalRecruitList(recruit);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}

	// 프로젝트 + 프로젝트 모집글 내용이 함께 상세페이지로 검색
	@GetMapping("/gettotalpj/{id}")
	public ResponseEntity<List<Object>> findAllRecPj(User user, ProjectRecruit recruit,
			@PathVariable("id") long pjSeq) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Object> total = projectRecruitService.getTotalRecruitByPjSeq(pjSeq, recruit);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}

	// 지역으로 모집글 검색 (필터링)
	@GetMapping("/getpjbylo/{loc}")
	public ResponseEntity<List<Object>> findRecPjByLocation(User user, @PathVariable("loc") String location) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Object> pjs = projectRecruitService.getTotalRecruitByLocation(location);
		return new ResponseEntity<>(pjs, HttpStatus.OK);
	}

	// 카테고리로 모집글 검색 (필터링)
	@GetMapping("/getpjbycate/{cate}")
	public ResponseEntity<List<Object>> findRecPjByCategory(User user, @PathVariable("cate") String pjCategory) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Object> pjs = projectRecruitService.getTotalRecruitByCategory(pjCategory);
		return new ResponseEntity<>(pjs, HttpStatus.OK);
	}

	// 모집글 총 리스트 출력
	@GetMapping("/getrecs")
	public ResponseEntity<List<ProjectRecruit>> findAllRecsList(User user, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<ProjectRecruit> recs = projectRecruitService.getProjectRecruitList(recruit);
		return new ResponseEntity<>(recs, HttpStatus.OK);
	}
	
	// 프로젝트 번호로 모집글 검색 (필터링)
	@GetMapping("/getbypj/{id}")
	public ResponseEntity<Object> getRecByPj(User user, @PathVariable("id") Long pjSeq, ProjectRecruit recruit ) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Object needNum = projectRecruitService.getRecByPj(pjSeq, recruit);
		
		return new ResponseEntity<>(needNum, HttpStatus.OK);
	}
	
	@GetMapping("/getbypjcheckcount/{id}")
	public ResponseEntity<Object> getRecByPjCheckCount(User user, @PathVariable("id") Long pjSeq, ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Object checkCount = projectRecruitService.getCheckCountByPj(pjSeq, recruit);
		System.out.println(checkCount);
		
		return new ResponseEntity<>(checkCount, HttpStatus.OK);
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

	@PostMapping("createpj")
	public ResponseEntity<Project> insertProject(User user, @RequestBody Project project) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("-------inserProject--------");
		Project pj = projectService.insertProject(project);
		return new ResponseEntity<>(pj, HttpStatus.CREATED);
	}

	@PostMapping("createrec")
	public ResponseEntity<ProjectRecruit> insertProjectRec(User user, @RequestBody ProjectRecruit recruit) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("-------insertRecruit--------");

		ProjectRecruit rec = projectRecruitService.insertProjectRecruit(recruit);
		return new ResponseEntity<>(rec, HttpStatus.CREATED);
	}

	// recSeq로 모집글 조회
	@GetMapping("/get/{id}")
	public ResponseEntity<ProjectRecruit> getRecruitByRecSeq(User user, @PathVariable("id") long id) {
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

	@GetMapping("/gethostedpj/{userId}")
	public ResponseEntity<List<Project>> getPjById(@PathVariable("userId") User userId, Project project) {
		if (userId.getUserId() == null) {
			System.out.println("실패1");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Project> pj = projectService.getPjByUserId(project, userId);
			System.out.println("프로젝트 불러오기 성공");
			return new ResponseEntity<>(pj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/gethostedpj2/{userId}")
	public ResponseEntity<List<Project>> getPjById(@PathVariable("userId") String userid, Project project) {
		
		if (userService.getUser(userid) == null) {
			System.out.println("실패1");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {	
			List<Project> pj = projectService.getPjByUserId2(project, userid);
			System.out.println("프로젝트 불러오기 성공");
			return new ResponseEntity<>(pj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/gethostedpj2/{userId}")
	public ResponseEntity<List<Project>> getPjById(@PathVariable("userId") String userid, Project project) {
		
		if (userService.getUser(userid) == null) {
			System.out.println("실패1");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {	
			List<Project> pj = projectService.getPjByUserId2(project, userid);
			System.out.println("프로젝트 불러오기 성공");
			return new ResponseEntity<>(pj, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updatepj/{id}")
	public ResponseEntity<Project> updateProject(User user, @PathVariable("id") long id, @RequestBody Project project) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("성공");
		Project pj = projectService.updateProject(id, project);
		return new ResponseEntity<>(pj, HttpStatus.CREATED);
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
	
	@PutMapping("/updatereccount/{id}")
	public ResponseEntity<ProjectRecruit> updateRecCount(User user, @PathVariable("id") long id, @RequestParam int checkCount) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ProjectRecruit rec = projectRecruitService.updateRecCount(id, checkCount);
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
