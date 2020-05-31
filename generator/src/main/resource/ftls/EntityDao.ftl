package com.kepe.dragon.persistent.dao;

import java.util.List;
import com.arti.dao.jade.annotation.DAO;
import com.arti.dao.jade.annotation.SQL;
import com.arti.dao.jade.annotation.SQLParam;
import com.kepe.dragon.persistent.domain.${entityName};

/**
* @author Jeremy
*/
@DAO
public interface ${entityName}DAO  {

	public static final String ID_FIELD	= " `${primary}` ";
	public static final String TABLE = " ${tableName} ";
	public static final String FIELD = " ${fileds} ";

	/**
	 * 增
	 */
	@SQL("INSERT INTO "+TABLE+" ("+FIELD+") VALUES(${javaFileds})")
	public void insert(@SQLParam("ei") ${entityName} data);
	
	/**
	 * 改
	 */
	@SQL("UPDATE "+TABLE+" SET ${sql} WHERE PlayerId=:ei.playerId")
	public int update(@SQLParam("ei") ${entityName} data);
	
	/**
	 * 查
	 */
	@SQL("SELECT "+FIELD+" FROM "+TABLE+" WHERE playerId= :playerId limit 1")
	public ${entityName} getOneById(@SQLParam("playerId") long playerId);
	
	/**
	 * 查多个
	 */
	@SQL("SELECT "+FIELD+" FROM "+TABLE+" WHERE playerId= :playerId")
	public List<${entityName}> getMoreById(@SQLParam("playerId") long playerId);
	
}
