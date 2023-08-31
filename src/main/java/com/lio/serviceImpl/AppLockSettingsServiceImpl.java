package com.lio.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lio.model.AppLockSettings;
import com.lio.model.User;
import com.lio.repository.AppLockSettingsRepository;
import com.lio.service.AppLockSettingsService;

@Service
public class AppLockSettingsServiceImpl implements AppLockSettingsService {

	
	@Autowired
	private AppLockSettingsRepository appLockSettingsRepository;
	
	@Override
	public AppLockSettings getAppLockSettingByUser(User user) {
		return appLockSettingsRepository.findByUser(user);
	}

	@Override
	public AppLockSettings save(AppLockSettings appLockSettings) {
		return appLockSettingsRepository.save(appLockSettings);
	}

}
