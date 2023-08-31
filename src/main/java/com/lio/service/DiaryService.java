package com.lio.service;

import java.util.List;

import com.lio.model.Diary;

public interface DiaryService {

	Diary saveDiary(Diary diary);

	List<Diary> getAllDiaryByUserId(Integer userId);

	Diary getDiaryById(Long diaryId);

}
