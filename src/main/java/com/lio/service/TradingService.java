package com.lio.service;

import java.util.List;

import com.lio.model.Trading;

public interface TradingService {

	Trading saveTradingData(Trading tradingData);

	List<String> getShareNameSuggestions(String prefix);

	List<Trading> getAllTradingData();

	Trading getTradingDataById(Long tradingId);

}
