package com.kepe.dragon.service;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.google.common.collect.Lists;
import com.kepe.dragon.core.cache.GameCacheBasic;

import com.kepe.dragon.persistent.domain.Item;
import com.kepe.dragon.entity.ItemDomain;
import com.kepe.dragon.persistent.dao.ItemDAO;



/**
 * Item控制器
 */
@Service
public class ItemService extends GameCacheBasic {
	
	private static final Logger log = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	private ItemDAO itemDao;
	
	public static Map<Long, ItemDomain> PLAYER_DOMAINMAP = Maps.newConcurrentMap();
	
	@Override
	public void gc(long playerId) {
		PLAYER_DOMAINMAP.remove(playerId);
	}
	
	/**
	 * 获取域
	 */
	public ItemDomain getDomain(long playerId) {
		ItemDomain domain = PLAYER_DOMAINMAP.get(playerId);
		if (domain == null) {
			List<Item> data = initList(itemDao.getMoreById(playerId));
			domain = new ItemDomain();
			domain.init(data);
			PLAYER_DOMAINMAP.put(playerId, domain);
		}
		return domain;
	}
	
	/**
	 * 初始化列表
	 * 
	 * @param propsList
	 * @return
	 */
	public List<Item> initList(List<Item> list) {
		List<Item> result = Lists.newCopyOnWriteArrayList();
		for (Item item : list) {
			if (item == null)
				continue;
			Item temp = initItem(item);
			if (temp == null) {
				continue;
			}
			result.add(temp);
		}
		// 删除非法数据
//		if (!deleteList.isEmpty()) {
//			ItemDAO.setUsage2False(deleteList);
//		}
		return result;
	}
	
	/**
	 * 初始化Item
	 * @return
	 */
	public Item initItem(Item item) {
		if (item != null) {
			item.afterLoad();
		}
		return item;
	}
	
	/***
	 * 根据id获取Item列表
	 * @param playerId
	 */
	public Collection<Item> getItems(long playerId) {
		ItemDomain domain = getDomain(playerId);
		if (domain == null) {
			return Lists.newCopyOnWriteArrayList();;
		}
		return domain.getAll();
	}
	
	
	/**
	 * 登陆
	 */
	public void onLogin() {

	}
	
	/**
	 * 更新信息
	 */
	public void responseItemInfo(ItemDomain domain) {
		Collection<Item> items = domain.getAll();
		//AckuDungeonInfoResp resp = AckuDungeonInfoResp.newInstance();
		//resp.setDungeon(dungeon);
		//SendMessageUtil.sendResponse(dungeon.getPlayerId(), resp);
	}
	
	/////////////业务逻辑//////////////////
	
	
	
	

}