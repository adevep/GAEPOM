package com.gaepom.service;

import java.util.List;

import com.gaepom.domain.Application;

public interface ApplicationService {

	List<Application> getApplicationList(Application application);

	void insertApplication(Application application);

	Application getApplication(Application application);

	void updateApplication(Application application);

	void deleteApplication(Application application);

	List<Application> findAppByUserId(String userId);

	List<Application> findFailedAppByUserId(String userId);

}
