package com.lio.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lio.model.App;
import com.lio.repository.AppRepository;
import com.lio.service.AppService;

@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private AppRepository appRepository;
	@Override
	public List<App> getAllApps() {
		return appRepository.findAll();
	}

}
