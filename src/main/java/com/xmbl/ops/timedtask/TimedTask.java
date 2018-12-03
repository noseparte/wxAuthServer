package com.xmbl.ops.timedtask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.protobuf.format.JsonFormat;
import com.xmbl.ops.entity.tree.base.StageTreeRes;
import com.xmbl.ops.proto.MsgObject.PbTGStage;
import com.xmbl.ops.service.H5BStaticService;
import com.xmbl.ops.service.StageTreeService;

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
	StageTreeService treeService;
	
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
	
//	/**每天凌晨24点统计前一天的点击数*/
//	@Scheduled(cron = "0 0/1 * * * ? ")
//	public void test() {
//		StageTreeRes res = treeService.getStageTreeRes(7702000000000368l, 7701000000005152l);
//		System.out.println(JSONObject.toJSONString(res));
//		
//		PbTGStage stage = res.transfer();
//		String json = JsonFormat.printToString(stage);
//		System.out.println(json);
//		JSONObject jsonObject =JSONObject.parseObject(json);
//	}
}
