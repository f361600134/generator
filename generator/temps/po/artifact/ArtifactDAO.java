package com.kepe.dragon.persistent.dao;

import java.util.List;
import com.arti.dao.jade.annotation.DAO;
import com.arti.dao.jade.annotation.SQL;
import com.arti.dao.jade.annotation.SQLParam;
import com.kepe.dragon.persistent.domain.Artifact;

/**
* @author Jeremy
*/
@DAO
public interface ArtifactDAO  {

	public static final String ID_FIELD	= " `PlayerId` ";
	public static final String TABLE = " k_Artifact ";
	public static final String FIELD = " PlayerId,ConfigId,Level,Exp,RefineLv,SkillLv,State,HolySealLv,SkinId,MissionStr ";

	/**
	 * 增
	 */
	@SQL("INSERT INTO "+TABLE+" ("+FIELD+") VALUES(ei:playerId,ei:configId,ei:level,ei:exp,ei:refineLv,ei:skillLv,ei:state,ei:holySealLv,ei:skinId,ei:missionStr)")
	public void insert(@SQLParam("ei") Artifact data);
	
	/**
	 * 改
	 */
	@SQL("UPDATE "+TABLE+" SET PlayerId=:ei.playerId,ConfigId=:ei.configId,Level=:ei.level,Exp=:ei.exp,RefineLv=:ei.refineLv,SkillLv=:ei.skillLv,State=:ei.state,HolySealLv=:ei.holySealLv,SkinId=:ei.skinId,MissionStr=:ei.missionStr WHERE PlayerId=:ei.playerId")
	public int update(@SQLParam("ei") Artifact data);
	
	/**
	 * 查
	 */
	@SQL("SELECT "+FIELD+" FROM "+TABLE+" WHERE playerId= :playerId limit 1")
	public Artifact getOneById(@SQLParam("playerId") long playerId);
	
	/**
	 * 查多个
	 */
	@SQL("SELECT "+FIELD+" FROM "+TABLE+" WHERE playerId= :playerId")
	public List<Artifact> getMoreById(@SQLParam("playerId") long playerId);
	
}
