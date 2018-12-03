package com.xmbl.ops.entity.tree.base;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.xmbl.ops.consts.MongoDBConst;

import lombok.Data;

@Data
@Document(collection = MongoDBConst.stageTreeRes)
public class StageTreeRes {
	@Id
	private String _id;
	@Field("id")
	private Long resId;//stageTreeRes的id字段，spring将命名为id的field当人mongo自己生成的字段，所以改个名字
	private Long treeId;
	private Long stageAuthorId;
	private Long streeAuthorId;
	private Long createTick;
	private Long submitTick;
	private Integer auditStatus;
	private Integer isShow;
	private Boolean needSave;
	private Integer energyIncome;

	private Shield shield;
	private List<Object> slaves;
	private Stage stage;

	@Data
	public static class Shield {
		private Boolean tex;
		private Boolean motif;
		private Boolean name;
		private Boolean intro;
		private Boolean voice;
		private Boolean plot;
	}

	@Data
	public static class Stage {
		private Long stageId;
		private Integer stageType;
		private Integer ruleType;
		private String name;
		private String dec;
		private String extureUrl;
		private Integer moneyType;
		private Integer cost;
		private String voiceUrl;
		private Long voiceTime;
		private Integer step;
		private Integer time;
		private Integer fishNum;
		private Integer linkNum;
		private Integer propNum;
		private Boolean isTargetAdd;
		private Boolean singelModel;
		private Boolean dropModel;
		private Boolean rollVictory;
		private Boolean createFish;
		private Boolean usePropInBag;
		private Boolean isAddInfectJudge;
		private Boolean isChangeedDropStage;
		private Boolean isTimeLimit;
		private Boolean isStepLimit;
		private Boolean isConditionLimit;
		private Boolean isElementLimit1;
		private Boolean isElementLimit2;
		private Boolean isSyncCollectTarget;
		private Boolean isDefaultMotif;
		private Long timeLimitLength;
		private Long createTime;
		private Long updateTime;
		private Boolean isOfficial;
		private Boolean isUpfoldPropItems;
		private Long cId;

		private String cKey;
		private Boolean isSubmitShop;
		private Boolean isSubmitPkShop;
		private Boolean isLimitUsePropCount;
		private Long motifGroupId;
		private String motifGroupName;

		private Author author;

		private List<Target> targets1;
		private List<Target> targets2;

		private List<Object> adTarget1;
		private List<Object> adTarget2;

		private List<Object> bdTarget1;
		private List<Object> bdTarget2;

		private List<Block> blocks;

		private List<DropBlock> dropBlocks;

		private List<Long> dropModels;

		private List<Object> items;
		private List<Object> propChests;
		private List<Object> magicHats;
		private List<Object> aRollTarget;
		private List<Object> bRollTarget;
		private List<Object> autoRollTarget;
		private List<Object> caterpillar;
		private List<Object> hiddens;
		private List<Object> belts;

		private List<Long> baseColor;

		private List<ItemMotif> itemMotifs;

		private Long permitCreateCompetition;

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
		private Integer sex;
		private Integer age;
		private Integer constellation;
		private String city;
		private Double[] geoPos;
		private Long[] avaters;
	}

	@Data
	public static class Target {
		private Long target;
		private Long num;
		private Long cur;
	}

	@Data
	public static class Block {
		private Long id1;
		private Long id2;
		private Long id3;
		private Long id4;
		private Long id5;
		private Long id6;
		private Long id7;
	}

	@Data
	public static class DropBlock {
		private List<Long> dropIds;
	}

	@Data
	public static class ItemMotif {
		@Field("id")
		private Integer itemMotifId;
		private String tex;
	}
}
