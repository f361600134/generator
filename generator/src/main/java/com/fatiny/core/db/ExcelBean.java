package com.fatiny.core.db;

/**
 * 读取excel主题内容, 生成Javabean
 * 
 * @auth Jeremy
 * @date 2019年9月16日上午10:55:41
 */
public class ExcelBean {

	private String field; // Java字段
	private String desc; // 描述
	private String primary; // 是否主键,第一个主键为1,第二个为2,依次累加,程序根据从小到大顺序组装mongo_id
	private String type; // 类型
	private String tbField; // 表字段

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		// this.wrapper = StringUtils.getWrapper(type);
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getTbField() {
		return tbField;
	}

	public void setTbField(String tbField) {
		this.tbField = tbField;
	}

	// public String getWrapper() {
//		return wrapper;
//	}
//	public void setWrapper(String wrapper) {
//		this.wrapper = wrapper;
//	}
	public ExcelBean() {
	}

	public ExcelBean(String field, String type, String desc, String primary) {
		super();
		this.field = field;
		this.type = type;
		this.desc = desc;
		this.primary = primary;
	}

	@Override
	public String toString() {
		return "ExcelBean [field=" + field + ", type=" + type + ", desc=" + desc + ", primary=" + primary + "]";
	}
}
