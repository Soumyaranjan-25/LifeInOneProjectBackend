package com.lio.serviceImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.hibernate.internal.util.compare.ComparableComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lio.model.Trading;
import com.lio.repository.TradingRepository;
import com.lio.service.TradingService;

import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Collections;

@Repository
public class TradingServiceImpl implements TradingService {

	@Autowired
	private TradingRepository tradingRepository;
	
	@Override
	public Trading saveTradingData(Trading tradingData) {
		return tradingRepository.save(tradingData);
	}

	@Override
	public List<String> getShareNameSuggestions(String prefix) {
		return tradingRepository.findShareNameSuggestionsByPrefix(prefix);
	}

	@Override
	public List<Trading> getAllTradingData() {
		return tradingRepository.findAll();
	}

	@Override
	public Trading getTradingDataById(Long diaryId) {
		return this.tradingRepository.findById(diaryId).get();
	}

}
