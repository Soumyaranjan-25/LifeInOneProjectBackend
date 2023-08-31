package com.lio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lio.model.App;

@Repository
public interface AppRepository extends JpaRepository<App, Integer> {

}
