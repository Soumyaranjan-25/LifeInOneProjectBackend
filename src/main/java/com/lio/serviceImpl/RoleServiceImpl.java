package com.lio.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lio.model.Role;
import com.lio.repository.RoleRepository;
import com.lio.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	

	@Override
	public Role getRoleByName(String roleName) {
		return this.roleRepository.findByRoleName(roleName).get(0);
	}

}
