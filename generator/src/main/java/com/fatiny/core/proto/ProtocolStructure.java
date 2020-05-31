package com.fatiny.core.proto;

import java.util.ArrayList;
import java.util.List;

/**
 * 协议结构
 */
public class ProtocolStructure {
	/**
	 * 协议名称
	 */
	private String name;
	/**
	 * 备注信息
	 */
	private String comment;
	/**
	 * 字段属性
	 */
	private List<ProtocolField> fields = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<ProtocolField> getFields() {
		return fields;
	}
	
	@Override
	public String toString() {
		return "ProtocolStructure [name=" + name + ", comment=" + comment + ", fields=" + fields + "]";
	}
}
