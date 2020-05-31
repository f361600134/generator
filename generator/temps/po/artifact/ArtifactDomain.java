package com.kepe.dragon.entity;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kepe.dragon.persistent.domain.Artifact;

/**
*
* @author Jeremy
*/
public class ArtifactDomain {

	private static final Logger log = LoggerFactory.getLogger(ArtifactDomain.class);
	
	/** 
	* key: configId
	* value: Artifact
	*/
	private Map<Integer, Artifact> artifactMap;
	
	public ArtifactDomain() {
		artifactMap = Maps.newConcurrentMap();
	}
	
	/**
	 * 初始化
	 */
	public void init(List<Artifact> artifactList) {
		for (Artifact artifact : artifactList) {
			artifactMap.put(artifact.getConfigId(), artifact);
		}
	}
	
	/**
	 * 获取所有
	 * @return
	 */
	public Collection<Artifact> getAll(){
		return artifactMap.values();
	}
	
	/**
	*获取一个
	*/
	public Artifact getArtifact(Integer configId) {
		return artifactMap.get(configId);
	}
	
	////////////业务代码////////////////////
}

