package com.gaepom.controller;

import java.io.PrintStream;
import java.util.List;

import org.hibernate.annotations.common.reflection.java.generics.TypeEnvironmentFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.Application;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;
import com.gaepom.service.ApplicationService;

import aj.org.objectweb.asm.Type;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("app")
@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	// 지원 앱 생성 (완료)
	@PostMapping("/create")
	public ResponseEntity<Application> insertApplication(User user, Application application) {
		if (user.getUserId() == null) {
			// 바꿔야함
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("-------insertApplication--------");
		Application app = applicationService.insertApplication(application);
		return new ResponseEntity<>(app, HttpStatus.CREATED);
	}

	// 주최자 프로젝트 시점에 지원자들앱 보기
	@GetMapping("/getpjapp/{pjSeq}")
	public ResponseEntity<List<Application>> findAppByPjSeq(User user, @PathVariable("pjSeq") Project pjSeq) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findAppByPjSeq(pjSeq);
			System.out.println(apps);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// user id로 selected 가 0인경우 해당 지원서 보기
	// 지원진행중인 앱 보기 (수락 거절 안받은) 초기생성값
	@GetMapping("/getapp/{userId}")
	public ResponseEntity<List<Application>> findAppByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findAppByUserId(userId);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getlist")
	public ResponseEntity<List<Application>> findAllApps() {
		List<Application> apps = applicationService.getApplicationList();
		return new ResponseEntity<>(apps, HttpStatus.OK);
	}

	// 거절당한 앱 보기 1이면 거정
	@GetMapping("/rejectapp/{userId}")
	public ResponseEntity<List<Application>> findFailedAppByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findFailedAppByUserId(userId);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 수락한 앱 보기 2 면 수락
	@GetMapping("/acceptapp/{userId}")
	public ResponseEntity<List<Application>> findAcceptedAppByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findAcceptedAppByUserId(userId);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 앱 수정
	// 시험 데이터 http://localhost:80/app/update/8?userId=user1
	@PutMapping("/update/{id}")
	public ResponseEntity<Application> updateApplication(User user, @PathVariable("id") long id,
			@RequestBody Application application) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("성공");
		Application app = applicationService.updateApplication(id, application);
		return new ResponseEntity<>(app, HttpStatus.CREATED);
	}

	// 앱 제거
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteApplication(User user, @PathVariable("id") long id) {
		if (user.getUserId() == null) {
			System.out.println("실패");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("성공");
		applicationService.deleteApplication(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}