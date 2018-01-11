package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_subject")
public class MetaSubject {
	@PrimaryKey
	private String db_id;
	private String name;
	private String seg_id;
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
	public String getSeg_id() {
		return seg_id;
	}
	public void setSeg_id(String seg_id) {
		this.seg_id = seg_id;
	}
	
}
