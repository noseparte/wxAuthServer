package com.xmbl.ops.entity.tree.player;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "player_tree_panel_info")
public class PlayerPanelInfo {
	
	@Id
	private String id;
	private String playerId;//玩家Id
	private Long treeId;//关卡集Id
	private Long nodeId;//节点Id
	private Long stageId;//关卡Id
	private Long index;//索引值
	private Long score;//得分
	private Integer status;//状态，0表示未通关,1表示已通关,2表示跳过
	
	private int finishNum;//通关次数
	
	private int rank;
}
