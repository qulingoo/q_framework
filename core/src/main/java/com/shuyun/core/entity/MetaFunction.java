package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_function")
public class MetaFunction {
	@PrimaryKey
	private String db_id;
	private String name;
	private String returnDataType;
	private String paramJson;
	private String expression;
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReturnDataType() {
		return returnDataType;
	}
	public void setReturnDataType(String returnDataType) {
		this.returnDataType = returnDataType;
	}
	public String getParamJson() {
		return paramJson;
	}
	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	 
	
}
