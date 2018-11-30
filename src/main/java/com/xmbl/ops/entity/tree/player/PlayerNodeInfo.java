package com.xmbl.ops.entity.tree.player;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "player_tree_node_info")
public class PlayerNodeInfo {
	
	@Id
	private String id;
	private String playerId;
	private Long treeId;
	private Long nodeId;
	private Long score;//得分
	private Integer status;//0表示未完成,1表示已完成,2已跳过
	private List<Long> finishedStageIds;//已完成的节点
	private List<Long> skipedStageIds;//跳过的节点
	private Long currentIndex;//当前所在的主线节点
	private Integer progress;//完成进度
	
//	private int finishNum;//当前关卡包通关的关卡数
	
	private int rank;

}
