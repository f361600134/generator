package com.fatiny.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fatiny.core.common.Config;
import com.fatiny.core.common.Config.Info;
import com.fatiny.core.db.ExcelEntity;
import com.fatiny.core.proto.ProtocolParser;
import com.fatiny.core.proto.ProtocolStructure;
import com.google.common.collect.Lists;

import freemarker.template.Template;

public class Printer {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Printer.class);
	
	public static void genPojo(List<ExcelEntity> entitys) {
		for (ExcelEntity entity : entitys) {
			try {
				if (Config.genTbMap.keySet().contains(entity.getEntityName())) {
					genEntity(entity);
					genEntityBase(entity);
					genDao(entity);
					genPersistent(entity);
					genController(entity);
					genService(entity);
					genDomain(entity);
					genPBbuilder(entity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
//	/**
//	 * 根据ExcelEntity生成PoJo
//	 * @param entity
//	 * @throws Exception  
//	 * @return void  
//	 * @date 2019年9月16日上午11:33:16
//	 */
//	public static void genPojo(ExcelEntity entity) throws Exception
//	{
//		Template template = FreeMarkerTemplateUtils.getTemplate("DbEntity.ftl");
//		//生成路径
//		File file = new File(Config.outputPath);
//		file.mkdirs();
//		
//		String filename = Config.outputPath + File.separator + entity.getEntityName() + Config.suffix;
//		file = new File(filename);
//
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("entityName", entity.getEntityName());
//		dataMap.put("entityBeans", entity.getEntityBeans());
//		dataMap.put("primaryKey", entity.getPrimaryKey());
//		dataMap.put("toStr", entity.getToStr());
//		dataMap.put("entityIndexs", entity.getEntityIndexs());
//		dataMap.put("indexNames", entity.getIndexNames());
//		dataMap.put("indexs", entity.getIndexs());
//		dataMap.put("options", entity.getOptions());
//		
//        FileOutputStream fos = new FileOutputStream(file);
//        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
//        template.process(dataMap, out);
//        log.info("成功生成文件{}", filename);
//	}
	
	/**
	 * 根据ExcelEntity生成Entity
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genEntity(ExcelEntity entity) throws Exception
	{
		Template template = FreeMarkerTemplateUtils.getTemplate("Entity.ftl");
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path +  File.separator + entity.getEntityName() + Config.suffix;
		file = new File(filename);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}
	
	/**
	 * 根据ExcelEntity生成EntityBase
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genEntityBase(ExcelEntity entity) throws Exception
	{
		Template template = FreeMarkerTemplateUtils.getTemplate("EntityBase.ftl");
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path + File.separator + entity.getEntityName() +"Base" + Config.suffix;
		file = new File(filename);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		dataMap.put("toStr", entity.getToStr());
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}
	
	/**
	 * 根据ExcelEntity生成Entity
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genDao(ExcelEntity entity) throws Exception
	{
		Template template = FreeMarkerTemplateUtils.getTemplate("EntityDao.ftl");
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path +  File.separator + entity.getEntityName()+"DAO" + Config.suffix;
		file = new File(filename);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		dataMap.put("tableName", entity.getTablName());
		dataMap.put("primary", entity.getPrimary());
		dataMap.put("fileds", entity.getFileds());
		dataMap.put("javaFileds", entity.getJavaFiled());
		dataMap.put("sql", entity.getSql());
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}

	/**
	 * 根据ExcelEntity生成Entity
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genPersistent(ExcelEntity entity) throws Exception
	{
		Template template = FreeMarkerTemplateUtils.getTemplate("EntityPersistentProvider.ftl");
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path + File.separator + entity.getEntityName() +"PersistentProvider" + Config.suffix;
		file = new File(filename);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}

	/**
	 * 根据ExcelEntity生成Entity
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genController(ExcelEntity entity) throws Exception
	{
		//EntityContorller.ftl
		Template template = FreeMarkerTemplateUtils.getTemplate("EntityContorller.ftl");
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path +  File.separator + entity.getEntityName()+"Controller" + Config.suffix;
		file = new File(filename);

		//协议层方法获取
		List<ProtocolStructure> protoStructList = Lists.newArrayList();
		for (String key : ProtocolParser.protoMap.keySet()) {
			if (key.contains(entity.getEntityName()) && key.startsWith("Req")) {
				protoStructList.add(ProtocolParser.protoMap.get(key));
			}
		}
		
		//模板信息创建
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		dataMap.put("protoStructList", protoStructList);
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}
	
	/**
	 * 根据ExcelEntity生成Entity
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genService(ExcelEntity entity) throws Exception
	{
		//选择模板
		Template template = null;
		Info info = Config.genTbMap.get(entity.getEntityName());
		if (info.isO2o()) {
			template = FreeMarkerTemplateUtils.getTemplate("EntityService1.ftl");
		}else {
			template = FreeMarkerTemplateUtils.getTemplate("EntityService2.ftl");
		}
		
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path +  File.separator + entity.getEntityName()+"Service" + Config.suffix;
		file = new File(filename);

		//协议层方法获取
		List<ProtocolStructure> protoStructList = Lists.newArrayList();
		for (String key : ProtocolParser.protoMap.keySet()) {
			if (key.contains(entity.getEntityName()) && key.startsWith("Ack")) {
				protoStructList.add(ProtocolParser.protoMap.get(key));
			}
		}
		//模板信息创建
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		dataMap.put("protoStructList", protoStructList);
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}
	
	
	/**
	 * 根据ExcelEntity生成Entity
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genDomain(ExcelEntity entity) throws Exception
	{
		//选择模板
		Template template = null;
		Info info = Config.genTbMap.get(entity.getEntityName());
		if (info.isO2o()) {
			template = FreeMarkerTemplateUtils.getTemplate("EntityDomain1.ftl");
		}else {
			template = FreeMarkerTemplateUtils.getTemplate("EntityDomain2.ftl");
		}
		
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path +  File.separator + entity.getEntityName()+"Domain" + Config.suffix;
		file = new File(filename);

		//模板信息创建
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}
	
	/**
	 * 根据协议生成builder 
	 * @param entity
	 * @throws Exception  
	 * @return void  
	 * @date 2019年9月16日上午11:33:16
	 */
	public static void genPBbuilder(ExcelEntity entity) throws Exception
	{
		//模板
		Template template = FreeMarkerTemplateUtils.getTemplate("EntityPbBuilder.ftl");
		
		//协议层方法获取
		List<ProtocolStructure> protoStructList = Lists.newArrayList();
		for (String key : ProtocolParser.protoMap.keySet()) {
			if (key.contains(entity.getEntityName()) && key.startsWith("PB")) {
				protoStructList.add(ProtocolParser.protoMap.get(key));
			}
		}
				
		//生成路径
		String path = Config.outputPath+"/"+entity.getEntityName().toLowerCase();
		File file = new File(path);
		file.mkdirs();
		
		String filename = path +  File.separator + "PB"+entity.getEntityName()+"InfoBuilder" + Config.suffix;
		file = new File(filename);

		//模板信息创建
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("entityName", entity.getEntityName());
		dataMap.put("entityBeans", entity.getEntityBeans());
		dataMap.put("protoStructList", protoStructList);
		
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        log.info("成功生成文件{}", filename);
	}
	
}
