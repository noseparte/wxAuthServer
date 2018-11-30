package com.xmbl.ops.service;

import java.util.List;

import com.xmbl.ops.entity.tree.base.StageTreeRes;
import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.base.StageTreeShop.Node;
import com.xmbl.ops.entity.tree.base.StageTreeShop.Panel;
import com.xmbl.ops.entity.tree.base.TreeCover;
import com.xmbl.ops.entity.tree.base.TreeDetail;
import com.xmbl.ops.entity.tree.local.NodeLocalInfo;
import com.xmbl.ops.entity.tree.local.PanelLocalInfo;
import com.xmbl.ops.entity.tree.local.TreeLocalInfo;

public interface StageTreeService {
	List<TreeCover> geTreeCovers();
	TreeDetail getTreeDetailById(long treeId);
	StageTreeShop getStageTree(long treeId);
	StageTreeRes getStageTreeRes(long treeId, long id);
	
	void incTreePlayerNum(long treeId);
	void incTreePlayerPassed(long treeId);
	
	void incNodePlayerNum(long treeId, long nodeId);
	void incNodePlayerPassed(long treeId, long nodeId, int index);
	
	TreeLocalInfo getTreeLocalInfo(long treeId);
	NodeLocalInfo getNodeLocalInfo(long treeId, long nodeId);
	PanelLocalInfo getPanelLocalInfo(long treeId, long nodeId, int index);
	
	void saveTreeLocalInfo(TreeLocalInfo info);
	void saveNodeLocalInfo(NodeLocalInfo info);
	void savePanelLocalInfo(PanelLocalInfo info);
	
	Node getNode(long treeId, long nodeId, StageTreeShop stageTree);
	
	Panel getPanel(long treeId, long nodeId, long stageId, StageTreeShop stageTree);
	
	Panel getPanelByIndex(long treeId, long nodeId, long index, StageTreeShop stageTree);
	
}
