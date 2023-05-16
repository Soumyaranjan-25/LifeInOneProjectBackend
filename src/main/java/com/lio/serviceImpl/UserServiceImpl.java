	package com.lio.serviceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lio.model.User;
import com.lio.repository.RoleRepository;
import com.lio.repository.UserRepository;
import com.lio.service.RoleService;
import com.lio.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;

	@Override
	public User saveUser(User user) {
		 
		user.setIsActive("true");
		user.setRole(this.roleService.getRoleByName("Normal"));
		user.setSignInOn(LocalDate.now().toString());
		User saveUser = this.userRepository.save(user);
		return saveUser;
	}

	@Override
	public User checkUser(String userName, String password) {
		User loginUser = this.userRepository.findByUserNameAndPassword(userName,password);
		return loginUser;
	}

	@Override
	public List<User> getUsers() {
		return this.userRepository.findAllActiveUser();
	}

	@Override
	public User getUserByName(String username) {
		return userRepository.findByUserName(username);
	}

}
