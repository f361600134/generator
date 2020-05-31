package com.kepe.dragon.persistent.domain;

import com.kepe.asyncdb.SyncObject;

/**
* ArtifactBase
* @author Jeremy
*/
public class ArtifactBase extends SyncObject {

	public long playerId;//玩家ID
	public int configId;//神器配置id
	public int level;//神器等级
	public int exp;//经验
	public int refineLv;//精炼等级
	public int skillLv;//技能等级
	public int state;//状态
	public int holySealLv;//圣印等级
	public int skinId;//皮肤id
	public String missionStr;//任务信息
	
	public ArtifactBase(){
		this.missionStr = "";
	}
	
	/** 玩家ID **/
	public long getPlayerId(){
		return this.playerId;
	}
	
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/** 神器配置id **/
	public int getConfigId(){
		return this.configId;
	}
	
	public void setConfigId(int configId){
		this.configId = configId;
	}
	
	/** 神器等级 **/
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	/** 经验 **/
	public int getExp(){
		return this.exp;
	}
	
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/** 精炼等级 **/
	public int getRefineLv(){
		return this.refineLv;
	}
	
	public void setRefineLv(int refineLv){
		this.refineLv = refineLv;
	}
	
	/** 技能等级 **/
	public int getSkillLv(){
		return this.skillLv;
	}
	
	public void setSkillLv(int skillLv){
		this.skillLv = skillLv;
	}
	
	/** 状态 **/
	public int getState(){
		return this.state;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	/** 圣印等级 **/
	public int getHolySealLv(){
		return this.holySealLv;
	}
	
	public void setHolySealLv(int holySealLv){
		this.holySealLv = holySealLv;
	}
	
	/** 皮肤id **/
	public int getSkinId(){
		return this.skinId;
	}
	
	public void setSkinId(int skinId){
		this.skinId = skinId;
	}
	
	/** 任务信息 **/
	public String getMissionStr(){
		return this.missionStr;
	}
	
	public void setMissionStr(String missionStr){
		this.missionStr = missionStr;
	}
	
	
	@Override
	public String toString() {
		return "Artifact [playerId= "+ playerId +", configId= "+ configId +", level= "+ level +", exp= "+ exp +", refineLv= "+ refineLv
				 +", skillLv= "+ skillLv +", state= "+ state +", holySealLv= "+ holySealLv +", skinId= "+ skinId +", missionStr= "+ missionStr
				+"]";
	}
}
