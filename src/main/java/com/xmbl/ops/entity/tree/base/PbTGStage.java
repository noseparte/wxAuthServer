package com.xmbl.ops.entity.tree.base;

import lombok.Data;

@Data
public class PbTGStage {
	
	private long StageId;//关卡的id
	private String  Name;//关卡的名称
	private int StageVictoryType;//关卡类型
	private boolean SingelModel;//关卡模式
	private int WinType;	  //胜利条件示意图0.无、1.传染、2.毛毛虫、3.经典
	private boolean IsSubmit;	  //是否上传过挑战商店
	private boolean IsOfficial;	  //是否是官方关卡
	private boolean IsTimeLimit;	  //是否限时关卡
	private int Downloads;	  //下载量	
	private String TextureUrl;	  //封面标示
	private int RuleType;	  //游戏规则 消除 收集

	
//	required int64    		StageId;
//	required int32    		StageVictoryType;
//	required string   		Name;
//	required string   		Dec;
//	required int32    		Step;
//	required int64    		Time;
//	required int32    		ColorNum;
//	required int32    		FishNum;
//	required int32    		LinkNum;
//	required bool     		IsAddTarget;
//	repeated PbTaskTarget   Targets1;
//	repeated PbTaskTarget   Targets2;
//	repeated PbBlock  		Blocks;
//	repeated PbDropBlock 	DropBlocks;
//	repeated int32          ItemIds;
//	repeated int32          ItemNums;
//	required int32			PropNum;
//	optional bool			SingelModel;
//	repeated PbPropChest    PropChests;
//	optional bool			DropModel;
//	repeated PbRollTarget   ARollTargets;
//	repeated PbRollTarget   BRollTargets;
//	repeated PbRollTarget   AutoRollTargets;
//	optional bool 			RollVictory;
//	optional bool			CanCreateFish		=  25[default = true];   //战斗中是否可以生成鱼(鸟/风车)
//	repeated PbCaterpillar  Caterpillars		=  26;	//棋盘上的毛毛虫
//	optional bool 			IsUsePropInBag		=  27;	//道具是否从玩家背包扣除
//	optional bool			IsAddInfectJudge	=  28[default = false]; //是否加入传染模式判断。判断内容为一方没有传染色块时，提前结束战斗。
//	repeated int32			SetColorNumElement  =  29;	//选中棋盘颜色值
//	optional bool			IsChangeedDropStage =  30;	//记录是否修改过掉落
//	optional bool			IsTimeLimit 		=  31[default = false];	//是否为限时关卡
//	optional int32			TimeLimitLength 	=  32[default = 0];	//限时关卡的倒计时时长
//	optional int32			RuleType			=  33[default = 0]; //游戏规则 消除 收集	
//	optional PbPlayer   	Author    	  		=  34;		//作者
//	optional string   		TextureUrl    		=  35;		//封面标示	
//	optional int32    		MoneyType     		=  36;		//货币类型	
//	optional int32    		Cost     	  		=  37;		//货币类型
//	optional string         VoiceUrl	  		=  38;		//声音标示
//	optional int32			VoiceTime	  		=  39;		//声音长度
//	repeated PbItemMotif    ItemMotifs			=  43;		//元素块主题列表
//	optional bool			IsDefaultMotif		=  44[default = true]; //是否使用默认主题
//	optional bool           IsOfficial			=  45[default = false];//是否是官方关卡
//	optional bool 			IsCanUseProp		=  46[default = false];//是否允许使用道具
//	repeated PbPropData 	Props		  	  	=  47;	//道具类型
//	optional int32 			StoryType			=  48;  //所属关卡集的模式	
//	optional int64 			ChallenageId		=  49;	//挑战ID
//	optional string         ChallenageKey		=  50;	//挑战名称
//	optional bool 			IsSubmitShop	 	=  51;  //是否勾选了上传挑战商店
//	optional bool 			IsSubmitPKShop	 	=  52;  //是否勾选了上传PK商店	
//	optional bool 			IsStepLimit			=  53[default = true];	//是否限制回合
//	optional bool 			IsConditionLimit	=  54[default = true];	//是否条件限制 
//	optional bool 			IsElementLimit1		=  55[default = false];	//是否限制元素1
//	optional bool 			IsElementLimit2		=  56[default = false];	//是否限制元素2
//	repeated PbTaskTarget	ALimitTargets1		=  57;
//	repeated PbTaskTarget	BLimitTargets1		=  58;
//	repeated PbTaskTarget	ALimitTargets2		=  59;
//	repeated PbTaskTarget	BLimitTargets2		=  60;
//	optional bool			IsSyncCollectTarget	=  61[default = false]; //是否直接收集任务目标
//	repeated int32			DropModels			=  62;	//掉落模式   0 随机掉落  1 顺序掉落  
//	optional bool			IsLimitUsePropCount	=  63[default = true];	//道具使用限制
//	optional int64			MotifGroupId		=  64;	//主题组ID
//	optional string 		MotifGroupName		=  65;	//主题组名称
//	repeated PbBelts		Belts				=  66;	//传送带数据
//	optional int32 			PermitCreateCompetition		=  67;	//允许其他玩家点赞收藏后开启竞技比赛
//	repeated PbMagicHat    	MagicHats    		=  68;     //魔术帽
//	optional int32    		PermitCreateStory   =  69[default = 1];     //收藏后允许作为关卡集的关卡
//	repeated PbHidden  		Hiddens				=  70;	//棋盘上的隐藏块
}
