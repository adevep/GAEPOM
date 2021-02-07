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

	public void updateApplication(Application application) {
		Application findApplication = applicationRepo.findById(application.getAplSeq()).get();

		findApplication.setSelected(application.getSelected());
		applicationRepo.save(findApplication);
	}

	public void deleteApplication(Application application) {
		applicationRepo.deleteById(application.getAplSeq());
	}

}
