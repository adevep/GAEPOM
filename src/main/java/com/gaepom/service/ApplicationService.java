package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Application;
import com.gaepom.domain.Project;

public interface ApplicationService {

	List<Application> getApplicationList();

	Application insertApplication(Application application);

	Application getApplication(Application application);

	Application updateApplication(Long id, Application application);

	void deleteApplication(Long id);

	List<Application> findAppByPjSeq(Project pjSeq);

	List<Application> findAppByUserId(String userId);

	List<Application> findFailedAppByUserId(String userId);

	List<Application> findAcceptedAppByUserId(String userId);

}
