package com.kepe.dragon.data.config.pojo;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kepe.dragon.persistent.provider.${entityName}PersistentProvider;
/**
*
* @author Jeremy
*/
@Provider(${entityName}PersistentProvider.class)
public class ${entityName} extends ${entityName}Base{

	private static final Logger log = LoggerFactory.getLogger(${entityName}Service.class);
	
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
	
	
	public static ${entityName} create(){
		//TODO
	}
	
	
	
}
