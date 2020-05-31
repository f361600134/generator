package com.kepe.dragon.entity;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kepe.dragon.persistent.domain.Item;

/**
*
* @author Jeremy
*/
public class ItemDomain {

	private static final Logger log = LoggerFactory.getLogger(ItemDomain.class);
	
	/** 
	* key: configId
	* value: Item
	*/
	private Map<Integer, Item> itemMap;
	
	public ItemDomain() {
		itemMap = Maps.newConcurrentMap();
	}
	
	/**
	 * 初始化
	 */
	public void init(List<Item> itemList) {
		for (Item item : itemList) {
			itemMap.put(item.getConfigId(), item);
		}
	}
	
	/**
	 * 获取所有
	 * @return
	 */
	public Collection<Item> getAll(){
		return itemMap.values();
	}
	
	/**
	*获取一个
	*/
	public Item getItem(Integer configId) {
		return itemMap.get(configId);
	}
	
	////////////业务代码////////////////////
}

