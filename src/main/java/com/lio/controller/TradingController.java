package com.lio.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lio.dto.ApiResponse;
import com.lio.model.Trading;
import com.lio.service.TradingService;

@RestController
@RequestMapping("/tradingData")
@CrossOrigin("*")
public class TradingController {

	private static final String UPLOAD_DIR = "D:\\LifeInOne\\Life_in_one_frontend\\src\\assets\\tradingData";

	@Autowired
	private TradingService tradingService;
	
	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllTradingData(){
		List<Trading> allTradingData = this.tradingService.getAllTradingData();
		return ResponseEntity.ok(ApiResponse.builder().code(200).message("fetch the Data successfully").data(allTradingData).build());
	}
	
	@GetMapping("/{tradingId}")
	public ResponseEntity<ApiResponse> getTradingDataById(@PathVariable Long tradingId) {
		Trading tradingDataById = tradingService.getTradingDataById(tradingId);
		return ResponseEntity.ok(ApiResponse.builder().data(tradingDataById).code(200).status("success").build());
	}
	
	@PostMapping("/")
	public ResponseEntity<ApiResponse> saveTradingData(@RequestParam("files") List<MultipartFile> files,
			@RequestParam("title") String title, @RequestParam("date") String date,
			@RequestParam("shareName") String shareName, @RequestParam("tradeType") String tradeType,
			@RequestParam("buyingPrice") double buyingPrice, @RequestParam("sellingPrice") double sellingPrice,
			@RequestParam("quantity") int quantity, @RequestParam("profitLoss") String profitLoss,
			@RequestParam("reason") String reason) throws IOException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		List<String> imageFileNames = new ArrayList<>();
		List<String> invalidFiles = new ArrayList<>();
		Integer index = 1;
		for (MultipartFile file : files) {
			String timestamp = dateFormat.format(new Date());
			String uniqueFileName = shareName + "_" + timestamp + "_" + index + ".png";
			try {
				String mimeType = file.getContentType();

				if (mimeType.startsWith("image/")) {
					Path path = Paths.get(UPLOAD_DIR, uniqueFileName);
					Files.write(path, file.getBytes());
					imageFileNames.add(uniqueFileName);
					index++;
				} else {
					invalidFiles.add(file.getOriginalFilename());
				}
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.ok(
						ApiResponse.builder().status("failure").code(201).message("could not save the file").build());
			}
		}
		Trading tradingData = new Trading();
		tradingData.setTitle(title);
		System.out.println(date);
		tradingData.setDate(date);
		tradingData.setShareName(shareName);
		tradingData.setTradeType(tradeType);
		tradingData.setBuyingPrice(buyingPrice);
		tradingData.setSellingPrice(sellingPrice);
		tradingData.setQuantity(quantity);
		tradingData.setProfitLoss(profitLoss);
		tradingData.setReason(reason);
		tradingData.setImages(imageFileNames.toString());
		System.out.println(tradingData);
		tradingService.saveTradingData(tradingData);
		// Create the response object with appropriate message
		String responseMessage;
		if (imageFileNames.isEmpty() && invalidFiles.isEmpty()) {
			responseMessage = "No valid images uploaded.";
		} else if (!imageFileNames.isEmpty() && !invalidFiles.isEmpty()) {
			responseMessage = "Some files uploaded successfully, but others were invalid.";
		} else if (!imageFileNames.isEmpty()) {
			responseMessage = "All files uploaded successfully.";
		} else {
			responseMessage = "No valid images uploaded, please upload PNG images only.";
		}
		return ResponseEntity.ok(ApiResponse.builder().status("success").message(responseMessage).code(200).build());
	}

	@GetMapping("/share-suggestions")
	public ResponseEntity<List<String>> getShareNameSuggestions(@RequestParam("prefix") String prefix) {
		List<String> suggestions = tradingService.getShareNameSuggestions(prefix);
		System.out.println(suggestions);
		return ResponseEntity.ok(suggestions);
	}
	
	

}
