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

import com.kepe.dragon.service.${entityName}Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

<#if protoStructList ? exists>
<#list protoStructList as proto>
import com.kepe.dragon.data.proto.${proto.name};
</#list>
</#if>

/**
 * ${entityName}控制器
 */
@Controller("${entityName}控制器")
public class ${entityName}Controller {
	
	private static final Logger log = LoggerFactory.getLogger(${entityName}Service.class);
	
	@Autowired
	private ${entityName}Service ${entityName ? uncap_first}Service;
	
	
	<#if protoStructList ? exists>
	<#list protoStructList as proto>
	/** ${proto.comment} **/
	@Action("${proto.comment}")
	@ProtocolNo(PBProtocol.${proto.name}_VALUE)
	public NetworkResponse ${proto.name}(Session session, ${proto.name} req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = ${entityName ? uncap_first}Service.${proto.name ? uncap_first}();
		return null;
	}
	
	
	</#list>
	</#if>
	

}
