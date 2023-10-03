package com.lio.service;

import com.lio.model.AppLockSettings;
import com.lio.model.User;

public interface AppLockSettingsService {

	AppLockSettings getAppLockSettingByUser(Integer userId);

	AppLockSettings save(AppLockSettings appLockSettings);

}
