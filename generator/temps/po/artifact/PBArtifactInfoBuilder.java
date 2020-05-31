package com.kepe.dragon.vo.item;


import java.util.List;
import java.util.Map;
import com.google.protobuf.GeneratedMessageLite;
import com.kepe.dragon.data.proto.ItemInfo;
import com.kepe.rpg.framework.proto.NetworkResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.GeneratedMessageLite;
import com.kepe.dragon.data.proto.ItemInfo;
import com.kepe.rpg.framework.proto.NetworkResponse;

/**
* ItemInfoBuilder
* @author Jeremy
*/
public class PBArtifactInfoBuilder extends NetworkResponse {

	private static final Logger log = LoggerFactory.getLogger(ArtifactService.class);
	
	protected boolean dirty;			//新数据标识,不存储
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public boolean isDirty() {
		return dirty;
	}
	
	/**
	 * 加载后调用
	 */
	public void afterLoad() {
	}
	
	/**
	 * 入库前操作
	 */
	public void beforeSave() {
	}
	
	
	public static Artifact create(){
		//TODO
	}
	
	
	
}
