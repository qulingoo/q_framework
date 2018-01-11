package com.shuyun.core.entity;

import com.alibaba.fastjson.JSON;
import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;
/**
 * 约束
 * @author qlk
 *
 */
@Table("field_reference")
public class Reference {
	@PrimaryKey
	private String id;
	private Type type;// 约束类型
	private String filed_id;// 引用字段ID
	private String context;// 约束内容

	public static enum Type {
		PRIMARY, FOREIGN, UNIQUE, CHECK, DEFAULT;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFiled_id() {
		return filed_id;
	}

	public void setFiled_id(String filed_id) {
		this.filed_id = filed_id;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
