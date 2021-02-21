package com.gaepom.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gaepom.dao.ProjectRepository;
import com.gaepom.dao.ProjectTrackingRepository;
import com.gaepom.domain.Comment;
import com.gaepom.domain.Project;
import com.gaepom.domain.ProjectTracking;
import com.gaepom.domain.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectTrackingServicImpl implements ProjectTrackingService {
	@Autowired
	private ProjectTrackingRepository trackingRepo;

	@Autowired
	private ProjectRepository projectRepo;

	public List<ProjectTracking> getProjectTrackingList(ProjectTracking tracking) {
		return (List<ProjectTracking>) trackingRepo.findAll();
	}

	public List<ProjectTracking> getProjectTrackingList2() {
		return (List<ProjectTracking>) trackingRepo.findAll();
	}

	@Transactional
	public ProjectTracking insertProjectTracking(ProjectTracking tracking, Project project, User user,
			MultipartFile mfile) {
		String imgname = null;
		System.out.println("================================" + mfile);
		try {
			imgname = String.valueOf(System.currentTimeMillis()) + mfile.getOriginalFilename();
			mfile.transferTo(new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + imgname));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();

		}

		System.out.println("================================" + mfile);
		System.out.println("================================" + imgname);
		tracking.setTrackImage(imgname);
		project.setUserId(user);
		tracking.setProject(project);
		projectRepo.save(project);
		trackingRepo.save(tracking);

		return tracking;
	}

	public ProjectTracking insertProjectTrackingNoImg(ProjectTracking tracking, Project project, User user) {

		project.setUserId(user);
		tracking.setProject(project);
		projectRepo.save(project);
		tracking.setTrackImage("default.png");
		trackingRepo.save(tracking);
		return tracking;
	}

	public ProjectTracking getProjectTracking(Long tracking) {
		return trackingRepo.findById(tracking).get();
	}

	public void updateProjComment(Comment comment) {
		ProjectTracking findTracking = trackingRepo.findById(comment.getTrackSeq().getTrackSeq()).get();
		findTracking.getTcomments().add(comment);

		trackingRepo.save(findTracking);
	}

	public ProjectTracking updateProjectTracking(ProjectTracking tracking, Project project, User user) {
		Optional<ProjectTracking> findProjectTracking = trackingRepo.findById(tracking.getTrackSeq());

		if (findProjectTracking.isPresent()) {

			String filename = findProjectTracking.get().getTrackImage();
			File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

			if (file.exists() && !filename.equals("default.png")) {
				if (file.delete()) {
					log.debug("사진 파일 삭제 완료");
				} else {
					log.debug("사진 파일 삭제 실패");
				}
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
			findProjectTracking.get().setTrackImage("default.png");
			findProjectTracking.get().setTrackLink(tracking.getTrackLink());

			projectRepo.save(findProject);
			trackingRepo.save(findProjectTracking.get());

			return findProjectTracking.get();
		} else {
			return null;
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
						log.debug("사진 파일 삭제 완료");
					} else {
						log.debug("사진 파일 삭제 실패");
					}
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				log.debug("오류 발생");
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

			return findProjectTracking.get();
		} else {
			return null;
		}
	}

	public ProjectTracking updateTrackingLike(Long trackSeq, int trackLike) {
		ProjectTracking findTracking = trackingRepo.findById(trackSeq).get();
		findTracking.setTrackLike(trackLike);

		trackingRepo.save(findTracking);
		System.out.println("트래킹 좋아요 수정완료");
		return findTracking;
	}

	public void deleteProjectTracking(Long trackSeq) {

		Optional<ProjectTracking> findProjectTracking = trackingRepo.findById(trackSeq);

		if (findProjectTracking.isPresent()) {

			String filename = findProjectTracking.get().getTrackImage();

			File file = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\upload\\" + filename);

			if (file.exists() && !filename.equals("default.png")) {
				if (file.delete()) {
					log.info(trackSeq + "tracking 사진 파일 삭제");
				} else {
					log.debug("tracking 사진 파일 삭제 실패");
				}
			}
			trackingRepo.delete(findProjectTracking.get());
			log.info(trackSeq + " tracking 삭제 완료");
		} else {
//			throw new ProjectTrackingNotFoundException("해당하는 tracking이 없습니다");
		}
	}
}