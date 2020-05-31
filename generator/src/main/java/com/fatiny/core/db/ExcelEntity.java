package com.fatiny.core.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fatiny.util.StringUtils;

/**
 * 封装一个实体名, 以及属性信息
 * @auth Jeremy
 * @date 2019年9月16日上午10:56:00
 */
public class ExcelEntity {

	private String tablName; //表名
	private String entityName;// 实体名
	private List<ExcelBean> entityBeans;// 实体内属性
	private List<ExcelIndex> entityIndexs;//实体索引
	
	private List<String> primaryKeys;//主键
	
	public String getTablName() {
		String newer = tablName.replace("k_", "");
		newer = StringUtils.firstCharUpper(newer);
		newer = "k_"+newer;
		return newer;
	}

	public void setTablName(String tablName) {
		this.tablName = tablName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<ExcelBean> getEntityBeans() {
		return entityBeans;
	}
	
	public void setEntityBeans(List<ExcelBean> entityBeans) {
		this.entityBeans = entityBeans;
	}
	
	public void addEntityBeans(ExcelBean entityBean) {
		this.entityBeans.add(entityBean);
	}
	
	public String getPrimary() {
		return primaryKeys.size() > 0 ? primaryKeys.get(0) : "";
	}
	
	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public List<ExcelIndex> getEntityIndexs() {
		boolean bool = false;
		for (ExcelIndex excelIndex : entityIndexs) {
			List<String> fieldStrList = excelIndex.getFieldList();
			for (String fieldStr : fieldStrList) {
				for (ExcelBean excelBean : entityBeans) {
					if (fieldStr.equals(excelBean.getField())) {
						bool = true;
					}
				}
			}
			if (!bool) 
				throw new RuntimeException("索引错误, 找不到指定索引, entityName:"+ entityName+", fieldStrList:"+fieldStrList);
			bool = false;
		}
		return entityIndexs;
	}

	public void setEntityIndexs(List<ExcelIndex> entityIndexs) {
		this.entityIndexs = entityIndexs;
	}

	/**
	 * 设置主键
	 */
	public void setPrimarys(List<String> primarys) {
		int index = 0;
		for (String primary : primarys) {
			for (ExcelBean excelBean : entityBeans) {
				if (excelBean.getField().equals(primary)) {
					++index;
					excelBean.setPrimary(String.valueOf(index));
				}
			}
		}
		this.primaryKeys = primarys;
	}
	
	public ExcelEntity() {
		entityBeans = new ArrayList<>();
		entityIndexs = new ArrayList<>();
	}
	
	public ExcelEntity(String entityName) {
		super();
		this.entityName = entityName;
		entityBeans = new ArrayList<>();
		entityIndexs = new ArrayList<>();
	}
	
	public ExcelEntity(String entityName, List<ExcelBean> entityBeans) {
		super();
		this.entityName = entityName;
		this.entityBeans = entityBeans;
	}
	
	@Override
	public String toString() {
		return "ExcelEntity [entityName=" + entityName + ", entityBeans=" + entityBeans + ", entityIndexs=" + entityIndexs + "]";
	}

	/**
	 * 生成toSting方法
	 * @return  
	 * @return String  
	 * @date 2019年9月16日上午10:25:13
	 */
	public String getToStr() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"").append(entityName);
		
