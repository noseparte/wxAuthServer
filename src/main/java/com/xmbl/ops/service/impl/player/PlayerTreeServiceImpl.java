package com.xmbl.ops.service.impl.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmbl.ops.dao.player.PlayerTreeDao;
import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.player.PlayerPanelInfo;
import com.xmbl.ops.entity.tree.player.PlayerTreeInfo;
import com.xmbl.ops.entity.tree.player.PlayerNodeInfo;
import com.xmbl.ops.service.player.PlayerTreeService;

@Service
public class PlayerTreeServiceImpl implements PlayerTreeService {
	
	@Autowired
	private PlayerTreeDao playerTreeDao;

	@Override
	public PlayerTreeInfo getPlayerTreeInfo(String playerId, long treeId) {
		return playerTreeDao.getPlayerTreeInfo(playerId, treeId);
	}

	@Override
	public PlayerNodeInfo getPlayerTreeNodeInfo(String playerId, long treeId, long nodeId) {
		return playerTreeDao.getPlayerTreeNodeInfo(playerId, treeId, nodeId);
	}

	@Override
	public PlayerPanelInfo getPlayerPanelInfo(String playerId, long treeId, long nodeId, long stageId) {
		return playerTreeDao.getPlayerPanelInfo(playerId, treeId, nodeId, stageId);
	}

	@Override
	public void reset(PlayerTreeInfo info, StageTreeShop treeShop) {
		info.setALimit1(null);
		info.setALimit2(null);
		info.setBLimit1(null);
		info.setBLimit2(null);
		
		info.setElementLimit1(0l);
		info.setElementLimit2(0l);
		info.setEnergy(treeShop.getMaxEnergy());
		info.setProgress(0);
		info.setFinishedNodeIds(null);
		info.setSkipedNodeIds(null);
		
		playerTreeDao.updatePlayerTreeInfo(info);
		
	}

	@Override
	public void savePlayerTreeInfo(PlayerTreeInfo info) {
		playerTreeDao.updatePlayerTreeInfo(info);
	}

	@Override
	public void savePlayerNodeInfo(PlayerNodeInfo nodeInfo) {
		playerTreeDao.savePlayerNodeInfo(nodeInfo);
		
	}

	@Override
	public void savePlayerPanelInfo(PlayerPanelInfo panelInfo) {
		playerTreeDao.savePlayerPanelInfo(panelInfo);
	}
}
