package com.xmbl.ops.entity.tree.rank;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tree_rank_info")
public class TreeRankInfo {
	private String playerId;
	private long treeId;
	private int finishNum;
	private long firstFinishTime;
}
