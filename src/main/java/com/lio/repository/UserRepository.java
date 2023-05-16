package com.lio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lio.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("from User where userName=:userName and password=:password")
	User findByUserNameAndPassword(String userName, String password);

	@Query("from User where isActive='true'") 
	List<User> findAllActiveUser();
	
	@Query("from User where userName=:userName") 
	User findByUserName(String userName);
}
