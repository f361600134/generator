package com.kepe.dragon.persistent.domain;

import com.kepe.asyncdb.SyncObject;

/**
* ${entityName}Base
* @author Jeremy
*/
public class ${entityName}Base extends SyncObject {

	<#-- Static Field -->
	<#if entityBeans ? exists>
	<#list entityBeans as bean>
	public ${bean.type} ${bean.field};//${bean.desc}
	</#list>
	</#if>
	
	<#-- Constructor -->
	public ${entityName}Base(){
		<#if entityBeans ? exists>
		<#list entityBeans as bean>
		<#if bean.type == 'String'>
		this.${bean.field} = "";
		</#if>
		</#list>
		</#if>
	}
	
	<#-- Getter Setter-->
	<#if entityBeans ? exists>
	<#list entityBeans as bean>
	/** ${bean.desc} **/
	public ${bean.type} get${bean.field ? cap_first}(){
		return this.${bean.field};
	}
	
	public void set${bean.field ? cap_first}(${bean.type} ${bean.field}){
		this.${bean.field} = ${bean.field};
	}
	
	</#list>
	</#if>
	
	@Override
	public String toString() {
		return ${toStr}
	}
}
