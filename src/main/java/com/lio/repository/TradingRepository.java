package com.lio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lio.model.Trading;

@Repository
public interface TradingRepository extends JpaRepository<Trading, Long> {
	
	@Query("SELECT DISTINCT t.shareName FROM Trading t WHERE t.shareName LIKE CONCAT(:prefix, '%')")
	List<String> findShareNameSuggestionsByPrefix(@Param("prefix") String prefix);

}
