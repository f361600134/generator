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

import com.kepe.dragon.persistent.domain.Artifact;
import com.kepe.dragon.entity.ArtifactDomain;
import com.kepe.dragon.persistent.dao.ArtifactDAO;



/**
 * Artifact控制器
 */
@Service
public class ArtifactService extends GameCacheBasic {
	
	private static final Logger log = LoggerFactory.getLogger(ArtifactService.class);
	
	@Autowired
	private ArtifactDAO artifactDao;
	
	public static Map<Long, ArtifactDomain> PLAYER_DOMAINMAP = Maps.newConcurrentMap();
	
	@Override
	public void gc(long playerId) {
		PLAYER_DOMAINMAP.remove(playerId);
	}
	
	/**
	 * 获取域
	 */
	public ArtifactDomain getDomain(long playerId) {
		ArtifactDomain domain = PLAYER_DOMAINMAP.get(playerId);
		if (domain == null) {
			List<Artifact> data = initList(artifactDao.getMoreById(playerId));
			domain = new ArtifactDomain();
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
	public List<Artifact> initList(List<Artifact> list) {
		List<Artifact> result = Lists.newCopyOnWriteArrayList();
		for (Artifact artifact : list) {
			if (artifact == null)
				continue;
			Artifact temp = initArtifact(artifact);
			if (temp == null) {
				continue;
			}
			result.add(temp);
		}
		// 删除非法数据
//		if (!deleteList.isEmpty()) {
//			ArtifactDAO.setUsage2False(deleteList);
//		}
		return result;
	}
	
	/**
	 * 初始化Artifact
	 * @return
	 */
	public Artifact initArtifact(Artifact artifact) {
		if (artifact != null) {
			artifact.afterLoad();
		}
		return artifact;
	}
	
	/***
	 * 根据id获取Artifact列表
	 * @param playerId
	 */
	public Collection<Artifact> getArtifacts(long playerId) {
		ArtifactDomain domain = getDomain(playerId);
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
	public void responseArtifactInfo(ArtifactDomain domain) {
		Collection<Artifact> artifacts = domain.getAll();
		//AckuDungeonInfoResp resp = AckuDungeonInfoResp.newInstance();
		//resp.setDungeon(dungeon);
		//SendMessageUtil.sendResponse(dungeon.getPlayerId(), resp);
	}
	
	/////////////业务逻辑//////////////////
	
	
	
	

}