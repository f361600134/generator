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

import com.kepe.dragon.service.ArtifactService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kepe.dragon.data.proto.ReqArtifactOpt;
import com.kepe.dragon.data.proto.ReqArtifactReceiveTask;
import com.kepe.dragon.data.proto.ReqArtifactList;
import com.kepe.dragon.data.proto.ReqArtifactHolySeal;

/**
 * Artifact控制器
 */
@Controller("Artifact控制器")
public class ArtifactController {
	
	private static final Logger log = LoggerFactory.getLogger(ArtifactService.class);
	
	@Autowired
	private ArtifactService artifactService;
	
	
	/** 请求神器操作 **/
	@Action("请求神器操作")
	@ProtocolNo(PBProtocol.ReqArtifactOpt_VALUE)
	public NetworkResponse ReqArtifactOpt(Session session, ReqArtifactOpt req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = artifactService.reqArtifactOpt();
		return null;
	}
	
	
	/** 请求领取神器任务 **/
	@Action("请求领取神器任务")
	@ProtocolNo(PBProtocol.ReqArtifactReceiveTask_VALUE)
	public NetworkResponse ReqArtifactReceiveTask(Session session, ReqArtifactReceiveTask req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = artifactService.reqArtifactReceiveTask();
		return null;
	}
	
	
	/** 请求神器信息 **/
	@Action("请求神器信息")
	@ProtocolNo(PBProtocol.ReqArtifactList_VALUE)
	public NetworkResponse ReqArtifactList(Session session, ReqArtifactList req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = artifactService.reqArtifactList();
		return null;
	}
	
	
	/** 请求圣印 **/
	@Action("请求圣印")
	@ProtocolNo(PBProtocol.ReqArtifactHolySeal_VALUE)
	public NetworkResponse ReqArtifactHolySeal(Session session, ReqArtifactHolySeal req) {
		long playerId = session.getUid();
		//TODO somthing.
		//int code = artifactService.reqArtifactHolySeal();
		return null;
	}
	
	
	

}
