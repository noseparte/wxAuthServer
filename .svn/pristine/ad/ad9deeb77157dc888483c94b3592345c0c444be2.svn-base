package com.xmbl.ops.controller.wxAuth;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSONObject;
import com.xmbl.ops.dao.mongo.WxLoginDao;
import com.xmbl.ops.entity.WxLogin;
import com.xmbl.ops.service.WxLoginService;
import com.xmbl.ops.util.AuthUtil;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名: WxLoginController
 * @创建时间: 2018年7月31日 下午6:38:08
 * @修改时间: 2018年7月31日 下午6:38:08
 * @类说明:
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/wxLogin")
public class WxLoginController {

	// wxUserIdSet 微信登录用户id
	private static Set<String> wxUserIdSet = new HashSet<String>();

	@Autowired
	private WxLoginService wxLoginService;

	@Autowired
	private WxLoginDao wxLoginDao;

	private static Logger LOGGER = LoggerFactory.getLogger(WxLoginController.class);

	/**
	 * 第一步: 要求微信给用户发起网页授权请求
	 */
	@RequestMapping("/authorize")
	public void authorize(HttpServletResponse resp, @RequestParam(value = "StageId", required = false) String stageId, // 关卡id
			@RequestParam(value = "ttamp", required = false) String ttamp, // 当前时间戳
			@RequestParam(value = "from", required = false) String from, // 来源
			@RequestParam(value = "urlLink", required = false) String urlLink, // 来源
			@RequestParam(value = "sType", required = false, defaultValue = "2") String sType// 设备地址 2默认阿里服务器线上环境 1
																								// 表示测试环境
	) throws IOException {
		LOGGER.info("授权前,urlLink链接地址打印:" + urlLink);
		String stateStr = "";
		// 拼接重定向url参数
		StringBuffer state = new StringBuffer();
		// 当前时间戳
		state.append("ttamp_").append(new Date().getTime()).append("@");
		if (StringUtils.isNotBlank(stageId)) {
			state.append("StageId_").append(stageId).append("@");
		}
		if (StringUtils.isNotBlank(from)) {
			state.append("from_").append(from).append("@");
		}
		if (StringUtils.isNotBlank(sType)) {
			state.append("sType_").append(sType).append("@");
		}
		if (StringUtils.isNotBlank(urlLink)) {
			state.append("urlLink_").append(urlLink).append("@");
		}
		if (state.length() > 0) {
			stateStr = state.toString().substring(0, state.length() - 1);
		}
		String backUrl = "DOMAIN_NAME/wxLogin/wxCallBack";
		backUrl = backUrl.replaceAll("DOMAIN_NAME", AuthUtil.domainName);
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		url = url.replaceAll("APPID", AuthUtil.APPID)//
				.replaceAll("REDIRECT_URI", URLEncoder.encode(backUrl, "utf-8"))//
				.replaceAll("STATE", stateStr);
		LOGGER.info("state:" + stateStr);
		LOGGER.info("url:" + url);
		resp.sendRedirect(url);
	}

	/**
	 * 第二步: 用户信息获取
	 * 
	 */
	// @ResponseBody
	@RequestMapping("/wxCallBack")
	public Object wxCallBack(HttpServletRequest req) {
		String paramStr = "";
		String h5Domain = "http://h5.ugcapp.com";
		try {
			String code = req.getParameter("code");
			String state = req.getParameter("state");
			StringBuffer paramBuffer = new StringBuffer();
			String[] stateArr = state.split("@");
			for (int i = 0; i < stateArr.length; i++) {
				String oneStr = stateArr[i];
				if (oneStr.indexOf("urlLink") != -1) {
					String[] urlLinkArr = oneStr.split("_");
					if (urlLinkArr.length >= 1) {
						// LOGGER.info(h5Domain);
						// LOGGER.info(URLDecoder.decode(urlLinkArr[1],"UTF-8"));
						h5Domain = URLDecoder.decode(urlLinkArr[1], "UTF-8");
					}
				} else {
					oneStr = oneStr.replaceAll("_", "=");
					paramBuffer.append(oneStr).append("&");
				}

			}
			if (paramBuffer.length() > 0) {
				paramStr = paramBuffer.substring(0, paramBuffer.length() - 1);
			}
			LOGGER.info("paramStr:" + paramStr);
			Assert.isTrue(StringUtils.isNotBlank(code), "用户授权失败,未获取到code");
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
			url = url.replaceAll("APPID", AuthUtil.APPID).replaceAll("APPSECRET", AuthUtil.APPSECRET).replaceAll("CODE",
					code);
			JSONObject jsonObject = AuthUtil.doGetJson(url);
			String openid = jsonObject.getString("openid");
			String access_token = jsonObject.getString("access_token");
			Assert.isTrue(StringUtils.isNotBlank(openid) && StringUtils.isNotBlank(access_token),
					"openid 或者 access_token 未获取到");
			String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
			infoUrl = infoUrl.replaceAll("ACCESS_TOKEN", access_token).replaceAll("OPENID", openid);
			JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
			openid = userInfo.getString("openid");
			Assert.isTrue(StringUtils.isNotBlank(openid), "用户信息查询失败");
			userInfo.put("status", 1);
			userInfo.put("msg", "成功");
			// 保存用户信息或者 修改用户信息
			wxLoginService.addOrUpd(userInfo);
			WxLogin wxLogin = wxLoginDao.findByOpenid(openid);
			// 保存wxUserId
			String wxUserId = wxLogin.get_id();
			wxUserIdSet.add(wxUserId);
			LOGGER.info("wxUserIdSet:" + JSONObject.toJSONString(wxUserIdSet));
			String redirectUrl = "H5DOMAIN?PARAMSTR&wxUserId=WXUSERID";
			redirectUrl = redirectUrl.replaceAll("H5DOMAIN", h5Domain).replaceAll("PARAMSTR", paramStr)
					.replaceAll("WXUSERID", wxLogin.get_id());
			return new ModelAndView(new RedirectView(redirectUrl));
		} catch (Exception e) {// 报错时返回微信用户信息获取失败
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			jsonObject.put("msg", e.getMessage());
			String redirectUrl = "H5DOMAIN?&wxUserId=0";
			redirectUrl = redirectUrl.replaceAll("H5DOMAIN", h5Domain);
			return new ModelAndView(new RedirectView(redirectUrl));
		}

	}

	@ResponseBody
	@RequestMapping("/getWxUserInfo")
	public JSONObject getUserInfo(HttpServletRequest req,
			@RequestParam(value = "wxUserId", required = false) String wxUserId) {
		JSONObject jsonObject = new JSONObject();
		try {
			LOGGER.info("打印要返回的用户 wxUserId:" + wxUserId);
			Assert.isTrue(StringUtils.isNotBlank(wxUserId), "wxUserId不能为空");
			Assert.isTrue(wxUserIdSet.contains(wxUserId), "wxUserId授权失效,请重新授权");
			// 进行授权前先把wxUserId在内存中移除
			wxUserIdSet.remove(wxUserId);
			WxLogin wxLogin = wxLoginDao.findById(wxUserId);
			jsonObject.put("wxLogin", wxLogin);
			jsonObject.put("status", 1);
			jsonObject.put("msg", "微信授权成功");
		} catch (Exception e) {
			jsonObject.put("status", 0);
			jsonObject.put("msg", "微信授权失败");
			LOGGER.error("微信授权登陆错误消息", e);
		}
		return jsonObject;
	}
}
