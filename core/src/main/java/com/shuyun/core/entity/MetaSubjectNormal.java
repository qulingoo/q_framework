package com.shuyun.core.entity;

import com.shuyun.core.mybatis.PrimaryKey;
import com.shuyun.core.mybatis.Table;

@Table("meta_subject_normal")
public class MetaSubjectNormal {
	@PrimaryKey
	private String db_id;
	private String name;
	private String table_id;
	private String table_name;
	private String subject_id;
	private String ismaster;
	private String refsubject;
	
	public String getIsmaster() {
		return ismaster;
	}
	public void setIsmaster(String ismaster) {
		this.ismaster = ismaster;
	}
	public String getRefsubject() {
		return refsubject;
	}
	public void setRefsubject(String refsubject) {
		this.refsubject = refsubject;
	}
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
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(String subject_id) {
		this.subject_id = subject_id;
	}
}
