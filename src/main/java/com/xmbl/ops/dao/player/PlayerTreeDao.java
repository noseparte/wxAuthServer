package com.xmbl.ops.dao.player;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.xmbl.ops.entity.tree.player.PlayerPanelInfo;
import com.xmbl.ops.entity.tree.player.PlayerTreeInfo;
import com.xmbl.ops.entity.tree.player.PlayerNodeInfo;

@Repository
public class PlayerTreeDao {

	@Resource
	@Qualifier("mongoTemplateLogin")
	public MongoTemplate mongoTemplateLogin;

	public PlayerTreeInfo getPlayerTreeInfo(String playerId, long treeId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("playerId").is(playerId).and("treeId").is(treeId));
		PlayerTreeInfo info = mongoTemplateLogin.findOne(query, PlayerTreeInfo.class);
		return info;
	}

	public void updatePlayerTreeInfo(PlayerTreeInfo info) {
		mongoTemplateLogin.save(info);
	}

	public PlayerNodeInfo getPlayerTreeNodeInfo(String playerId, long treeId, long nodeId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("playerId").is(playerId).and("treeId").is(treeId).and("nodeId").is(nodeId));
		PlayerNodeInfo info = mongoTemplateLogin.findOne(query, PlayerNodeInfo.class);
		return info;
	}

	public PlayerPanelInfo getPlayerPanelInfo(String playerId, long treeId, long nodeId, long stageId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("playerId").is(playerId).and("treeId").is(treeId).and("nodeId").is(nodeId)
				.and("stageId").is(stageId));
		PlayerPanelInfo info = mongoTemplateLogin.findOne(query, PlayerPanelInfo.class);
		return info;
	}

	public void savePlayerNodeInfo(PlayerNodeInfo nodeInfo) {
		mongoTemplateLogin.save(nodeInfo);
	}

	public void savePlayerPanelInfo(PlayerPanelInfo panelInfo) {
		mongoTemplateLogin.save(panelInfo);
	}

}
