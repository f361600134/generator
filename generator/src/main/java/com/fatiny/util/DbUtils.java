package com.fatiny.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.fatiny.core.common.Config;
import com.fatiny.core.db.ExcelBean;
import com.fatiny.core.db.ExcelEntity;

public class DbUtils {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DbUtils.class);
	
	public static List<ExcelEntity> readDb(DataSource ds) {
		try {
			Connection conn = ds.getConnection();
			ResultSet tbRs = conn.getMetaData().getTables(Config.dbName, null, "%", new String[] { "TABLE" });
			//所有表
			List<String> tbNames = new LinkedList<>();
			while(tbRs.next()) {
				tbNames.add(tbRs.getString("TABLE_NAME"));
			}
			log.info("tbNames:{}", tbNames);
			
			List<ExcelEntity> result = new ArrayList<ExcelEntity>();
			for (String tbName : tbNames) {//单表
				ResultSet rs = conn.getMetaData().getColumns(Config.dbName, null, tbName, null);
				ResultSet keys = conn.getMetaData().getPrimaryKeys(Config.dbName, null, tbName);

				//组装表信息
				ExcelEntity excelEntity = new ExcelEntity();
				excelEntity.setTablName(tbName);
				excelEntity.setEntityName(StringUtils.split(tbName));
				ExcelBean excelBean = null;
				//列
				while (rs.next()) {
					String ctype = rs.getString("TYPE_NAME");
					try {
						ctype = StringUtils.sqlTypeToJavaType(ctype);
					} catch (Exception e) {
						e.printStackTrace();
					}
					excelBean = new ExcelBean();
					//player_id -> playerId
					excelBean.setField(StringUtils.firstCharLower(rs.getString("COLUMN_NAME")));
					excelBean.setTbField(StringUtils.firstCharUpper(rs.getString("COLUMN_NAME")));
					excelBean.setType(ctype);
					//excelBean.setWrapper(StringUtils.getWrapper(excelBean.getType()));
					excelBean.setDesc(rs.getString("REMARKS"));
					excelEntity.addEntityBeans(excelBean);
				}
				
				// 主键
				List<String> primarys = new ArrayList<String>();
				while (keys.next()) {
					String keyColName = keys.getString("COLUMN_NAME");
					primarys.add(StringUtils.underlineToLowerCamal(keyColName));
				}
				excelEntity.setPrimarys(primarys);
				result.add(excelEntity);
			}
			log.info("result:{}", result);
			return result;
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}
	
}
