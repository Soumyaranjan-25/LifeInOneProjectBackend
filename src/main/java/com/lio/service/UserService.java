package com.lio.service;

import java.util.List;

import com.lio.model.User;

public interface UserService {

	User saveUser(User user);

	User checkUser(String userName, String password);
 
	List<User> getUsers();

	User getUserByName(String username);

}
