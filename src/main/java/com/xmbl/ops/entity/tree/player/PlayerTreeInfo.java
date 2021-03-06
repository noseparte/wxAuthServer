package com.xmbl.ops.entity.tree.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Document(collection = "player_tree_info")
public class PlayerTreeInfo {
	@Id
	private String id;
	private String playerId;// 玩家Id
	private Long treeId;// 关卡集ID
	private List<Long> finishedNodeIds;// 已完成节点
	private List<Long> skipedNodeIds;// 已跳过节点
	// private Long currentMainNodeId;//当前所在的主线节点
	private Integer energy = 0;// 体力值
	private Integer progress = 0;// 进度

	private List<Long> aLimit1;// a玩家第一限制条件
	private List<Long> aLimit2;// a玩家第二限制条件
	private List<Long> bLimit1;// b玩家第一限制条件
	private List<Long> bLimit2;// b玩家第二限制条件

	private Long elementLimit1;// 元素限制1
	private Long elementLimit2;// 元素限制2

	private int returnEnergyFlag = 1;// 是否在首次完成关卡后返还体力值,0表示不返还，1表示返还

	private int shareTime;// 分享次数

	private int rank;

	private int skipStageNum = 0;
	
	private Map<Long, NodeInfo> nodeInfos = new HashMap<>();
	
	@Getter
	@Setter
	public static class NodeInfo {
		public NodeInfo() {super();}
		
		public NodeInfo(Long nodeId, Integer status) {
			super();
			this.nodeId = nodeId;
			this.status = status;
		}

		Long nodeId;
		/**0表示未完成,1表示已完成,2已跳过*/
		Integer status;
	}

}
