package com.lio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lio.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("From Role where roleName= :roleName")
	List<Role> findByRoleName(String roleName);

}
