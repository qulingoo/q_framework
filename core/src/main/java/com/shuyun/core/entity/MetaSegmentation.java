package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_segmentation")
public class MetaSegmentation {
	@PrimaryKey
	private String db_id;
	private String code;
	private String name;
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
