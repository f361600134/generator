package com.fatiny;

import java.util.List;

import com.fatiny.core.common.Config;
import com.fatiny.core.db.ExcelEntity;
import com.fatiny.core.param.ParamAnalysis;
import com.fatiny.core.proto.ProtocolParser;
import com.fatiny.util.DbUtils;
import com.fatiny.util.FreeMarkerTemplateUtils;
import com.fatiny.util.Printer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try {
    		//配置解析
			ParamAnalysis.analysis();
			
			//数据库内容从初始化
			List<ExcelEntity> list = DbUtils.readDb(Config.dbSource);
			
			//模板初始化
			FreeMarkerTemplateUtils.setConfig(Config.ftlPath);
			
			//解析协议
			ProtocolParser.parse();
			
			//生成代码
			Printer.genPojo(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
