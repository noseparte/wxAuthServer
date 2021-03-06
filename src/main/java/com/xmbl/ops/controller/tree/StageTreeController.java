package com.xmbl.ops.controller.tree;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.protobuf.format.JsonFormat;
import com.xmbl.ops.controller.AbstractController;
import com.xmbl.ops.dto.ResponseResult;
import com.xmbl.ops.entity.tree.base.StageTreeRes;
import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.base.TreeCover;
import com.xmbl.ops.entity.tree.local.NodeLocalInfo;
import com.xmbl.ops.entity.tree.local.PanelLocalInfo;
import com.xmbl.ops.entity.tree.local.TreeLocalInfo;
import com.xmbl.ops.logic.TreeFactory;
import com.xmbl.ops.service.StageTreeService;

@RestController
@RequestMapping("/stage/tree")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StageTreeController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(StageTreeController.class);

	@Autowired
	private StageTreeService stageTreeService;

	@RequestMapping("/covers")
	public ResponseResult getTrees() {
		List<TreeCover> treeInfos = stageTreeService.geTreeCovers();
		logger.info(treeInfos.toString());
		return successJson("", JSONObject.toJSONString(treeInfos));
	}

	@RequestMapping("/info")
	public ResponseResult getTree(@RequestParam(name = "treeId", required = false) long treeId) {
		if (treeId <= 0) {
			return errorJson("关卡集ID不能为空");
		}
		StageTreeShop stageTree = stageTreeService.getStageTree(treeId);
		logger.info(JSONObject.toJSONString(stageTree));
		
//		TreeLocalInfo treeLocalInfo = stageTreeService.getTreeLocalInfo(treeId);
		
		return successJson("", stageTree);
	}

	@RequestMapping("/res")
	public ResponseResult getStageTreeRes(@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId,
			@RequestParam(name = "index", required = false) long index,
			@RequestParam(name = "stageId", required = false) long stageId) {
		if (treeId <= 0) {
			return errorJson("关卡集ID不能为空");
		}
		if (stageId <= 0) {
			return errorJson("ID不能为空");
		}
		StageTreeRes res = stageTreeService.getStageTreeRes(treeId, stageId);
		
		String jsonString = JsonFormat.printToString(res.transfer());
		JSONObject jsonObject = JSONObject.parseObject(jsonString);
		
		JSONObject parent = new JSONObject();
		parent.put("Stage", jsonObject);
		return successJson("", parent);
	}

	@RequestMapping("/local/treeinfo")
	public ResponseResult getTreeLocalInfo(@RequestParam(name = "treeId", required = false) long treeId) {
		TreeLocalInfo info = stageTreeService.getTreeLocalInfo(treeId);
		if (Objects.nonNull(info)) {
			info = TreeFactory.createTreeLocalInfo(treeId);
			stageTreeService.saveTreeLocalInfo(info);
		}
		return successJson("", info);
	}

	@RequestMapping("/local/nodeinfo")
	public ResponseResult getNodeLocalInfo(@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId) {
		NodeLocalInfo info = stageTreeService.getNodeLocalInfo(treeId, nodeId);
		if (Objects.nonNull(info)) {
			info = TreeFactory.createNodeLocalInfo(treeId, nodeId);
			stageTreeService.saveNodeLocalInfo(info);
		}
		return successJson("", info);
	}

	@RequestMapping("/local/panelinfo")
	public ResponseResult getPanelLocalInfo(@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId,
			@RequestParam(name = "stageId", required = false) long stageId,
			@RequestParam(name = "index", required = false) int index) {
		PanelLocalInfo info = stageTreeService.getPanelLocalInfo(treeId, nodeId, index);
		if (Objects.nonNull(info)) {
			info = TreeFactory.createPanelLocalInfo(treeId, nodeId, stageId, index);
			stageTreeService.savePanelLocalInfo(info);
		}
		return successJson("", info);
	}

	@RequestMapping("/treeRank")
	public ResponseResult getTreeRank(@RequestParam(name = "treeId", required = false) long treeId) {
		return null;

	}

	@RequestMapping("/nodeRank")
	public ResponseResult getTreeNodeRank(@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId) {
		return null;

	}

	@RequestMapping("/stageRank")
	public ResponseResult getTreeStageRank(@RequestParam(name = "treeId", required = false) long treeId,
			@RequestParam(name = "nodeId", required = false) long nodeId,
			@RequestParam(name = "stageId", required = false) long stageId) {
		return null;
	}

}
