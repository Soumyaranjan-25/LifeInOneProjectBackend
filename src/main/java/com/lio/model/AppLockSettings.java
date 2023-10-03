package com.lio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "t_lio_app_lock_settings")
public class AppLockSettings implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "app_lock_id")
	private Long appLockId;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "lock_app_list")
	private String lockAppList;
	
//	@OneToOne
//	@JoinColumn(name = "user_id")
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "is_app_lock_on")
	private Boolean isAppLockOn;
	
	 
	
	
}
