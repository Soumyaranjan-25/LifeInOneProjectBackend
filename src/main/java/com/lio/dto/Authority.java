package com.lio.dto;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@ToString
public class Authority implements GrantedAuthority {

	private String authority;
	
	@Override
	public String getAuthority() {
		
		return this.authority;
	}

}
 