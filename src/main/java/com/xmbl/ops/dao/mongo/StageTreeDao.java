package com.xmbl.ops.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.xmbl.ops.consts.MongoDBConst;
import com.xmbl.ops.entity.tree.base.StageTreeRes;
import com.xmbl.ops.entity.tree.base.StageTreeShop;
import com.xmbl.ops.entity.tree.base.TreeCover;
import com.xmbl.ops.entity.tree.base.TreeDetail;
import com.xmbl.ops.entity.tree.local.NodeLocalInfo;
import com.xmbl.ops.entity.tree.local.PanelLocalInfo;
import com.xmbl.ops.entity.tree.local.TreeLocalInfo;

@Repository
public class StageTreeDao {
	private static final Logger logger = LoggerFactory.getLogger(StageTreeDao.class);
	
	@Resource
	@Qualifier("stageMongoTemplate")
	private MongoTemplate treeTemplate;
	
	@Resource
	@Qualifier("mongoTemplateLogin")
	public MongoTemplate mongoTemplateLogin;

	public List<DBObject> getTree() {
		List<DBObject> dbObjects = new ArrayList<>();
		DBCursor dbCursor = treeTemplate.getCollection(MongoDBConst.stageTreeShop).find();
		dbCursor.forEach(dbObject -> {
			dbObjects.add(dbObject);
		});
		return dbObjects;
	}

	public List<TreeCover> geTreeCovers() {
		List<TreeCover> treeInfos = treeTemplate.findAll(TreeCover.class);
		return treeInfos;
	}

	public TreeDetail geTreeDetailById(long treeId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("treeId").is(treeId).and("isShow").is(1));
		TreeDetail detail = treeTemplate.findOne(query, TreeDetail.class);
		return detail;
	}

	public StageTreeShop getStageTreeById(long treeId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("treeId").is(treeId).and("isShow").is(1));
		StageTreeShop stageTree = treeTemplate.findOne(query, StageTreeShop.class);
		return stageTree;

	}

	public StageTreeRes getStageTreeRes(long treeId, long id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("treeId").is(treeId).and("id").is(id).and("isShow").is(1));
		StageTreeRes res = treeTemplate.findOne(query, StageTreeRes.class);
		return res;
	}
	
	public void incTreePlayerNum(long treeId) {
		try {
			mongoTemplateLogin.upsert(new Query(Criteria.where("treeId").is(treeId)), new Update().inc("numOfPlayer", 1), TreeLocalInfo.class);
		} catch (Exception e) {
			logger.info("",e);
		}
	}
	
	public void incTreePlayerPassed(long treeId) {
		try {
			mongoTemplateLogin.upsert(new Query(Criteria.where("treeId").is(treeId)), new Update().inc("numOfPassed", 1), TreeLocalInfo.class);
		} catch (Exception e) {
			logger.info("", e);
		}
	}
	
	public void incNodePlayerNum(long treeId, long nodeId) {
		try {
			mongoTemplateLogin.upsert(new Query(Criteria.where("treeId").is(treeId).and("nodeId").is(nodeId)), new Update().inc("playCount", 1), NodeLocalInfo.class);
		} catch (Exception e) {
			logger.info("", e);
		}
	}

	public void incNodePlayerPassed(long treeId, long nodeId, long index) {
		try {
			mongoTemplateLogin.upsert(new Query(Criteria.where("treeId").is(treeId).and("nodeId").is(nodeId).and("index").is(index)), new Update().inc("passCount", 1), NodeLocalInfo.class);
		} catch (Exception e) {
			logger.info("", e);
		}
	}
	
	public TreeLocalInfo getTreeLocalInfo(long treeId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("treeId").is(treeId));
		return mongoTemplateLogin.findOne(query, TreeLocalInfo.class);
	}

	public NodeLocalInfo getNodeLocalInfo(long treeId, long nodeId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("treeId").is(treeId).and("nodeId").is(nodeId));
		return mongoTemplateLogin.findOne(query, NodeLocalInfo.class);
	}

	public PanelLocalInfo getPanelLocalInfo(long treeId, long nodeId, int index) {
		Query query = new Query();
		query.addCriteria(Criteria.where("treeId").is(treeId).and("nodeId").is(nodeId).and("index").is(index));
		return mongoTemplateLogin.findOne(query, PanelLocalInfo.class);
	}
	
	public void save(Object data) {
		mongoTemplateLogin.save(data);
	}
}
