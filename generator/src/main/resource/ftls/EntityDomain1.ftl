package com.kepe.dragon.entity;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kepe.dragon.persistent.domain.${entityName};

/**
*
* @author Jeremy
*/
public class ${entityName}Domain {

	private static final Logger log = LoggerFactory.getLogger(${entityName}Domain.class);
	
	private ${entityName} ${entityName ? lower_case};
	
	public void init(${entityName} ${entityName ? lower_case}){
		try {
			this.dungeon = dungeon;
		} catch (Exception e) {
			log.error("${entityName}Domain error", e);
		}
	}
	
	public ${entityName} get${entityName}() {
		return ${entityName ? lower_case};
	}
	
	////////////业务代码////////////////////
}
