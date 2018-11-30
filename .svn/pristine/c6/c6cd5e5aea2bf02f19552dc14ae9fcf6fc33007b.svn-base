package com.xmbl.ops.entity.tree.base;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.xmbl.ops.consts.MongoDBConst;

import lombok.Data;

@Data
@Document(collection = MongoDBConst.stageTreeShop)
public class StageTreeShop {

	public Boolean isShareGetEnergy() {
		return isShareGetEnergy;
	}

	public void setShareGetEnergy(Boolean isShareGetEnergy) {
		this.isShareGetEnergy = isShareGetEnergy;
	}

	private String _id;
	private Long treeId;
	private String name;
	private String textureUrl;
	private String dec;
	private String voiceUrl;
	private Long voiceTime;
	private Boolean isCanCreateBattle;
	private Integer maxEnergy;
	private Integer pce;
	private Integer eri;
	private Integer shareGetEnergy;
	private Integer buyEnergyCost;
	private Integer buyEnergyCostType;
	private Boolean isEnergyRecoveryInterval;
	private Boolean isShareGetEnergy;
	private Integer storyType;
	private Boolean isConditionLimit;
	private Integer conditionType;
	private Integer conditionValue;
	private Boolean isElementLimit1;
	private Boolean isElementLimit2;
	private Integer serial;
	private Long headNodeId;
	private Long createTick;
	private Long submitTick;
	private Long updateTick;
	private Integer isShow;
	private Integer praise;
	private List<Object> aLimitTargets1;
	private List<Object> aLimitTargets2;
	private List<Object> bLimitTargets1;
	private List<Object> bLimitTargets2;
	private Integer propGetType;
	private Boolean isUsePropLimit;
	private List<Object> Props;
	private List<Node> Nodes;
	private Author author;
	
	@Data
	public static class Node {
		private Long nodeId;
		private Long index;
		private Integer nodeType = 0;
		private Integer pathType;
		private Integer pce;
		private String nodeName;
		private Integer openType = 0;
		private Integer passCondition;
		private Long priorId;
		private Long nextId;
		private Long branchId;
		private List<Panel> panels;
	}

	@Data
	public static class Panel {
		private Long index;
		private Long stageId;
		private Integer stageType;
		private String stageTexurl;
		private String stageName;
		private String authorName;
		private Long authorId;
		private Integer authorAvatar;
		private String plotName;
		private String plotTexurl;
		private Long plotId;
		private Integer difficulty;
		private String tag;
		private Boolean singelModel;
	}

	@Data
	public static class Author {
		@Field("id")
		private Long authorId;
		private String name;
		private Integer level;
		private Long sid;
		private Long csId;
		private Integer ll;
		private Integer ls;
		private String avatar;
		private Boolean needGuild;
		private Integer model;
		private Integer nationality;
		private String signature;
		private Byte sex;
		private Integer age;
		private Integer constellation;
		private String city;
		private Double[] geoPos;
		private Integer[] avaters;
	}

}