		int length = 0;
		for (ExcelBean excelBean : entityBeans) {
			length ++;
			if (length == 1) {
				builder.append(" [");
			}else {
				builder.append(", ");
			}
			builder.append(excelBean.getField()).append("= \"+ ").append(excelBean.getField());
			if (length % 5 == 0) {
				builder.append("\n\t\t\t\t");
			}
			if (length == entityBeans.size()) {
				builder.append("+\"]\";");
			}else {
				builder.append(" +\"");
			}
		}
		return builder.toString();
	}
	
	/**
	 * 生成主键
	 */
	public String getPrimaryKey() {
		Map<Integer, ExcelBean> primaryMap = null;
		try {
			primaryMap = new TreeMap<Integer, ExcelBean>();
			for (ExcelBean excelBean : entityBeans) {
				if (excelBean.getPrimary() == null) 
					continue;
				int primary = Integer.parseInt(excelBean.getPrimary());
				if (primary > 0) {
					primaryMap.put(primary, excelBean);
				}
			}
			if (primaryMap.isEmpty())
				throw new RuntimeException("请设置主键!, entityName:"+entityName) ;
			
			StringBuilder builder = new StringBuilder();
			String plus = "+\"-\"+";
			for (ExcelBean excelBean : primaryMap.values()) {
				builder.append(excelBean.getField());
				builder.append(plus);
			}
			int start = builder.length() - plus.length();
			builder.delete(start, builder.length());
			return builder.toString();
		} catch (Exception e) {
			throw new RuntimeException("生成主键异常, entityName:"+entityName+", primaryMap:"+primaryMap, e) ;
		}
	}
	
	/**
	 * 生成数据库字段
	 * @return
	 */
	public String getFileds() {
		StringBuilder builder = new StringBuilder();
		for (ExcelBean excelBean : entityBeans) {
			builder.append(excelBean.getTbField()).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * java字段
	 * @return
	 */
	public String getJavaFiled() {
		StringBuilder builder = new StringBuilder();
		for (ExcelBean excelBean : entityBeans) {
			builder.append("ei:").append(excelBean.getField()).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	//
	/**
	 * java字段
	 * @return
	 * PlayerId=:ei.playerId, ItemId=:ei.itemId, ConfigId=:ei.configId,Count=:ei.count,Params=:ei.params WHERE ItemId=:ei.itemId LIMIT 1
	 */
	public String getSql() {
		StringBuilder builder = new StringBuilder();
		for (ExcelBean excelBean : entityBeans) {
			builder.append(excelBean.getTbField()).append("=:ei.").append(excelBean.getField()).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	/**
	 * 生成索引名称
	 * @return  
	 * @return String  
	 * @date 2019年9月17日下午1:05:27
	 */
	public String getIndexNames() {
		StringBuilder builder = new StringBuilder();
		for (ExcelIndex excelIndex: entityIndexs) {
			builder.append("\"").append(excelIndex.getIndexName()).append("\",");
		}
		if (builder.length() > 0) 
			builder.deleteCharAt(builder.length()-1);
		
		return builder.toString();
	}
	
	/**
	 * 生成索引
	 * 
	 * public static final Bson[] Indexs= {Filters.and(Filters.eq(initServerIdField, 1), Filters.eq(userNameField, 1)),
	 *						Filters.and(Filters.eq(initServerIdField, 1), Filters.eq(nickNameField, 1))};
	 * 
	 * @return  
	 * @return String  
	 * @date 2019年9月17日下午1:05:27
	 */
	public String getIndexs() {
		StringBuilder result = new StringBuilder();
		String model = "Filters.eq(%sField, 1)";
		for (ExcelIndex excelIndex : entityIndexs) {
			StringBuilder builder = new StringBuilder("Filters.and(");
			for (String fieldStr : excelIndex.getFieldList()) {
				String child = String.format(model, fieldStr);
				builder.append(child).append(",");
			}
			if (builder.length() > 0)
				builder.deleteCharAt(builder.length()-1);
//			builder.append("),");
			builder.append(")\n\t\t\t\t,");
			result.append(builder);
		}
		if (result.length() > 0)
			result.deleteCharAt(result.length()-1);
		return result.toString();
	}
	
	/**
	 * public static final IndexOptions[] Options= {
					new IndexOptions().unique(true).background(true),
					new IndexOptions().unique(true).background(true)};
	 */
	public String getOptions() {
		StringBuilder result = new StringBuilder();
		for (ExcelIndex excelIndex : entityIndexs) {
			StringBuilder builder = new StringBuilder("new IndexOptions().");
			for (String option : excelIndex.getOptionList()) {
				builder.append(option).append("(true).");
			}
			if (builder.length() > 0) {
				builder.deleteCharAt(builder.length()-1);
			}
			builder.append(",");
			result.append(builder);
		}
		if (result.length() > 0) {
			result.deleteCharAt(result.length()-1);
		}
		return result.toString();
	}
}















