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
import org.springframework.web.bind.annotation.RestController;

import com.gaepom.domain.Application;
import com.gaepom.domain.Project;
import com.gaepom.domain.User;
import com.gaepom.service.ApplicationService;

@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("app")
@RestController
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/create")
	public ResponseEntity<Application> insertApplication(User user, Application application) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Application app = applicationService.insertApplication(application);
		logger.info("{} 지원 정보 생성 완료", app.getAplSeq());
		return new ResponseEntity<>(app, HttpStatus.CREATED);
	}

	@GetMapping("/getpjapp/{pjSeq}")
	public ResponseEntity<List<Application>> findAppByPjSeq(User user, @PathVariable("pjSeq") Project pjSeq) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findAppByPjSeq(pjSeq);
			logger.info("{} 프로젝트 지원 정보 조회 완료", pjSeq);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("{} 프로젝트 지원 정보 조회 실패", pjSeq);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getapp/{userId}")
	public ResponseEntity<List<Application>> findAppByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findAppByUserId(userId);
			logger.info("{} 유저 지원 정보 조회 완료", userId);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("{} 유저 지원 정보 조회 실패", userId);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getlist")
	public ResponseEntity<List<Application>> findAllApps() {
		List<Application> apps = applicationService.getApplicationList();
		logger.info("{} 지원 전체 정보 조회");
		return new ResponseEntity<>(apps, HttpStatus.OK);
	}

	// 거절당한 앱 보기 1이면 거절
	@GetMapping("/rejectapp/{userId}")
	public ResponseEntity<List<Application>> findFailedAppByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findFailedAppByUserId(userId);
			logger.info("{} 유저 지원 정보 조회 완료", userId);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("{} 유저 지원 정보 조회 실패", userId);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 수락한 앱 보기 2 면 수락
	@GetMapping("/acceptapp/{userId}")
	public ResponseEntity<List<Application>> findAcceptedAppByUserId(User user, @PathVariable("userId") String userId) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			List<Application> apps = applicationService.findAcceptedAppByUserId(userId);
			logger.info("{} 유저 지원 정보 조회 완료", userId);
			return new ResponseEntity<>(apps, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("{} 유저 지원 정보 조회 실패", userId);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 앱 수정
	@PutMapping("/update/{id}")
	public ResponseEntity<Application> updateApplication(User user, @PathVariable("id") long id,
			@RequestBody Application application) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Application app = applicationService.updateApplication(id, application);
		logger.info("{} 지원 정보 갱신 완료", id);
		return new ResponseEntity<>(app, HttpStatus.CREATED);
	}

	// 앱 제거
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteApplication(User user, @PathVariable("id") long id) {
		if (user.getUserId() == null) {
			logger.error("{} 미 존재 회원 요청", user.getUserId());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		applicationService.deleteApplication(id);
		logger.info("{} 지원 정보 삭제 완료", id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}