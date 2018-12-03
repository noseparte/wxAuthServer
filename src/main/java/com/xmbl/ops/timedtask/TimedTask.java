package com.xmbl.ops.timedtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xmbl.ops.service.H5BStaticService;

/**
 * @author: sunbenbao
 * @Email: 1402614629@qq.com
 * @类名:  TimedTask 
 * @创建时间:  2018年11月1日 下午6:22:20
 * @修改时间:  2018年11月1日 下午6:22:20
 * @类说明:
 */
@Component
public class TimedTask {
	@Autowired
	private H5BStaticService h5BStaticService;
	
	@Autowired
	
	/**每天中午12点发送统计信息*/
	@Scheduled(cron = "0 0 12 * * ?")//  
	public void sendH5BStatisticMsg() {
		h5BStaticService.sendStatisticInfo();
	}
	
	/**每天凌晨24点统计前一天的点击数*/
	@Scheduled(cron = "0 0 0 * * ? ")
	public void h5BStatistic() {
		h5BStaticService.statistic();
	}
}
