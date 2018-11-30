package com.xmbl.ops.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResponseResult {
	private int status;
	private String msg;
	private Object result;
	
	@Data
	public static class ShareResult {
		int energy;
	}
}

