package com.lio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lio.model.AppLockSettings;
import com.lio.model.User;

@Repository
public interface AppLockSettingsRepository extends JpaRepository<AppLockSettings, Integer> {
	@Query("from AppLockSettings where user =:user")
	AppLockSettings findByUser(User user);
}
