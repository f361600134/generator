package com.kepe.dragon.persistent.dao;

import java.util.List;
import com.arti.dao.jade.annotation.DAO;
import com.arti.dao.jade.annotation.SQL;
import com.arti.dao.jade.annotation.SQLParam;
import com.kepe.dragon.persistent.domain.Item;

/**
* @author Jeremy
*/
@DAO
public interface ItemDAO  {

	public static final String ID_FIELD	= " `ItemId` ";
	public static final String TABLE = " k_Item ";
	public static final String FIELD = " PlayerId,ItemId,ConfigId,Count,RecieveTime,Params ";

	/**
	 * 增
	 */
	@SQL("INSERT INTO "+TABLE+" ("+FIELD+") VALUES(ei:playerId,ei:itemId,ei:configId,ei:count,ei:recieveTime,ei:params)")
	public void insert(@SQLParam("ei") Item data);
	
	/**
	 * 改
	 */
	@SQL("UPDATE "+TABLE+" SET PlayerId=:ei.playerId,ItemId=:ei.itemId,ConfigId=:ei.configId,Count=:ei.count,RecieveTime=:ei.recieveTime,Params=:ei.params WHERE PlayerId=:ei.playerId")
	public int update(@SQLParam("ei") Item data);
	
	/**
	 * 查
	 */
	@SQL("SELECT "+FIELD+" FROM "+TABLE+" WHERE playerId= :playerId limit 1")
	public Item getOneById(@SQLParam("playerId") long playerId);
	
	/**
	 * 查多个
	 */
	@SQL("SELECT "+FIELD+" FROM "+TABLE+" WHERE playerId= :playerId")
	public List<Item> getMoreById(@SQLParam("playerId") long playerId);
	
}
