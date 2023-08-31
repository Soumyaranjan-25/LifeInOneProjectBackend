package com.lio.service;

import com.lio.model.AppLockSettings;
import com.lio.model.User;

public interface AppLockSettingsService {

	AppLockSettings getAppLockSettingByUser(User user);

	AppLockSettings save(AppLockSettings appLockSettings);

}
