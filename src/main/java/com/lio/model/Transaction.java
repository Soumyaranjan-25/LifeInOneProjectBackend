package com.lio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@Table(name = "t_lio_transaction")
public class Transaction implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_Id")
	private Integer trascationId;
	
	@Column(name = "transaction_Date")
	private Date trasactionDate;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "transaction_From")
	private String transactionFrom;
	
	@Column(name = "remark")
	private String remark;
	
	/* 1 - Credit
	 * 2 - Debit
	 */
	@Column(name = "transaction_Type")
	private int trasactionType;
	
	/* 0 - Active
	 * 1 - Inactive
	 */
	@Column(name = "active_Status")
	private int activeStatus;
}
