package com.xmbl.ops.dto.base;

import lombok.Data;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名:  ResponseResult 
 * @创建时间:  2018年9月10日 上午11:51:31
 * @修改时间:  2018年9月10日 上午11:51:31
 * @类说明:
 */
@Data
public class ResponseResult {
	
	// isSucc 是否成功 
	private boolean isSucc = Boolean.FALSE;
	// 是否消息
	private Boolean isMsgType = Boolean.FALSE;
	// 状态码 0 表示失败 1 表示成功  other状态码表示特定失败原因
	private int status = 0;
	// succMsg 成功消息
	private String succMsg = null;
	// errMsg 失败消息
	private String errMsg = null;
	// succRespResult 成功结果
	private Object succResp = null;
	
	/**
	 * 成功消息组装
	 * 
	 * @param status
	 * @param succMsg
	 * @param data
	 * @return
	 */
	public static ResponseResult succ(Boolean isMsgType, String succMsg, Object data) {
		ResponseResult result = new ResponseResult();
		result.setSucc(true);
		result.setStatus(1);
		result.setIsMsgType(isMsgType);
		result.setSuccMsg(succMsg);
		result.setSuccResp(data);
		return result;
	}
	
	/**
	 * 成功消息类型 提示成功消息
	 * 
	 * @param status
	 * @param succMsg
	 * @return
	 */
	public static ResponseResult succ(String succMsg) {
		return ResponseResult.succ(true, succMsg, null);
	}
	
	/**
	 * 成功消息类型 提示失败消息
	 * @param status
	 * @param data
	 * @return
	 */
	public static ResponseResult succ(Object data) {
		return ResponseResult.succ(false, null, data);
	}
	
	/**
	 * 失败消息类型 提示失败消息
	 * 
	 * @param status
	 * @param errMsg
	 * @return
	 */
	public static ResponseResult err(Integer status, String errMsg) {
		ResponseResult result = new ResponseResult();
		result.setSucc(false);
		result.setIsMsgType(true);
		result.setStatus(status);
		result.setErrMsg(errMsg);
		return result;
	}
	
}
