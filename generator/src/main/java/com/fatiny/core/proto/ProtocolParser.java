/**
 * 
 */
package com.fatiny.core.proto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RegExUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;

/**
 * 协议文件解释器
 * key:协议名 value: 协议结构
 * @date 2019年4月23日下午7:53:10
 */
public class ProtocolParser {
	
	public static final Logger logger = LoggerFactory.getLogger(ProtocolParser.class.getName());
	
	public static void main(String[] args) {
		parse();
	}
	
	/**
	 * key:方法名
	 * value: 协议号
	 */
	public static HashBiMap<String, Integer> protoIdMap = HashBiMap.create();
	public static Map<String, ProtocolStructure> protoMap = Maps.newHashMap();
	
	public static void parse() {
		File folder = new File("src/main/resource/proto/");
		for(File file : folder.listFiles()) {
			if(!file.getName().endsWith(".proto")) {
				continue;
			}
			if (file.getName().equals("PBProtocol.proto")) {
				//解析协议号对应的方法名
				getProtocol(file);
				continue;
			}
			List<List<String>> protoList = getProtocolScopeList(file);
			for(List<String> scope : protoList) {
				try {
					protoMap.putAll(parseStructure(scope));
				} catch (Exception e) {
					System.out.println(scope);
					e.printStackTrace();
				}
			}
		}
//		for(ProtocolStructure ps:protoMap.values()) {
//			System.out.println(ps);
//		}
		logger.info("protoId:{}", protoIdMap.keySet());
		logger.info("groupList:{}", protoMap.keySet());
	}
	
	/**
	 * 解释出一个协议结构
	 * @param structList
	 * @return
	 */
	private static Map<String, ProtocolStructure> parseStructure(List<String> structList) {
		Map<String, ProtocolStructure> temp = Maps.newHashMap();
		ProtocolStructure struct = new ProtocolStructure();
		for(String line : structList) {
			try {
				line = line.trim();
				if(line.startsWith("//")) {
					struct.setComment(line.replaceAll("//", "").trim());
				} else if(line.startsWith("message")) {
					struct.setName(line.substring(line.indexOf("message")+8, line.indexOf("{")).trim());
				} else {
					if (line.equals("")) {
						continue;
					}
					if(line.contains("}"))
						continue;
					ProtocolField field = new ProtocolField();
					String[] temps = line.split("//");
					if(temps.length > 1) {
						field.setComment(temps[1].replaceAll("//", "").trim());
					}
					String[] fieldStrs = temps[0].substring(0, temps[0].indexOf("=")).split(" ");
					if(fieldStrs.length>2) {
						field.setIdentifier(fieldStrs[0].trim());
						field.setType(fieldStrs[1].trim());
						field.setName(fieldStrs[2].trim());
					}else {
						field.setType(fieldStrs[0].trim());
						field.setName(fieldStrs[1].trim());
					}
					struct.getFields().add(field);
				}
			} catch (Exception e) {
				logger.info("parseStructure error, line="+line);
				e.printStackTrace();
			}
		}
		temp.put(struct.getName(), struct);
		return temp;
	}
	
	/**
	 * 获取协议号对应的方法名
	 * @param protocolFile
	 * @return
	 */
	private static HashBiMap<String, Integer> getProtocol(File protocolFile) {
		//协议结构列表
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(protocolFile)));
			String line = reader.readLine();
			boolean start = false;
			while(line != null) {
				if (line.startsWith("enum")) {
					start = true;
				}
				if (start && line.contains("=")) {
					String arr[] = line.split("=");
					String key = RegExUtils.replaceAll(arr[0], " ", "").replaceAll("	", "");
					String value = RegExUtils.replaceAll(arr[1], " ", "").replaceAll(";", "");
					protoIdMap.putIfAbsent(key, Integer.valueOf(value));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			logger.error("protocolFile:{}", protocolFile.getName());
			e.printStackTrace();
		}
		return protoIdMap;
	}
	
	/**
	 * 划分每个协议结构的字符串
	 * @param protocolFile
	 * @return
	 */
	private static List<List<String>> getProtocolScopeList(File protocolFile) {
		//协议结构列表
		List<List<String>> scopeList = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(protocolFile)));
			List<String> lineList = new ArrayList<>();
			//标记是否为协议结构
			boolean scoping = false;
			//单个协议结构的内容列表
			List<String> scopeBuilder = null;
			String line = reader.readLine();
			while(line != null) {
				//协议结构开始
				if(line.startsWith("message")) {
					scoping = true;
					scopeBuilder = new ArrayList<>();
					
					//结构的备注
					String comment = lineList.get(lineList.size() - 1);
					if(comment.startsWith("//")) {
						scopeBuilder.add(comment);
					}
				}
				//协议结构结束
				if(line.contains("}")) {
					scopeBuilder.add(line.substring(0, line.indexOf("}") + 1));
					scopeList.add(scopeBuilder);
					scoping = false;
				}
				if(scoping) {
					scopeBuilder.add(line);
				}
				lineList.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			logger.error("protocolFile:{}", protocolFile.getName());
			e.printStackTrace();
		}
		return scopeList;
	}
}
