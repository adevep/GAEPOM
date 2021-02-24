package com.gaepom.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.dao.CommentRepository;
import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.ProjectTrackingRepository;
import com.gaepom.domain.Comment;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;
import com.gaepom.exception.ProjectTrackingException;
import com.gaepom.exception.ProjectTrackingNotFoundException;

@Service
public class ProjectTrackingServicImpl implements ProjectTrackingService {
	@Autowired
	private ProjectTrackingRepository trackingRepo;

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private CommentRepository commentRepo;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<ProjectTracking> getProjectTrackingList() {
		return (List<ProjectTracking>) trackingRepo.findAll();
	}

	@Transactional
	public ProjectTracking insertProjectTracking(ProjectTracking tracking, Project project, User user,
			MultipartFile mfile) {
		
		String imgname = null;
		Optional<ProjectTracking> findTracking = trackingRepo.findById(tracking.getTrackSeq());

		if (!findTracking.isPresent()) {
			
			try {
				imgname = String.valueOf(System.currentTimeMillis()) + mfile.getOriginalFilename();
				mfile.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + imgname));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				logger.debug(project.getPjTitle() + " 트래킹 등록시 오류 발생");
			}

			tracking.setTrackImage(imgname);
			project.setUserId(user);
			tracking.setProject(project);
			
			projectRepo.save(project);
			trackingRepo.save(tracking);

			logger.info(project.getPjTitle() + " 트래킹 등록완료");
			return tracking;
		} else {
			throw new ProjectTrackingException("이미 생성된 트래킹 글입니다");
		}
	}

	public ProjectTracking insertProjectTrackingNoImg(ProjectTracking tracking, Project project, User user) {
		Optional<ProjectTracking> findTracking = trackingRepo.findById(tracking.getTrackSeq());

		if (!findTracking.isPresent()) {

			tracking.setTrackImage("pj.jpg");
			project.setUserId(user);
			tracking.setProject(project);
			
			projectRepo.save(project);
			trackingRepo.save(tracking);

			logger.info(project.getPjTitle() + " 트래킹 등록완료");
			return tracking;
		} else {
			throw new ProjectTrackingException("이미 생성된 트래킹 글입니다");
		}
	}

	public ProjectTracking getProjectTracking(Long tracking) {
		Optional<ProjectTracking> findTracking = trackingRepo.findById(tracking);

		if (!findTracking.isPresent()) {
			return trackingRepo.findById(tracking).get();
		} else {
			throw new ProjectTrackingNotFoundException("존재하지 않는 트래킹 글입니다");
		}
	}

	public void updateProjComment(Comment comment) {
		Optional<Comment> findComment = commentRepo.findById(comment.getCmtSeq());

		if (!findComment.isPresent()) {
			ProjectTracking findTracking = trackingRepo.findById(comment.getTrackSeq().getTrackSeq()).get();
			findTracking.getTcomments().add(comment);

			trackingRepo.save(findTracking);
		} else {
			throw new ProjectTrackingNotFoundException("존재하지 않는 댓글입니다");
		}
	}

	public ProjectTracking updateProjectTracking(ProjectTracking tracking, Project project, User user) {
		Optional<ProjectTracking> findProjectTracking = trackingRepo.findById(tracking.getTrackSeq());

		if (findProjectTracking.isPresent()) {

			Project findProject = projectRepo.findById(project.getPjSeq()).get();
			
			findProject.setPjTitle(project.getPjTitle());
			findProject.setPjDescription(project.getPjDescription());
			findProject.setPjDuration(project.getPjDuration());
			findProject.setPjTools(project.getPjTools());
			findProject.setPjCategory(project.getPjCategory());
			findProject.setPjLang(project.getPjLang());
			findProject.setPjDbms(project.getPjDbms());
			findProject.setUserId(user);
			findProjectTracking.get().setStage(tracking.getStage());
			findProjectTracking.get().setIssue(tracking.getIssue());
			findProjectTracking.get().setOutput(tracking.getOutput());
			findProjectTracking.get().setTrackLink(tracking.getTrackLink());

			projectRepo.save(findProject);
			trackingRepo.save(findProjectTracking.get());

			logger.info(project.getPjTitle() + " 정보 업데이트 완료");
			return findProjectTracking.get();
		} else {
			throw new ProjectTrackingNotFoundException("존재하지 않는 트래킹 글입니다");
		}
	}

	public ProjectTracking updateProjectTrackingImg(ProjectTracking tracking, Project project, User user,
			MultipartFile mfile) {
		
		Optional<ProjectTracking> findProjectTracking = trackingRepo.findById(tracking.getTrackSeq());

		if (findProjectTracking.isPresent()) {
			String imgname = null;
			try {
				
				imgname = String.valueOf(System.currentTimeMillis()) + mfile.getOriginalFilename();
				mfile.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + imgname));

				String filename = findProjectTracking.get().getTrackImage();
				File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

				if (file.exists() && !filename.equals("default.png")) {
					if (file.delete()) {
						logger.debug("사진 파일 삭제 완료");
					} else {
						logger.debug("사진 파일 삭제 실패");
					}
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				logger.debug("오류 발생");
			}

			Project findProject = projectRepo.findById(project.getPjSeq()).get();
			
			findProject.setPjTitle(project.getPjTitle());
			findProject.setPjDescription(project.getPjDescription());
			findProject.setPjDuration(project.getPjDuration());
			findProject.setPjTools(project.getPjTools());
			findProject.setPjCategory(project.getPjCategory());
			findProject.setPjLang(project.getPjLang());
			findProject.setPjDbms(project.getPjDbms());
			findProject.setUserId(user);
			findProjectTracking.get().setStage(tracking.getStage());
			findProjectTracking.get().setIssue(tracking.getIssue());
			findProjectTracking.get().setOutput(tracking.getOutput());
			findProjectTracking.get().setTrackImage(imgname);
			findProjectTracking.get().setTrackLink(tracking.getTrackLink());

			projectRepo.save(findProject);
			trackingRepo.save(findProjectTracking.get());

			logger.info(project.getPjTitle() + " 정보 업데이트 완료");
			return findProjectTracking.get();
		} else {
			throw new ProjectTrackingNotFoundException("존재하지 않는 트래킹 글입니다");
		}
	}

	public ProjectTracking updateTrackingLike(Long trackSeq, int trackLike) {
		Optional<ProjectTracking> findProjectTracking = trackingRepo.findById(trackSeq);

		if (findProjectTracking.isPresent()) {
			ProjectTracking findTracking = trackingRepo.findById(trackSeq).get();
			findTracking.setTrackLike(trackLike);

			trackingRepo.save(findTracking);
			logger.info(findProjectTracking.get().getTrackSeq() + " 좋아요 수정 완료");
			return findTracking;
		} else {
			throw new ProjectTrackingNotFoundException("존재하지 않는 트래킹 글입니다");
		}
	}

	public void deleteProjectTracking(Long trackSeq) {

		Optional<ProjectTracking> findProjectTracking = trackingRepo.findById(trackSeq);

		if (findProjectTracking.isPresent()) {

			String filename = findProjectTracking.get().getTrackImage();

			File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

			if (file.exists() && !filename.equals("default.png")) {
				if (file.delete()) {
					logger.info(trackSeq + "tracking 사진 파일 삭제");
				} else {
					logger.debug("tracking 사진 파일 삭제 실패");
				}
			}
			trackingRepo.delete(findProjectTracking.get());
			logger.info(trackSeq + " tracking 삭제 완료");
		} else {
			throw new ProjectTrackingNotFoundException("해당하는 tracking이 없습니다");
		}
	}
}