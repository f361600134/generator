package com.kepe.dragon.controller;

import java.util.List;
import java.util.Map;

import com.kepe.rpg.annotation.Action;
import com.kepe.rpg.annotation.Controller;
import com.kepe.rpg.annotation.ProtocolNo;
import com.kepe.rpg.framework.Session;
import com.kepe.rpg.framework.proto.NetworkResponse;
import com.kepe.dragon.data.proto.PBDefine.PBProtocol;

import org.springframework.beans.factory.annotation.Autowired;

import com.kepe.dragon.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kepe.dragon.data.proto.ReqOperateItem;
import com.kepe.dragon.data.proto.ReqBuyItem;

/**
 * Item控制器
 */
@Controller("Item控制器")
public class ItemController {
	
	private static final Logger log = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemService itemService;
	
	
	/** 请求操作物品 **/
	@Action("请求操作物品")
	@ProtocolNo(PBProtocol.ReqOperateItem_VALUE)
	public NetworkResponse ReqOperateItem(Session session, ReqOperateItem req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = itemService.reqOperateItem();
		return null;
	}
	
	
	/** 请求购买物品 **/
	@Action("请求购买物品")
	@ProtocolNo(PBProtocol.ReqBuyItem_VALUE)
	public NetworkResponse ReqBuyItem(Session session, ReqBuyItem req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = itemService.reqBuyItem();
		return null;
	}
	
	
	

}
