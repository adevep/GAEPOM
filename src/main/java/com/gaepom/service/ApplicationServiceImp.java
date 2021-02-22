package com.gaepom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaepom.dao.ApplicationRepository;
import com.gaepom.domain.Application;
import com.gaepom.domain.Project;

@Service
public class ApplicationServiceImp implements ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepo;

	public List<Application> getApplicationList() {
		return (List<Application>) applicationRepo.findAll();
	}

	public Application insertApplication(Application application) {
		return applicationRepo.save(application);
	}

	public Application getApplication(Application application) {
		return applicationRepo.findById(application.getAplSeq()).get();
	}

	public List<Application> findAppByUserId(String userId) {
		System.out.println(applicationRepo.findApplicationByUserId(userId));
		return applicationRepo.findApplicationByUserId(userId);
	}

	public List<Application> findFailedAppByUserId(String userId) {
		System.out.println(applicationRepo.findFailedAppsByUserId(userId));
		return (List<Application>) applicationRepo.findFailedAppsByUserId(userId);
	}

	public List<Application> findAcceptedAppByUserId(String userId) {
		System.out.println(applicationRepo.findAcceptedAppsByUserId(userId));
		return (List<Application>) applicationRepo.findAcceptedAppsByUserId(userId);
	}

	public List<Application> findAppByPjSeq(Project pjSeq) {
		return (List<Application>) applicationRepo.findApplicationByPjSeq(pjSeq);
	}

	public Application updateApplication(Long id, Application application) {
		Application app = applicationRepo.findById(id).get();
		app.setAplPosi(application.getAplPosi());
		app.setWords(application.getWords());
		app.setSelected(application.getSelected());
		return applicationRepo.save(app);
	}

	public void deleteApplication(Long id) {
		applicationRepo.deleteById(id);
	}
}
