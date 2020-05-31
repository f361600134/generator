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
	 * @return
	 */
	public DungeonDomain getDomain(long playerId) {
		${entityName}Domain domain = PLAYER_DOMAINMAP.get(playerId);
		if (domain == null) {
			${entityName} ${entityName ? lower_case} = ${entityName ? lower_case}Dao.getOneById(playerId);
			if (${entityName ? lower_case} == null) {
				${entityName ? lower_case} = ${entityName}.create(playerId);
				Context.getDataSyncService().commitSync(${entityName ? lower_case});
			}
			domain = new ${entityName}Domain();
			domain.init(${entityName ? lower_case});
			PLAYER_DOMAINMAP.put(playerId, domain);
		}
		return domain;
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