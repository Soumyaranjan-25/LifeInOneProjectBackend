package com.lio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
	@Builder.Default
	private String status="success";
	@Builder.Default
	private Integer code=200;
	@Builder.Default
	private String message="";
	@Builder.Default
	private Object data="";
}
