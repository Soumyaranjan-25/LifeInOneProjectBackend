package com.lio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lio.dto.ApiResponse;
import com.lio.model.App;
import com.lio.model.AppLockSettings;
import com.lio.model.User;
import com.lio.service.AppLockSettingsService;
import com.lio.service.AppService;

@RestController
@RequestMapping("/app")
@CrossOrigin("*")
public class AppController {

	@Autowired
	private AppService appService;
	
	@Autowired
	private AppLockSettingsService appLockSettingsService;
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getApps(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 User user=(User) authentication.getPrincipal();
		List<App> apps = this.appService.getAllApps();
		return ResponseEntity.ok(ApiResponse.builder().code(200).message("fetch the Data successfully").data(apps).build());
	}
	
	@GetMapping("/getAppLockSettings")
	public ResponseEntity<ApiResponse> getAppLockSettings(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 User user=(User) authentication.getPrincipal();
		AppLockSettings appLockSettings = this.appLockSettingsService.getAppLockSettingByUser(user);
		System.out.println(appLockSettings);
		return ResponseEntity.ok(ApiResponse.builder().code(200).message("fetch the Data successfully").data(appLockSettings).build());
	}
	
	@PostMapping("/saveLockSettings")
	public ResponseEntity<ApiResponse> saveLockSettings(@RequestBody AppLockSettings appLockSettings){
		System.out.println(appLockSettings);
		AppLockSettings saveAppLockSettings = this.appLockSettingsService.save(appLockSettings);
		if(saveAppLockSettings != null) {
			return ResponseEntity.ok(ApiResponse.builder().code(200).message("Save the AppLock Settings successfully").data(saveAppLockSettings).build());
		}
		else {
			return ResponseEntity.ok(ApiResponse.builder().code(201).message("Error in save appLock Settings").data(appLockSettings).build());
		}
	}
	
}
