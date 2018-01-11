package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_database")
public class MetaDatabase {
	@PrimaryKey
	private String db_id;
	private String id;
	 
	public String getDb_id() {
		return db_id;
	}
	public void setDb_id(String db_id) {
		this.db_id = db_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
