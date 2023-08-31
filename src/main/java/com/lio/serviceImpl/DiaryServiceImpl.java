package com.lio.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lio.model.Diary;
import com.lio.repository.DiaryRepository;
import com.lio.service.DiaryService;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	private DiaryRepository diaryRepository;
	@Override
	public Diary saveDiary(Diary diary) {
		return diaryRepository.save(diary);
	}
	@Override
	public List<Diary> getAllDiaryByUserId(Integer userId) {
		return diaryRepository.findByUserId(userId);
	}
	@Override
	public Diary getDiaryById(Long diaryId) {
		// TODO Auto-generated method stub
		return diaryRepository.findById(diaryId).get();
	}

}
