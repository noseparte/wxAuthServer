package com.xmbl.ops.controller.stage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.xmbl.ops.consts.SystemConstant;
import com.xmbl.ops.controller.AbstractController;
import com.xmbl.ops.dto.ResponseResult;
import com.xmbl.ops.util.PlatformSendYouXiServerUtil;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名: H5RespSendController
 * @创建时间: 2018年6月19日 下午3:37:35
 * @修改时间: 2018年6月19日 下午3:37:35
 * @类说明:
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/sendGame")
public class H5RespSendController extends AbstractController {

	private static Logger LOGGER = LoggerFactory.getLogger(H5RespSendController.class);

	@RequestMapping(value = "/getStageInfo", method = RequestMethod.POST)
	public ResponseResult getH5RespSend(@RequestParam(value = "StageId", required = false) String stageId) {
		if (StringUtils.isBlank(stageId)) {
			return errorJson("StateId不能为空");
		}
		
		long stId = 0l;
		try {
			stId = Long.parseLong(stageId);
		} catch (Exception e) {
			LOGGER.error("将stageId解析为整数时报错");
			return errorJson("StageId必须是正整数");
		}

		if (stId == 0l) {
			LOGGER.error("stageId不能为0");
			return errorJson("StageId必须是正整数");
		}
		
		String url = SystemConstant.gameUrl;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("StageId", stId);
		
		String bodyStr = PlatformSendYouXiServerUtil.send(url, jsonObj.toJSONString());
		if (StringUtils.isBlank(bodyStr)) {
			LOGGER.error("无法向服务获取关卡数据,stageId={}", stId);
			return errorJson("游戏服务查询关卡数据异常");
		}
		
		try {
			jsonObj = JSONObject.parseObject(bodyStr);
			return successJson("查询成功", jsonObj);
		} catch (Exception e) {
			LOGGER.error("游戏服务查询关卡数据异常", e);
			return errorJson("游戏服务查询关卡数据异常");
		}
	}
}
