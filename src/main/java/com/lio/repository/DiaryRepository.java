package com.lio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lio.model.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
	List<Diary> findByUserId(Integer userId);
}
