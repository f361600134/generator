package com.kepe.dragon.data.config.pojo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kepe.dragon.persistent.provider.ItemPersistentProvider;
/**
*
* @author Jeremy
*/
@Provider(ItemPersistentProvider.class)
public class Item extends ItemBase{

	private static final Logger log = LoggerFactory.getLogger(ItemService.class);
	
	protected boolean dirty;			//新数据标识,不存储
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public boolean isDirty() {
		return dirty;
	}
	
	/**
	 * 加载后调用
	 */
	public void afterLoad() {
	}
	
	/**
	 * 入库前操作
	 */
	public void beforeSave() {
	}
	
	
	public static Item create(){
		//TODO
	}
	
	
	
}
