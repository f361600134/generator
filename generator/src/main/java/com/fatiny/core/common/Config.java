package com.fatiny.core.common;

import java.util.Map;

import com.alibaba.druid.pool.DruidDataSource;
import com.fatiny.core.param.annotation.ParamMapping;


@ParamMapping(prefix = "config")
public class Config {
	
	public static String suffix;
	public static String dbName;
	public static String outputPath;
	public static String ftlPath;
	public static DruidDataSource dbSource;
	
	/**
	 * 生成表列表
	 */
	public static Map<String, Info> genTbMap;
	
	/**
	 * 表信息
	 * @author Jereme
	 *
	 */
	public static class Info{
		
		private String tbName;//实体名
		private boolean o2o;//true表示一对一,false表示一对多
		
		public String getTbName() {
			return tbName;
		}
		public void setTbName(String tbName) {
			this.tbName = tbName;
		}
		public boolean isO2o() {
			return o2o;
		}
		public void setO2o(boolean o2o) {
			this.o2o = o2o;
		}
	}
	
	
}
