package com.xmbl.ops.controller;

import com.xmbl.ops.dto.ResponseResult;
import com.xmbl.ops.enumeration.EnumResCode;

public abstract class AbstractController {


	protected ResponseResult successJson(String message, Object data) {
		ResponseResult result = new ResponseResult();
		result.setStatus(EnumResCode.SUCCESSFUL.value());
		result.setMsg(message);
		result.setResult(data);
		return result;
	}
	
	protected ResponseResult errorJson(String message) {
		ResponseResult result = new ResponseResult();
		result.setStatus(EnumResCode.SERVER_ERROR.value());
		result.setMsg(message);
		return result;
	}
}
