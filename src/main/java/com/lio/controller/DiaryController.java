package com.lio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lio.model.Diary;
import com.lio.service.DiaryService;

@RestController
@RequestMapping("/diary")
@CrossOrigin("*")
public class DiaryController {

	@Autowired
	private DiaryService diaryService;

	@PostMapping("/")
	public ResponseEntity<?> saveDiaryContent(@RequestBody Diary diary) {
		System.out.println(diary);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		diary.setDateOfWriting(dateFormat.format(new Date()).toString());
		diary.setUserId(1);
		Diary saveDiary = diaryService.saveDiary(diary);
		if (saveDiary != null) {
			return ResponseEntity.ok("{\"message\": \"Success\"}");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failure\"}");
	}

	@GetMapping("/")
	public ResponseEntity<List<Diary>> getAllDiaryByUser() {
		List<Diary> diaries = diaryService.getAllDiaryByUserId(1);
		return ResponseEntity.ok(diaries);
	}

	@GetMapping("/{diaryId}")
	public ResponseEntity<Diary> getDiaryById(@PathVariable Long diaryId) {
		Diary diary = diaryService.getDiaryById(diaryId);
		if (diary != null) {
			return ResponseEntity.ok(diary);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
