package com.kepe.dragon.persistent.domain;

import com.kepe.asyncdb.SyncObject;

/**
* ItemBase
* @author Jeremy
*/
public class ItemBase extends SyncObject {

	public long playerId;//玩家ID
	public long itemId;//道具唯一id
	public int configId;//道具配置id
	public int count;//物品数量
	public int recieveTime;//获得时间
	public String params;//额外参数如符文的随机属性、技能,神装随机属性
	
	public ItemBase(){
		this.params = "";
	}
	
	/** 玩家ID **/
	public long getPlayerId(){
		return this.playerId;
	}
	
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/** 道具唯一id **/
	public long getItemId(){
		return this.itemId;
	}
	
	public void setItemId(long itemId){
		this.itemId = itemId;
	}
	
	/** 道具配置id **/
	public int getConfigId(){
		return this.configId;
	}
	
	public void setConfigId(int configId){
		this.configId = configId;
	}
	
	/** 物品数量 **/
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	/** 获得时间 **/
	public int getRecieveTime(){
		return this.recieveTime;
	}
	
	public void setRecieveTime(int recieveTime){
		this.recieveTime = recieveTime;
	}
	
	/** 额外参数如符文的随机属性、技能,神装随机属性 **/
	public String getParams(){
		return this.params;
	}
	
	public void setParams(String params){
		this.params = params;
	}
	
	
	@Override
	public String toString() {
		return "Item [playerId= "+ playerId +", itemId= "+ itemId +", configId= "+ configId +", count= "+ count +", recieveTime= "+ recieveTime
				 +", params= "+ params+"]";
	}
}
