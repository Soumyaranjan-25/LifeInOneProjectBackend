package com.lio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_lio_trading_data")
public class Trading implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trading_data_id")
	private Long tradingId;

	@Column(name = "trading_title")
	private String title;

	@Column(name = "trading_date")
	private String date;

	@Column(name = "share_name")
	private String shareName;

	@Column(name = "trade_type")
	private String tradeType;

	@Column(name = "buying_price")
	private double buyingPrice;

	@Column(name = "selling_price")
	private double sellingPrice;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "profit_loss")
	private String profitLoss;

	@Column(name = "reason")
	private String reason;
	
	@Column(name = "images")
//	@ElementCollection
	private String images;
	
}
