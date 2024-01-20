package com.lio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lio.dto.ApiResponse;

@Controller
public class TransactionController {
	
	@PostMapping("saveCreditDetails")
	public ResponseEntity<ApiResponse> saveCreditDetails(@RequestBody String data) {
		
		return ResponseEntity.ok(ApiResponse.builder().status("success").build());
	}
}
