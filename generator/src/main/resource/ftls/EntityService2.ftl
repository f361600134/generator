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

import com.kepe.dragon.persistent.domain.${entityName};
import com.kepe.dragon.entity.${entityName}Domain;
import com.kepe.dragon.persistent.dao.${entityName}DAO;



/**
 * ${entityName}控制器
 */
@Service
public class ${entityName}Service extends GameCacheBasic {
	
	private static final Logger log = LoggerFactory.getLogger(${entityName}Service.class);
	
	@Autowired
	private ${entityName}DAO ${entityName ? lower_case}Dao;
	
	public static Map<Long, ${entityName}Domain> PLAYER_DOMAINMAP = Maps.newConcurrentMap();
	
	@Override
	public void gc(long playerId) {
		PLAYER_DOMAINMAP.remove(playerId);
	}
	
	/**
	 * 获取域
	 */
	public ${entityName}Domain getDomain(long playerId) {
		${entityName}Domain domain = PLAYER_DOMAINMAP.get(playerId);
		if (domain == null) {
			List<${entityName}> data = initList(${entityName ? lower_case}Dao.getMoreById(playerId));
			domain = new ${entityName}Domain();
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
	public List<${entityName}> initList(List<${entityName}> list) {
		List<${entityName}> result = Lists.newCopyOnWriteArrayList();
		for (${entityName} ${entityName ? lower_case} : list) {
			if (${entityName ? lower_case} == null)
				continue;
			${entityName} temp = init${entityName}(${entityName ? lower_case});
			if (temp == null) {
				continue;
			}
			result.add(temp);
		}
		// 删除非法数据
//		if (!deleteList.isEmpty()) {
//			${entityName}DAO.setUsage2False(deleteList);
//		}
		return result;
	}
	
	/**
	 * 初始化${entityName}
	 * @return
	 */
	public ${entityName} init${entityName}(${entityName} ${entityName ? lower_case}) {
		if (${entityName ? lower_case} != null) {
			${entityName ? lower_case}.afterLoad();
		}
		return ${entityName ? lower_case};
	}
	
	/***
	 * 根据id获取${entityName}列表
	 * @param playerId
	 */
	public Collection<${entityName}> get${entityName}s(long playerId) {
		${entityName}Domain domain = getDomain(playerId);
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
	public void response${entityName}Info(${entityName}Domain domain) {
		Collection<${entityName}> ${entityName ? lower_case}s = domain.getAll();
		//AckuDungeonInfoResp resp = AckuDungeonInfoResp.newInstance();
		//resp.setDungeon(dungeon);
		//SendMessageUtil.sendResponse(dungeon.getPlayerId(), resp);
	}
	
	/////////////业务逻辑//////////////////
	
	
	
	

}