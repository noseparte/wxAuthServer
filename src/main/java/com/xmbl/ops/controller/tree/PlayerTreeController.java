package com.xmbl.ops.controller.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmbl.ops.consts.GameConst;
import com.xmbl.ops.controller.AbstractController;
import com.xmbl.ops.dto.ResponseResult;
import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.base.StageTreeShop.Node;
import com.xmbl.ops.entity.tree.base.StageTreeShop.Panel;
import com.xmbl.ops.entity.tree.player.PlayerNodeInfo;
import com.xmbl.ops.entity.tree.player.PlayerPanelInfo;
import com.xmbl.ops.entity.tree.player.PlayerTreeInfo;
import com.xmbl.ops.logic.PlayerFactory;
import com.xmbl.ops.logic.TreeEnums;
import com.xmbl.ops.logic.TreeEnums.NodeType;
import com.xmbl.ops.logic.TreeEnums.StoryType;
import com.xmbl.ops.service.StageTreeService;
import com.xmbl.ops.service.player.PlayerTreeService;

@RestController
@RequestMapping("/player/tree")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PlayerTreeController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerTreeController.class);

	@Autowired
	private PlayerTreeService playerTreeService;
	@Autowired
	StageTreeService treeService;

	@RequestMapping("/info")
	public ResponseResult getPlayerTreeInfo(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId) {

		PlayerTreeInfo info = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(info)) {
			StageTreeShop stageTreeShop = treeService.getStageTree(treeId);
			info = PlayerFactory.createPlayerTreeInfo(playerId, treeId, stageTreeShop);
			if (Objects.isNull(info)) {
				logger.error("初始化玩家关卡集数据失败,playerId={},treeId={}", playerId, treeId);
				return errorJson("初始化玩家关卡集数据失败");
			}
			playerTreeService.savePlayerTreeInfo(info);
			treeService.incTreePlayerNum(treeId);
		}
		return successJson("", info);
	}

	@RequestMapping("/node")
	public ResponseResult getPlayerTreeNodeInfo(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId) {

		StageTreeShop stageTree = treeService.getStageTree(treeId);
		if (Objects.isNull(stageTree)) {
			return errorJson("找不到关卡数据");
		}

		Node node = treeService.getNode(treeId, nodeId, stageTree);
		if (Objects.isNull(node)) {
			return errorJson("找不到节点数据");
		}

		PlayerTreeInfo playerTreeInfo = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(playerTreeInfo)) {
			return errorJson("获取不到玩家关卡集数据");
		}

		if (!canEnterNode(playerTreeInfo, node)) {
			return errorJson("你还不能进入当前节点, 请完成或跳过前置节点");
		}

		NodeType nodeType = NodeType.getNodeType(node.getNodeType());
		if (nodeType == NodeType.normal) {
			Panel panel = node.getPanels().get(0);
			if (Objects.nonNull(panel)) {
				return errorJson("找不到单关节点里唯一关卡的信息");
			}
			return getPlayerPanelInfo(playerId, treeId, nodeId, panel.getIndex());
		} else if (nodeType == NodeType.pack) {
			PlayerNodeInfo playerTreeNodeInfo = playerTreeService.getPlayerTreeNodeInfo(playerId, treeId, nodeId);
			enterNode(playerId, treeId, playerTreeNodeInfo);
			return successJson("进入关卡节点成功", playerTreeNodeInfo);
		}
		return errorJson("未知关卡节点类型");
	}

	/** 是否可以进入节点 */
	private boolean canEnterNode(PlayerTreeInfo playerTreeInfo, Node node) {
		long nodeId = node.getNodeId();
		long priorNodeId = node.getPriorId();
		List<Long> finishedNodeIds = playerTreeInfo.getFinishedNodeIds();
		List<Long> skipNodeIds = playerTreeInfo.getSkipedNodeIds();

		if (Objects.nonNull(finishedNodeIds) && finishedNodeIds.contains(nodeId)) {
			return true;
		}

		if ((Objects.nonNull(finishedNodeIds) && finishedNodeIds.contains(priorNodeId))
				|| Objects.nonNull(skipNodeIds) && skipNodeIds.contains(priorNodeId)) {
			return true;
		}
		return false;
	}

	private PlayerNodeInfo enterNode(String playerId, long treeId, PlayerNodeInfo playerTreeNodeInfo) {
		if (Objects.isNull(playerTreeNodeInfo)) {
			StageTreeShop stageTree = treeService.getStageTree(treeId);
			playerTreeNodeInfo = PlayerFactory.createPlayerTreeNodeInfo(playerId, treeId,
					playerTreeNodeInfo.getNodeId(), stageTree);
			playerTreeService.savePlayerNodeInfo(playerTreeNodeInfo);
			treeService.incNodePlayerNum(treeId, playerTreeNodeInfo.getNodeId());
		}
		return playerTreeNodeInfo;
	}

	@RequestMapping("/node/stage")
	public ResponseResult getPlayerPanelInfo(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId,
			@RequestParam(name = "stageId", required = false) long stageId) {

		if (StringUtils.isEmpty(playerId)) {
			return errorJson("playerId不能为空");
		}

		StageTreeShop stageTree = treeService.getStageTree(treeId);
		if (Objects.isNull(stageTree)) {
			return errorJson("找不到关卡数据");
		}

		Node node = treeService.getNode(treeId, nodeId, stageTree);
		if (Objects.isNull(node)) {
			return errorJson("获取不到关卡节点数据");
		}

		Panel panel = treeService.getPanel(treeId, nodeId, stageId, stageTree);
		if (Objects.isNull(panel)) {
			return errorJson("获取不到关卡数据");
		}

		PlayerTreeInfo playerTreeInfo = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(playerTreeInfo)) {
			return errorJson("获取不到玩家关卡集数据");
		}

		PlayerNodeInfo playerNodeInfo = playerTreeService.getPlayerTreeNodeInfo(playerId, treeId, nodeId);
		if (Objects.isNull(playerNodeInfo)) {
			return errorJson("获取不到玩家节点数据");
		}

		if (!canEnterPanel(playerTreeInfo, playerNodeInfo, node, panel)) {
			return errorJson("你还不能进入当前关卡, 请完成或跳过前置节点");
		}

		if (!deductEnengy(playerTreeInfo, stageTree, node)) {
			return errorJson("扣除体力值失败");
		}
		PlayerPanelInfo playerPanelInfo = playerTreeService.getPlayerPanelInfo(playerId, treeId, nodeId, stageId);
		if (Objects.isNull(playerPanelInfo)) {
			playerPanelInfo = PlayerFactory.createPlayerPanelInfo(playerId, treeId, nodeId, stageId);
			playerTreeService.savePlayerPanelInfo(playerPanelInfo);
		}

		playerPanelInfo.setStatus(1);
		playerTreeService.savePlayerPanelInfo(playerPanelInfo);

		return successJson("", playerPanelInfo);
	}

	/** 扣除进入关卡需要消耗的体力值 */
	private boolean deductEnengy(PlayerTreeInfo playerTreeInfo, StageTreeShop treeShop, Node node) {
		int energy = playerTreeInfo.getEnergy().intValue();
		int pce = getPce(treeShop, node);
		if (energy < pce) {
			return false;
		}
		playerTreeInfo.setEnergy(energy - pce);
		playerTreeService.savePlayerTreeInfo(playerTreeInfo);
		return true;
	}

	/** 是否可以进入关卡 */
	private boolean canEnterPanel(PlayerTreeInfo playerTreeInfo, PlayerNodeInfo playerNodeInfo, Node node,
			Panel panel) {
		if (!canEnterNode(playerTreeInfo, node)) {
			return false;
		}
		Integer openType = node.getOpenType();
		if (TreeEnums.OpenType.noOrder.ordinal() == openType) {
			return true;
		}

		long index = panel.getIndex();
		if (index == 0) {
			return true;
		}

		List<Long> finishedStageIds = playerNodeInfo.getFinishedStageIds();
		List<Long> skipedStageIds = playerNodeInfo.getSkipedStageIds();
		if (Objects.nonNull(finishedStageIds) && finishedStageIds.contains(panel.getStageId())) {
			return true;
		}

		if (Objects.nonNull(skipedStageIds) && skipedStageIds.contains(panel.getStageId())) {
			return true;
		}

		long priorIndex = panel.getIndex() - 1;
		Panel priorPanel = treeService.getPanelByIndex(playerTreeInfo.getTreeId(), playerNodeInfo.getNodeId(),
				priorIndex, null);
		if (Objects.isNull(priorPanel)) {
			return true;// 简单处理
		}
		long priorStageId = priorPanel.getStageId();
		if (Objects.nonNull(finishedStageIds) && finishedStageIds.contains(priorStageId)) {
			return true;
		}

		if (Objects.nonNull(skipedStageIds) && skipedStageIds.contains(priorStageId)) {
			return true;
		}
		return false;
	}

	@RequestMapping("/reset")
	public ResponseResult resetTree(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId) {

		StageTreeShop treeShop = treeService.getStageTree(treeId);
		if (Objects.isNull(treeShop)) {
			return errorJson("获取不到关卡集数据");
		}
		Integer st = treeShop.getStoryType();
		if (Objects.isNull(st)) {
			return errorJson("获取不到关卡集数据");
		}

		StoryType storyType = StoryType.getStoryType(st.intValue());
		if (Objects.isNull(storyType)) {
			return errorJson("获取不到关卡集数据");
		}

		if (storyType != StoryType.survive) {
			return errorJson("生存模式才需要重置关卡");
		}

		PlayerTreeInfo playerTreeInfo = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(playerTreeInfo)) {
			return errorJson("获取不到玩家关卡集数据");
		}

		playerTreeService.reset(playerTreeInfo, treeShop);
		return successJson("", playerTreeInfo);
	}

	@RequestMapping("/skip")
	public ResponseResult skip(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId,
			@RequestParam(name = "stageId", required = false) long stageId) {

		PlayerTreeInfo playerTreeInfo = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(playerTreeInfo)) {
			return errorJson("获取不到玩家关卡集数据");
		}

		List<Long> skipedNodeIds = playerTreeInfo.getSkipedNodeIds();
		if (Objects.nonNull(skipedNodeIds) && skipedNodeIds.contains(nodeId)) {
			return errorJson("该关卡已经跳过");
		}

		List<Long> finishedNodeIds = playerTreeInfo.getFinishedNodeIds();
		if (Objects.nonNull(finishedNodeIds) && finishedNodeIds.contains(nodeId)) {
			return errorJson("该关卡已经完成");
		}

		StageTreeShop stageTree = treeService.getStageTree(treeId);
		if (Objects.isNull(stageTree)) {
			return errorJson("获取不到关卡集数据");
		}

		Node node = treeService.getNode(treeId, nodeId, stageTree);
		if (Objects.isNull(node)) {
			return errorJson("找不到节点数据");
		}
		NodeType nodeType = NodeType.getNodeType(node.getNodeType().intValue());
		if (nodeType == NodeType.pack && stageId <= 0) {
			return errorJson("关卡包不能直接跳过");
		}

		PlayerNodeInfo playerNodeInfo = playerTreeService.getPlayerTreeNodeInfo(playerId, treeId, nodeId);
		if (Objects.isNull(playerNodeInfo)) {
			return errorJson("获取不到玩家节点数据");
		}

		if (playerNodeInfo.getFinishedStageIds().contains(stageId)) {
			return errorJson("该关卡已完成");
		}

		List<Long> skipedStageIds = playerNodeInfo.getSkipedStageIds();
		if (Objects.nonNull(skipedStageIds) && skipedStageIds.contains(stageId)) {
			return errorJson("该关卡已跳过");
		}

		if (Objects.isNull(skipedNodeIds)) {
			skipedNodeIds = new ArrayList<>();
			playerTreeInfo.setSkipedNodeIds(skipedNodeIds);
		}
		skipedNodeIds.add(stageId);
		playerTreeInfo.setSkipStageNum(playerTreeInfo.getSkipStageNum() + 1);
		playerTreeService.savePlayerTreeInfo(playerTreeInfo);

		if (Objects.isNull(skipedStageIds)) {
			skipedNodeIds = new ArrayList<>();
			playerNodeInfo.setSkipedStageIds(skipedStageIds);
		}
		skipedStageIds.add(stageId);
		playerTreeService.savePlayerNodeInfo(playerNodeInfo);

		return successJson("跳过关卡成功", playerTreeInfo);
	}

	@RequestMapping("/commit")
	public ResponseResult commit(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId,
			@RequestParam(name = "stageId", required = false) long stageId,
			@RequestParam(name = "result", required = false) long result) {
		if (result == GameConst.tree_stage_result_success) {
			PlayerTreeInfo playerTreeInfo = playerTreeService.getPlayerTreeInfo(playerId, treeId);
			PlayerNodeInfo playerNodeInfo = playerTreeService.getPlayerTreeNodeInfo(playerId, treeId, nodeId);
			PlayerPanelInfo playerPanelInfo = playerTreeService.getPlayerPanelInfo(playerId, treeId, nodeId, stageId);

			StageTreeShop stageTree = treeService.getStageTree(treeId);
			Node node = treeService.getNode(treeId, nodeId, stageTree);

			if (playerPanelInfo.getStatus() == GameConst.tree_stage_result_fail) {
				playerPanelInfo.setStatus(GameConst.tree_stage_status_pass);
				returnEnergy(playerTreeInfo, stageTree, node);
			}
			playerPanelInfo.setFinishNum(playerPanelInfo.getFinishNum() + 1);
			playerTreeService.savePlayerPanelInfo(playerPanelInfo);

			if (!playerNodeInfo.getFinishedStageIds().contains(stageId)) {
				playerNodeInfo.getFinishedStageIds().add(stageId);
				int progress = playerNodeInfo.getFinishedStageIds().size() / node.getPanels().size();
				playerNodeInfo.setProgress(progress);
			}
			if (playerNodeInfo.getSkipedStageIds().contains(stageId)) {
				playerNodeInfo.getSkipedStageIds().remove(stageId);
				playerTreeInfo.setSkipStageNum(playerTreeInfo.getSkipStageNum() - 1);
			}
			if (node.getNodeType().intValue() == TreeEnums.NodeType.normal.ordinal()) {
				if (playerNodeInfo.getStatus().intValue() == GameConst.tree_node_status_unpass) {
					playerNodeInfo.setStatus(GameConst.tree_node_status_pass);
				}

			} else if (node.getNodeType().intValue() == TreeEnums.NodeType.pack.ordinal()) {
				if (playerNodeInfo.getStatus().intValue() == GameConst.tree_node_status_unpass) {
					if (playerNodeInfo.getProgress() >= node.getPassCondition()) {
						playerNodeInfo.setStatus(GameConst.tree_node_status_pass);
					}
				}
			}
			playerTreeService.savePlayerNodeInfo(playerNodeInfo);

			if (playerNodeInfo.getStatus().intValue() == GameConst.tree_node_status_pass) {
				if (!playerTreeInfo.getFinishedNodeIds().contains(nodeId)) {
					playerTreeInfo.getFinishedNodeIds().add(nodeId);
					playerTreeInfo
							.setProgress(playerTreeInfo.getFinishedNodeIds().size() / stageTree.getNodes().size());
				}
			}
			playerTreeService.savePlayerTreeInfo(playerTreeInfo);
		}
		return null;
	}

	private void returnEnergy(PlayerTreeInfo playerTreeInfo, StageTreeShop treeShop, Node node) {
		int currentEnergy = playerTreeInfo.getEnergy();
		int max = treeShop.getMaxEnergy();
		int pce = getPce(treeShop, node);
		int r = currentEnergy + pce > max ? max : currentEnergy + pce;
		playerTreeInfo.setEnergy(r);
	}

	private int getPce(StageTreeShop treeShop, Node node) {
		Integer temp = treeShop.getPce();
		int pce = 0;
		if (Objects.nonNull(temp)) {
			pce = temp.intValue();
		}

		Integer temp2 = node.getPce();
		if (Objects.nonNull(temp2)) {
			pce = temp2.intValue();
		}

		return pce;
	}

	@RequestMapping("/setReturnEnergyFlag")
	public ResponseResult setReturnEngeryFlag(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "flag", required = false) int flag) {

		PlayerTreeInfo info = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(info)) {
			return errorJson("找不到玩家相关数据");
		}

		info.setReturnEnergyFlag(flag);
		playerTreeService.savePlayerTreeInfo(info);

		return successJson("设置玩家通关返回体力值标志成功", "");

	}

	@RequestMapping("/share")
	public ResponseResult share(@RequestParam(name = "playerId", required = false) String playerId,
			@RequestParam(name = "treeId", required = false) long treeId) {
		PlayerTreeInfo info = playerTreeService.getPlayerTreeInfo(playerId, treeId);
		if (Objects.isNull(info)) {
			return errorJson("找不到玩家相关数据");
		}

		int currentEnergy = info.getEnergy();
		int shareTime = info.getShareTime();
		StageTreeShop stageTreeShop = treeService.getStageTree(treeId);
		int maxEnergy = stageTreeShop.getMaxEnergy();

		if (currentEnergy > maxEnergy) {
			logger.info("玩家:{}在关卡集:{}的体力值大于限制值:{}", playerId, treeId, maxEnergy);
			return successJson("success", "");
		} else if (currentEnergy == maxEnergy) {
			return successJson("success", "");
		} else {
			if (shareTime == 0) {
				shareTime++;
				info.setEnergy(maxEnergy);
			} else if (shareTime == 1) {
				int temp = currentEnergy + GameConst.share_second_return_energy;
				int returnValue = temp < maxEnergy ? temp : maxEnergy;
				info.setEnergy(returnValue);
			}
		}

		info.setShareTime(shareTime++);
		playerTreeService.savePlayerTreeInfo(info);

		ResponseResult.ShareResult shareResult = new ResponseResult.ShareResult();
		shareResult.setEnergy(info.getEnergy());
		return successJson("success", shareResult);
	}
}
