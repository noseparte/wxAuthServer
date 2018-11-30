package com.xmbl.ops.logic;

import java.util.List;
import java.util.Objects;

import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.base.StageTreeShop.Node;
import com.xmbl.ops.entity.tree.player.PlayerPanelInfo;
import com.xmbl.ops.entity.tree.player.PlayerTreeInfo;
import com.xmbl.ops.entity.tree.player.PlayerNodeInfo;

public class PlayerFactory {
	public static final PlayerTreeInfo createPlayerTreeInfo(String playerId, long treeId, StageTreeShop tree) {
		PlayerTreeInfo info = new PlayerTreeInfo();
		info.setPlayerId(playerId);
		info.setTreeId(treeId);
		info.setReturnEnergyFlag(1);
		info.setProgress(0);
		info.setEnergy(tree.getMaxEnergy());
		return info;
	}

	public static final PlayerNodeInfo createPlayerTreeNodeInfo(String playerId, long treeId, long nodeId,
			StageTreeShop tree) {
		if (Objects.isNull(tree)) {
			return null;
		}
		List<Node> nodes = tree.getNodes();
		if (Objects.isNull(nodes) || nodes.isEmpty()) {
			return null;
		}
		Node node = null;
		for (Node n : nodes) {
			if (n.getNodeId() == nodeId) {
				node = n;
				break;
			}
		}
		if (Objects.isNull(node)) {
			return null;
		}
		PlayerNodeInfo info = new PlayerNodeInfo();
		info.setCurrentIndex(0l);
		info.setPlayerId(playerId);
		info.setTreeId(treeId);
		info.setNodeId(nodeId);
		info.setProgress(0);
//		info.setFinishNum(0);
		info.setStatus(0);
		info.setScore(0l);
		return info;
	}

	public static final PlayerPanelInfo createPlayerPanelInfo(String playerId, long treeId, long nodeId, long stageId) {
		PlayerPanelInfo info = new PlayerPanelInfo();
		info.setPlayerId(playerId);
		info.setTreeId(treeId);
		info.setNodeId(nodeId);
		info.setStageId(stageId);
		info.setFinishNum(0);
		info.setScore(0l);
		info.setStatus(0);
		return info;
	}

}
