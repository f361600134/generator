package com.fatiny.core.db;

import java.util.Arrays;
import java.util.List;

/**
 * 用于nosql的索引
 * @auth Jeremy
 * @date 2019年9月17日上午9:35:06
 */
public class ExcelIndex {
	
	private String indexName;//索引名称
	private String fieldArr; //字段组, 需要跟字段相同
	private String options;	 //所支持的操作选项
	
	//转换后的数据
	private List<String> fieldList;
	private List<String> optionList;
	
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public void setFieldArr(String fieldArr) {
		this.fieldArr = fieldArr;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	
	public List<String> getFieldList() {
		fieldList = Arrays.asList(fieldArr.split(","));
		return fieldList;
	}
	public List<String> getOptionList() {
		optionList = Arrays.asList(options.split(","));
		return optionList;
	}
	@Override
	public String toString() {
		return "ExcelIndex [indexName=" + indexName + ", fieldArr=" + fieldArr + ", options=" + options + ", fieldList=" + getFieldList() + ", optionList=" + getOptionList() + "]";
	}
}
