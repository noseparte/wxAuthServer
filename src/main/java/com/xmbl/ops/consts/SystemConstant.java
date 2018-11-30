package com.xmbl.ops.consts;

import com.xmbl.ops.util.PropertyUtil;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名:  SystemConstant 
 * @创建时间:  2017年12月22日 上午10:26:37
 * @修改时间:  2017年12月22日 上午10:26:37
 * @类说明: 系统常量
 */
public class SystemConstant {
	public static String gameUrl = PropertyUtil.getProperty("conf/env.properties", "gameUrl");
}
