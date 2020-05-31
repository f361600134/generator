package com.kepe.dragon.entity;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kepe.dragon.persistent.domain.${entityName};

/**
*
* @author Jeremy
*/
public class ${entityName}Domain {

	private static final Logger log = LoggerFactory.getLogger(${entityName}Domain.class);
	
	/** 
	* key: configId
	* value: ${entityName}
	*/
	private Map<Integer, ${entityName}> ${entityName ? lower_case}Map;
	
	public ${entityName}Domain() {
		${entityName ? lower_case}Map = Maps.newConcurrentMap();
	}
	
	/**
	 * 初始化
	 */
	public void init(List<${entityName}> ${entityName ? lower_case}List) {
		for (${entityName} ${entityName ? lower_case} : ${entityName ? lower_case}List) {
			${entityName ? lower_case}Map.put(${entityName ? lower_case}.getConfigId(), ${entityName ? lower_case});
		}
	}
	
	/**
	 * 获取所有
	 * @return
	 */
	public Collection<${entityName}> getAll(){
		return ${entityName ? lower_case}Map.values();
	}
	
	/**
	*获取一个
	*/
	public ${entityName} get${entityName}(Integer configId) {
		return ${entityName ? lower_case}Map.get(configId);
	}
	
	////////////业务代码////////////////////
}

