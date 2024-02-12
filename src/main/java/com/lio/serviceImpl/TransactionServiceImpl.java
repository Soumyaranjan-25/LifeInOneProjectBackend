package com.lio.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lio.config.EncryptionService;
import com.lio.model.Transaction;
import com.lio.repository.TransactionRepository;
import com.lio.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String saveCreditDetails(Map<String, String> data) throws Exception {
        String encryptedData = data.get("encryptedData");
		String decryptedData = encryptionService.decryptData(encryptedData);

		Transaction creditDetails = objectMapper.readValue(decryptedData, Transaction.class);
		creditDetails.setTrasactionType(1);
		creditDetails.setActiveStatus(0);
		Transaction save = transactionRepository.save(creditDetails);

		return null;
	}

}
