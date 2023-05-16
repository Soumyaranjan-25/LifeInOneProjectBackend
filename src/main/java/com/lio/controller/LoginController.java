package com.lio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lio.model.Role;
import com.lio.repository.RoleRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private RoleRepository roleRepository;
	@RequestMapping("/")
	public void insertData() {
		this.roleRepository.saveAll(List.of(new Role("Admin"),new Role("Normal")));
	}
	
	
}
