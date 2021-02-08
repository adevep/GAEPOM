package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ApplicationRepository;
import com.gaepom.domain.Application;

@Service
public class ApplicationServiceImp implements ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepo;

	public List<Application> getApplicationList(Application application) {
		return (List<Application>) applicationRepo.findAll();
	}

	public void insertApplication(Application application) {
		applicationRepo.save(application);
	}

	public Application getApplication(Application application) {
		return applicationRepo.findById(application.getAplSeq()).get();
	}

	public List<Application> findAppByUserId(String userId) {
		System.out.println("진짜 나오나" + userId);
		System.out.println(applicationRepo.findApplicationByUserId(userId));
		return (List<Application>)applicationRepo.findApplicationByUserId(userId);
//		Application findApp = applicationRepo.findApplicationByUserId(userId);
//		if (findApp != null) {
//			return findApp;
//		} else {
//			throw new UserNotFoundException("해당 애플리케이션을 찾을수 없습니다.");
//		}
	}

	public void updateApplication(Application application) {
		Application findApplication = applicationRepo.findById(application.getAplSeq()).get();

		findApplication.setSelected(application.getSelected());
		applicationRepo.save(findApplication);
	}

	public void deleteApplication(Application application) {
		applicationRepo.deleteById(application.getAplSeq());
	}

}
