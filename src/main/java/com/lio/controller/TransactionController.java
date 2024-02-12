package com.lio.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lio.dto.ApiResponse;
import com.lio.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/saveCreditDetails")
	public ResponseEntity<ApiResponse> saveCreditDetails(@RequestBody Map<String,String> data) {
		try {
			transactionService.saveCreditDetails(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(ApiResponse.builder().status("success").build());
	}
}
