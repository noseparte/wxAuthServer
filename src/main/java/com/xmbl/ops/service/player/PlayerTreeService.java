package com.xmbl.ops.service.player;


import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.player.PlayerPanelInfo;
import com.xmbl.ops.entity.tree.player.PlayerTreeInfo;
import com.xmbl.ops.entity.tree.player.PlayerNodeInfo;

public interface PlayerTreeService {
	
	public PlayerTreeInfo getPlayerTreeInfo(String playerId, long treeId);
	
	public PlayerNodeInfo getPlayerTreeNodeInfo(String playerId, long treeId, long nodeId);
	
	public PlayerPanelInfo getPlayerPanelInfo(String playerId, long treeId, long nodeId, long stageId);
	
	public void reset(PlayerTreeInfo info, StageTreeShop treeShop);
	
	public void savePlayerTreeInfo(PlayerTreeInfo info);
	
	public void savePlayerNodeInfo(PlayerNodeInfo nodeInfo);
	
	public void savePlayerPanelInfo(PlayerPanelInfo panelInfo);
}
