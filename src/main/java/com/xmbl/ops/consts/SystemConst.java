package com.xmbl.ops.consts;

import com.xmbl.ops.util.PropertyUtil;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名:  SystemConst 
 * @创建时间:  2018年8月2日 上午10:24:59
 * @修改时间:  2018年8月2日 上午10:24:59
 * @类说明:
 */
public class SystemConst {
	// 网页微信登录域名
	public static String DOMAIN_NAME = PropertyUtil.getProperty("conf/env.properties", "domain_name");
	// appid
	public static String APPID = PropertyUtil.getProperty("conf/env.properties", "appid");
	// appsecret
	public static String APPSECRET = PropertyUtil.getProperty("conf/env.properties", "appsecret");
}
